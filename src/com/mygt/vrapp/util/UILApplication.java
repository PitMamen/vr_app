/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.mygt.vrapp.util;

import java.util.ArrayList;
import java.util.Locale;

import com.mygt.vrapp.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class UILApplication extends Application {
	

	public static ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	public void onCreate() {	
		super.onCreate();	
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		/*if(imageLoader!=null){
			imageLoader.clearMemoryCache();
			imageLoader.clearDiscCache();
		}	*/
	}
	
	public static void initLocalLang(Context context, String lang_type) {
		Context mContext;
		Resources res;
		Configuration config;
		DisplayMetrics dm;

		mContext = context;
		res = mContext.getResources();
		config = res.getConfiguration();
		dm = res.getDisplayMetrics();
		config.locale = Locale.SIMPLIFIED_CHINESE;
		/*
		if (lang_type.equals("zh_CN")) {
			config.locale = Locale.SIMPLIFIED_CHINESE;
		} else if (lang_type.equals("zh_TW")) {
			config.locale = Locale.TRADITIONAL_CHINESE;
		} else if (lang_type.equals("en")) {
			config.locale = Locale.ENGLISH;
			//config.locale = Locale.JAPANESE;
		}
		else if (lang_type.equals("ja")) {
			//config.locale = Locale.ENGLISH;
			config.locale = Locale.JAPANESE;
		}
		else if (lang_type.equals("ko")) {
			config.locale = Locale.KOREAN;
			//config.locale = Locale.JAPANESE;
		}
		else{
			config.locale = Locale.SIMPLIFIED_CHINESE;
		}
		*/
		res.updateConfiguration(config, dm);
	}
	
	public static void InitService(Context context){

		/*if(!ImageLoader.getInstance().isInited()){
			initImageLoader(context.getApplicationContext());
		}*/
		
		if(!ImageLoader.getInstance().isInited()){
			initImageLoader(context.getApplicationContext());
			imageLoader = ImageLoader.getInstance();
		}
	
	}

	public static void UnInitService(){
	
		if(imageLoader!=null){
			imageLoader.clearMemoryCache();
			//imageLoader.clearDiscCache();
		}

	}

	
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them, 
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheSize(10*1024*1024)
				/*.enableLogging()*/ // Not necessary in common
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	public static DisplayImageOptions videoOption = new DisplayImageOptions.Builder()
	.showStubImage(R.drawable.video_default)
	.showImageForEmptyUri(R.drawable.video_default)
	.showImageOnFail(R.drawable.video_default)
	.cacheInMemory(true)
	.cacheOnDisc(true)
	.displayer(new SimpleBitmapDisplayer())
	.build();
	
	/*public static DisplayImageOptions tvOption = new DisplayImageOptions.Builder()
	.showStubImage(R.drawable.tv_default)
	.showImageForEmptyUri(R.drawable.tv_default)
	.showImageOnFail(R.drawable.tv_default)
	.cacheInMemory(true)
	.cacheOnDisc(true)
	.displayer(new SimpleBitmapDisplayer())
	.build();
	
	public static DisplayImageOptions messageOption = new DisplayImageOptions.Builder()
	.showStubImage(R.drawable.dailog_messsage_default_pic)
	.showImageForEmptyUri(R.drawable.dailog_messsage_default_pic)
	.showImageOnFail(R.drawable.dailog_messsage_default_pic)
	.cacheInMemory(true)
	.cacheOnDisc(true)
	.displayer(new SimpleBitmapDisplayer())
	.build();*/
}