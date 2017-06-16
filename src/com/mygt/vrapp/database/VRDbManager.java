package com.mygt.vrapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mygt.vrapp.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/5/19.
 *
 * @author lkuan
 */

public class VRDbManager {

    public String TAG = getClass().getSimpleName();

    private VRSQLiteOpenHelper helper;

    private VRDbManager() {
        Context context = App.getInstance();
        helper = new VRSQLiteOpenHelper(context);
    }

    private static class DbInstance {
        private static final VRDbManager instance = new VRDbManager();
    }

    public static VRDbManager getInstance() {
        return DbInstance.instance;
    }


    /**
     * 判断是否已经有收藏，已有返回true,没有返回false
     **/
    public boolean checkCollect(VRDbVideo dbVideo) {
        if (null == dbVideo) {
            return false;
        }
        String id = dbVideo.getId();
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT (" + VRDbConstant._ID + ")FROM "
                + VRDbConstant.TABLE_COLLECT
                + " WHERE " + VRDbConstant.ID + "=?;";
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        if (null != cursor) {
            int count = cursor.getCount();
            cursor.close();
            return count > 0;
        }
        db.close();
        return false;
    }

    public void insertHistory(VRDbVideo dbVideo) {
        if (null == dbVideo) {
            return;
        }
        String id = dbVideo.getId();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(VRDbConstant.ID, dbVideo.getId());
        values.put(VRDbConstant.VIDEO_NAME, dbVideo.getVideoname());
        values.put(VRDbConstant.IMAGE_PREFIX, dbVideo.getImagePrefix());
        values.put(VRDbConstant.THUMBNAILS, dbVideo.getThumbnails());
        values.put(VRDbConstant.CLASSIFICATION, dbVideo.getClassification());
        values.put(VRDbConstant.COLLECT_NUMBER, dbVideo.getCollectnumber());
        values.put(VRDbConstant.DESCRIPTION, dbVideo.getDescription());
        values.put(VRDbConstant.DOWNLOAD_NUMBER, dbVideo.getDownloadnumber());
        values.put(VRDbConstant.DURATION, dbVideo.getDuration());
        values.put(VRDbConstant.LABELS, dbVideo.getLabels());
        values.put(VRDbConstant.LAUD_NUMBER, dbVideo.getLaudnumber());
        values.put(VRDbConstant.PLAY_NUMBER, dbVideo.getPlaynumber());
        values.put(VRDbConstant.YEAR, dbVideo.getYear());
        values.put(VRDbConstant.DETAILED_DESCRIPTION, dbVideo.getDetaileddescription());

        String sql = "SELECT (" + VRDbConstant._ID + ")FROM "
                + VRDbConstant.TABLE_HISTORY + " WHERE " + VRDbConstant.ID + "=?;";
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        if (null != cursor) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(VRDbConstant.T_COLUMN_KEY_ID);
                values.put(VRDbConstant._ID, _id);
            }
            cursor.close();
        }
        db.replace(VRDbConstant.TABLE_HISTORY, null, values);
        // delete old data
        cursor = db.query(VRDbConstant.TABLE_HISTORY, null, null, null, null, null, null);
        if (null != cursor) {
            int count = cursor.getCount();
            if (count > 8) {
                cursor.moveToFirst();
                String _id = cursor.getString(VRDbConstant.T_COLUMN_KEY_ID);
                db.delete(VRDbConstant.TABLE_HISTORY, VRDbConstant._ID + "=?", new String[]{_id});
            }
            cursor.close();
        }
        db.close();
    }

    /**
     * 删除历史记录
     * @param dbVideo
     */

    public void deleteHistory(VRDbVideo dbVideo) {
        if(null == dbVideo){
            return ;
        }
        String id = dbVideo.getId();
        if(null != id && !id.isEmpty()){
            SQLiteDatabase db = helper.getWritableDatabase();
            db.delete(VRDbConstant.TABLE_HISTORY, VRDbConstant.ID + "=?", new String[]{id});
            db.close();
        }

    }


	/**
	* ture添加, false取消
	*
	*/
    public boolean updateCollect(VRDbVideo dbVideo) {
        if (null == dbVideo) {
            return false;
        }
		boolean collect = false;
        String id = dbVideo.getId();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT (" + VRDbConstant._ID + ")FROM "
                + VRDbConstant.TABLE_COLLECT + " WHERE "
                + VRDbConstant.ID + "=?;";
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        if (null != cursor && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(VRDbConstant.T_COLUMN_KEY_ID);
                Log.d(TAG, "_id: " + _id);
                String deleteSql = "DELETE FROM "
                        + VRDbConstant.TABLE_COLLECT
                        + " WHERE " + VRDbConstant._ID + "=" + _id + ";";
                db.execSQL(deleteSql);
            }
            cursor.close();
			
			collect = false;
        } else {
            ContentValues values = new ContentValues();
            values.put(VRDbConstant.ID, dbVideo.getId());
            values.put(VRDbConstant.VIDEO_NAME, dbVideo.getVideoname());
            values.put(VRDbConstant.IMAGE_PREFIX, dbVideo.getImagePrefix());
            values.put(VRDbConstant.THUMBNAILS, dbVideo.getThumbnails());
            values.put(VRDbConstant.CLASSIFICATION, dbVideo.getClassification());
            values.put(VRDbConstant.COLLECT_NUMBER, dbVideo.getCollectnumber());
            values.put(VRDbConstant.DESCRIPTION, dbVideo.getDescription());
            values.put(VRDbConstant.DOWNLOAD_NUMBER, dbVideo.getDownloadnumber());
            values.put(VRDbConstant.DURATION, dbVideo.getDuration());
            values.put(VRDbConstant.LABELS, dbVideo.getLabels());
            values.put(VRDbConstant.LAUD_NUMBER, dbVideo.getLaudnumber());
            values.put(VRDbConstant.PLAY_NUMBER, dbVideo.getPlaynumber());
            values.put(VRDbConstant.YEAR, dbVideo.getYear());
            values.put(VRDbConstant.DETAILED_DESCRIPTION, dbVideo.getDetaileddescription());
            db.insert(VRDbConstant.TABLE_COLLECT, null, values);
			
			collect = true;
        }
        db.close();
		return collect;
    }


    public List<VRDbVideo> queryHistory() {
        return queryTable(VRDbConstant.TABLE_HISTORY);
    }

    public List<VRDbVideo> queryCollect() {
        return queryTable(VRDbConstant.TABLE_COLLECT);
    }

    private List<VRDbVideo> queryTable(String table) {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<VRDbVideo> list = new ArrayList<>();
        Cursor cursor = db.query(table, null, null, null, null, null, VRDbConstant._ID + " DESC");
        if (null != cursor) {
            db.beginTransaction();
            while (cursor.moveToNext()) {
                VRDbVideo videoVo = new VRDbVideo();
                videoVo.setId(cursor.getString(VRDbConstant.T_COLUMN_ID));
                videoVo.setVideoname(cursor.getString(VRDbConstant.T_COLUMN_VIDEO_NAME));
                videoVo.setImagePrefix(cursor.getString(VRDbConstant.T_COLUMN_IMAGE_PREFIX));
                videoVo.setThumbnails(cursor.getString(VRDbConstant.T_COLUMN_THUMBNAILS));
                videoVo.setClassification(cursor.getString(VRDbConstant.T_COLUMN_CLASSIFICATION));
                videoVo.setCollectnumber(cursor.getInt(VRDbConstant.T_COLUMN_COLLECT_NUMBUER));
                videoVo.setDescription(cursor.getString(VRDbConstant.T_COLUMN_DESCRIPTION));
                videoVo.setDownloadnumber(cursor.getInt(VRDbConstant.T_COLUMN_DOWNLOAD_NUMBER));
                videoVo.setDuration(cursor.getString(VRDbConstant.T_COLUMN_DURATION));
                videoVo.setLabels(cursor.getString(VRDbConstant.T_COLUMN_LABELS));
                videoVo.setLaudnumber(cursor.getInt(VRDbConstant.T_COLUMN_LAUD_NUMBER));
                videoVo.setPlaynumber(cursor.getInt(VRDbConstant.T_COLUMN_PLAY_NUMBER));
                videoVo.setYear(cursor.getString(VRDbConstant.T_COLUMN_YEAR));
                videoVo.setDetaileddescription(cursor.getString(VRDbConstant.T_COLUMN_DETAILED_DESCRIPTION));
                list.add(videoVo);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            cursor.close();
        }
        db.close();
        return list;
    }


}
