package com.mygt.vrapp.Image;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.graphics.Bitmap;
import android.view.View;

public class MyImageLoader {

	
	private MyImageLoader(){}
	
	private static class LazyHolder {
		private static final MyImageLoader INSTANCE = new MyImageLoader();
	}
	
	private static MyImageLoader getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void getImage(String url) {
		ImageLoader.getInstance().loadImage(url, new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String s, View view) {
			}
			
			@Override
			public void onLoadingFailed(String s, View view, FailReason failreason) {
			}
			
			@Override
			public void onLoadingComplete(String s, View view, Bitmap bitmap) {
			}
			
			@Override
			public void onLoadingCancelled(String s, View view) {
			}
		});
	}
	
	public void withImage(String url) {
		
	}
	
	
	
}
