package com.mygt.vrapp.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mygt.vrapp.App;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class OKHttpUtil {

    private volatile static OKHttpUtil instance = null;
    private volatile  static OkHttpClient okHttpClient = null;

    private OKHttpUtil() {
        Context context = App.getInstance();
        long maxCacheSize = 30 * 1024 * 1024; // 30M
        String cacheDir = context.getCacheDir() + "/cache";
        Cache cache = new Cache(new File(cacheDir), maxCacheSize);
        okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(mCacheInterceptor)
                .addNetworkInterceptor(mNetworkInterceptor)
                .cache(cache)
                .build();
    }

    public static OKHttpUtil getInstance() {
        if (null == instance) {
            synchronized (OKHttpUtil.class) {
                if (null == instance) {
                    instance = new OKHttpUtil();
                }
            }
        }
        return instance;
    }

    public static OkHttpClient getOkHttpClient() {
        if(null == okHttpClient){
            getInstance();
        }
        return okHttpClient;
    }

    public static VRRetrofitService getBaseService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.API_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(VRRetrofitService.class);
    }

    public static VRRetrofitService getVRService(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(VRRetrofitService.class);
    }

    private Interceptor mNetworkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
//            request.header("api-version=1.0.0");
            Response response = chain.proceed(request);
            if (isNetworkConnected()) {
                // 有网络时，缓存超时时间为3小时
                int maxAge = 3 * 60;
                response = response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            } else {
                // 无网络时，设置超时时间为24小时
                int maxStale = 1 * 60 * 60 * 24;
                response = response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };

    private Interceptor mCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            // 如果没网络，重定向请求，请求缓存
            if (!isNetworkConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            return chain.proceed(request);
        }
    };

    private boolean isNetworkConnected() {
        Context context = App.getInstance();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (null != networkInfo && networkInfo.isAvailable());
    }
}
