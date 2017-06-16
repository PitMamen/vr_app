package com.mygt.vrapp.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.mygt.vrapp.App;
import com.mygt.vrapp.user.VideoInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

public class VideoThumbnailLoader {

	private static final String TAG = "VideoThumbnailLoader";

	private static VideoThumbnailLoader ins = new VideoThumbnailLoader();
	
	private List<AsyncTask<String, Void, Bitmap>> tasks = new ArrayList<AsyncTask<String, Void, Bitmap>>();

	private boolean isCancel;

	public static VideoThumbnailLoader getIns() {
		return ins;
	}

	private VideoThumbnailLoader() {
	}

	public void display(String dir, String url,int width, int height, ThumbnailListener thumbnailListener) {

		AsyncTask<String, Void, Bitmap> task = new ThumbnailLoadTask(dir,width, height, thumbnailListener)
				.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url);// 使用AsyncTask自带的线程池
		
		tasks.add(task);

	}

	private class ThumbnailLoadTask extends AsyncTask<String, Void, Bitmap> {

		private ThumbnailListener thumbnailListener;
		private int width;
		private int height;
		String dir;

		public ThumbnailLoadTask(String dir, int width, int height, ThumbnailListener thumbnailListener) {
			this.dir = dir;
			this.width = width;
			this.height = height;
			this.thumbnailListener = thumbnailListener;
		}

		@Override
		protected void onCancelled(Bitmap bitmap) {
			Log.d(TAG, "onCancelled");
			tasks.remove(this);
			thumbnailListener.onThumbnailLoadCompleted(bitmap);
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			Bitmap bitmap = null;
			if (!TextUtils.isEmpty(url)) {
				String name = FileUtils.getFileMD5ToString(url);
				if(name == null) {
					return null;
				}
				Log.d(TAG, "name:"+name+",url:"+url);
				File fullFile = FileUtils.getFullFile(dir, name);
				if(FileUtils.createOrExistsDir(dir)) {
					String fullName = "file:"+File.separator+File.separator+fullFile.getAbsolutePath();
					Log.d(TAG, "fullName:"+fullName);
					if(fullFile.exists()) {
						bitmap = ImageLoader.getInstance().loadImageSync(fullName);
					}else {
						Log.d(TAG, "file is null:"+fullName);
					}
				}

				if (bitmap == null || bitmap.isRecycled()) {
					Log.d(TAG, "bitmap is null");
					bitmap = getVideoThumbnail(url, width, height, MediaStore.Video.Thumbnails.MINI_KIND);
					if(FileUtils.createOrExistsDir(dir)) {
						ImageUtils.save(bitmap, fullFile, Bitmap.CompressFormat.PNG);
					}else {
						Log.d(TAG, "dir is null:"+dir);
					}
				}
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			tasks.remove(this);
			thumbnailListener.onThumbnailLoadCompleted(bitmap);// 回调
		}
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel() {
		this.isCancel = true;
		for (AsyncTask<String, Void, Bitmap> task : tasks) {
			task.cancel(true);
		}
	}
	
	/**
	 * @param videoPath
	 *            视频路径
	 * @param width
	 * @param height
	 * @param kind
	 *            eg:MediaStore.Video.Thumbnails.MICRO_KIND MINI_KIND: 512 x
	 *            384，MICRO_KIND: 96 x 96
	 * @return
	 */
	private Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
		// 获取视频的缩略图
		Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	// 自己定义一个回调,通知外部图片加载完毕
	public interface ThumbnailListener {
		void onThumbnailLoadCompleted(Bitmap bitmap);
	}
}
