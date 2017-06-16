package com.mygt.vrapp.user;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mygt.vrapp.util.FileUtils;
import com.mygt.vrapp.util.MemoryUtil;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


/***
 * 查找本地视频类
 */
public class VideoSearchManager {

    private static final String TAG = "VideoSearchManager";

    private boolean isCancel;

    private boolean isFinish;

    private Callback mCallback;

    private List<AsyncTask<String, Integer, List<VideoInfo>>> tasks = new ArrayList<AsyncTask<String, Integer, List<VideoInfo>>>();

    public void searchVideo(final Context context, final Callback callback) {
        this.mCallback = callback;

        setCancel();

        MemoryUtil memoryUtil = new MemoryUtil(context);
        String[] paths = memoryUtil.getSDCardpath();
        String insertFile = Environment.getExternalStorageDirectory().getAbsolutePath();
        startTask(insertFile);
        if (paths != null && paths.length > 0) {
            for (String path : paths) {
                if (!path.equals(insertFile)) {
                    String externalSd = path;
                    Log.d(TAG, "insertFile" + insertFile + ",externalSd" + externalSd);
                    startTask(externalSd);
                }
            }
        }

    }

    private void startTask(String path) {
        VideoSearchTask task = new VideoSearchTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, path);
        tasks.add(task);
    }

    class VideoSearchTask extends AsyncTask<String, Integer, List<VideoInfo>> {

        @Override
        protected void onCancelled(List<VideoInfo> result) {
            Log.d(TAG, "onCancelled");
            tasks.remove(this);
            mCallback.onFinish(result, tasks.size() == 0);
        }

        @Override
        protected void onPostExecute(List<VideoInfo> result) {
            Log.d(TAG, "onPostExecute");
            if (result != null) {
                Log.d(TAG, "onPostExecute,size:" + result.size());
            }
            tasks.remove(this);
            Log.d(TAG, "onPostExecute,task size:" + tasks.size());
            mCallback.onFinish(result, tasks.size() == 0);
        }

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "onProgressUpdate");
            super.onProgressUpdate(values);
        }

        @Override
        protected List<VideoInfo> doInBackground(String... params) {
            Log.d(TAG, "doInBackground");
            String path = params[0];
            return getVideoList(path);
        }

        public List<VideoInfo> getVideoList(String path) {
            File file = new File(path);
            return getVideoList(file);
        }

        public List<VideoInfo> getVideoList(File file) {
            if (!FileUtils.isFileExists(file) || !file.isDirectory()) {
                return null;
            }

			/*Set<String> set = new HashSet<String>();
			for(String path:file.list()) {
				set.add(path);
			}*/

            List<VideoInfo> allVideoList = null;// 视频信息集合
            allVideoList = new ArrayList<VideoInfo>();
            getVideoFile(allVideoList, file);// 获得视频文件
            Log.d(TAG, "size:" + allVideoList.size());
            return allVideoList;
        }

        private void getVideoFile(final List<VideoInfo> list, File file) {// 获得视频文件

            file.listFiles(new FileFilter() {

                @Override
                public boolean accept(File file) {
                    if (isCancelled()) {// 大于1mb
                        return false;
                    }

                    // sdCard找到视频名称
                    String name = file.getName();

                    // sdCard找到视频名称
                    int i = name.lastIndexOf('.');
                    if (i != -1) {
                        name = name.substring(i);
                        if (name.equalsIgnoreCase(".mp4") || name.equalsIgnoreCase(".3gp")
                                || name.equalsIgnoreCase(".wmv") || name.equalsIgnoreCase(".ts")
                                || name.equalsIgnoreCase(".rmvb") || name.equalsIgnoreCase(".mov")
                                || name.equalsIgnoreCase(".m4v") || name.equalsIgnoreCase(".avi")
                                || name.equalsIgnoreCase(".m3u8") || name.equalsIgnoreCase(".3gpp")
                                || name.equalsIgnoreCase(".3gpp2") || name.equalsIgnoreCase(".mkv")
                                || name.equalsIgnoreCase(".flv") || name.equalsIgnoreCase(".divx")
                                || name.equalsIgnoreCase(".f4v") || name.equalsIgnoreCase(".rm")
                                || name.equalsIgnoreCase(".asf") || name.equalsIgnoreCase(".ram")
                                || name.equalsIgnoreCase(".mpg") || name.equalsIgnoreCase(".v8")
                                || name.equalsIgnoreCase(".swf") || name.equalsIgnoreCase(".m2v")
                                || name.equalsIgnoreCase(".asx") || name.equalsIgnoreCase(".ra")
                                || name.equalsIgnoreCase(".ndivx") || name.equalsIgnoreCase(".xvid")) {
                            long length = file.length();
                            if (length > 1024 * 1024) {
                                VideoInfo vi = new VideoInfo();
                                vi.setDisplayName(file.getName());
                                vi.setPath(file.getAbsolutePath());
                                vi.setSize(length);
                                list.add(vi);
                            }
                        }
                        return true;
                    } else if (file.isDirectory() && !((name.equals("Android") || name.equals("tencent")))) {
                        getVideoFile(list, file);
                    }
                    return false;
                }
            });
        }

    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel() {
        this.isCancel = true;
        for (AsyncTask<String, Integer, List<VideoInfo>> task : tasks) {
            task.cancel(true);
        }
    }

    public interface Callback {

        public void onFinish(List<VideoInfo> list, boolean isFinish);

    }

}
