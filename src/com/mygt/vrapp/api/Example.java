package com.mygt.vrapp.api;

import android.util.Log;

import com.mygt.vrapp.api.bean.GameIndexResponse;
import com.mygt.vrapp.api.bean.GameInfoResponse;
import com.mygt.vrapp.api.bean.GameUrlResponse;
import com.mygt.vrapp.api.bean.IndexInfo;
import com.mygt.vrapp.api.bean.IndexResponse;
import com.mygt.vrapp.api.bean.MainIndexInfo;
import com.mygt.vrapp.api.bean.MainIndexResponse;
import com.mygt.vrapp.api.bean.SearchGameResponse;
import com.mygt.vrapp.api.bean.SearchIDYSResponse;
import com.mygt.vrapp.api.bean.SearchIDYXResponse;
import com.mygt.vrapp.api.bean.SearchVideoResponse;
import com.mygt.vrapp.api.bean.TypeResponse;
import com.mygt.vrapp.api.bean.VideoByIdResponse;
import com.mygt.vrapp.api.bean.VideoMoreResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class Example {

    private static final String TAG = "Example";

    private VRRetrofitService mService;

    public Example() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mService = retrofit.create(VRRetrofitService.class);
    }

    /**
     * １、获取 APP 首页精选数据 返回数据为  IndexResponse
     */
    public void testGetHomeIndex() {
        mService.getHomeIndex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IndexResponse>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        //开始请求网络
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                        //数据获取完成，可以取消加载框
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(IndexResponse indexResponse) {
                        Log.d(TAG, "onNext: " + indexResponse);
                        //取数据展示，主线程
                    }
                });
    }

    /**
     * ２、获取单个视频资源信息
     */
    public void testGetVideoByID() {
        //传入的两个参数
        String id = "1";
        String type = ApiConstant.VIDEO_BY_ID_TYPE_1;

        mService.getVideoById(id, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoByIdResponse>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(VideoByIdResponse videoByIdResponse) {
                        Log.d(TAG, "onNext: " + videoByIdResponse);
                        //取数据
                    }
                });
    }

    /**
     * ３、获取视频首页的资源信息
     */
    public void getMainIndex() {
        //传入的参数 例子
        String type = ApiConstant.MAIN_INDEX_VR;

        mService.getMainIndex(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MainIndexResponse>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(MainIndexResponse mainIndexResponse) {
                        Log.d(TAG, "onNext: " + mainIndexResponse);
                        //数据
                    }
                });
    }

    /**
     * ４、获取更多视频资源信息
     */
    public void getVideoMore() {
        //传入参数
        String type = ApiConstant.MAIN_INDEX_VR;
        String classification = ApiConstant.VIDEO_MORE_XGMN;
        int pageNumber = 10;

        mService.getVideoMore(type, classification, pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoMoreResponse>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(VideoMoreResponse videoMoreResponse) {
                        Log.d(TAG, "onNext: " + videoMoreResponse);
                        //数据
                    }
                });
    }

    /**
     * ５、获取单个视频资源信息
     */
    public void serachVideo() {
        //传参
        String name = "";//选一个已有视频验证
        int pageNumber = 10;

        mService.searchVideo(name, pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchVideoResponse>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(SearchVideoResponse searchVideoResponse) {
                        Log.d(TAG, "onNext: " + searchVideoResponse);
                        //数据
                    }
                });
    }

    /**
     * 6、游戏主页资源信息
     */
    public void getGameIndex() {
        mService.getGameIndex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GameIndexResponse>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(GameIndexResponse gameIndexResponse) {
                        Log.d(TAG, "onNext: " + gameIndexResponse);
                    }
                });
    }

    /**
     * 7 获取单个游戏资源信息
     */
    public void findGame() {
        //传参
        String id = "1";
        String type = ApiConstant.FIND_GAME_DOWNLOAD;//只能是这个参数

        //获取下载路径
        mService.findGameByIdDownload(id, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GameUrlResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GameUrlResponse gameUrlResponse) {
                        Log.d(TAG, "onNext: " + gameUrlResponse);

                        String url = gameUrlResponse.getResponse().getUrl();
                    }
                });

        // 查询单个游戏资源信息
        mService.findGameByIdInfo(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GameInfoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GameInfoResponse gameInfoResponse) {
                        Log.d(TAG, "onNext: " + gameInfoResponse);
                    }
                });
    }

    /**
     * 8、 搜索游戏资源信息
     */
    public void searchGame() {
        //传参
        String title = "";
        String type = ApiConstant.SEARCH_GAME_SPORTS;
        int pageNumber = 10;
        mService.searchGame(title, type, pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchGameResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchGameResponse searchGameResponse) {
                        Log.d(TAG, "onNext: " + searchGameResponse);
                    }
                });
    }

    /**
     * 9
     */
    public void searchType() {
        String type = ApiConstant.APP_START;

        mService.searchType(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TypeResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TypeResponse typeResponse) {
                        Log.d(TAG, "onNext: " + typeResponse);
                    }
                });
    }

    /**
     * 10、搜索单个视频/游戏资源信息
     */
    public void searchID() {
        String id = "1";
        String type1 = ApiConstant.SEARCH_YS;
        String type2 = ApiConstant.SEARCH_YX;

        //搜索单个视频
        mService.searchIDYS(id, type1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchIDYSResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchIDYSResponse searchIDYSResponse) {
                        Log.d(TAG, "onNext: " + searchIDYSResponse);
                    }
                });

        //搜索游戏资源
        mService.searchIDYX(id, type2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchIDYXResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchIDYXResponse searchIDYXResponse) {
                        Log.d(TAG, "onNext: " + searchIDYXResponse);
                    }
                });
    }
}
