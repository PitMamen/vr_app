package com.mygt.vrapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 2017/5/19.
 *
 * @author lkuan
 */

public class VRSQLiteOpenHelper extends SQLiteOpenHelper {

    public VRSQLiteOpenHelper(Context context) {
        super(context, VRDbConstant.DB_NAME, null, VRDbConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableSql(VRDbConstant.TABLE_HISTORY));
        db.execSQL(createTableSql(VRDbConstant.TABLE_COLLECT));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do sth update
    }

    private String createTableSql(String table) {
        return "CREATE TABLE "
                + table
                + "("
                + VRDbConstant._ID + " INTEGER PRIMARY KEY,"
                + VRDbConstant.ID + " TEXT,"
                + VRDbConstant.VIDEO_NAME + " TEXT,"
                + VRDbConstant.IMAGE_PREFIX + " TEXT,"
                + VRDbConstant.THUMBNAILS + " TEXT,"
                + VRDbConstant.CLASSIFICATION + " TEXT,"
                + VRDbConstant.COLLECT_NUMBER + " INTEGER DEFAULT 0,"
                + VRDbConstant.DESCRIPTION + " TEXT,"
                + VRDbConstant.DOWNLOAD_NUMBER + " INTEGER DEFAULT 0,"
                + VRDbConstant.DURATION + " TEXT,"
                + VRDbConstant.LABELS + " TEXT,"
                + VRDbConstant.LAUD_NUMBER + " INTEGER DEFAULT 0,"
                + VRDbConstant.PLAY_NUMBER + " INTEGER DEFAULT 0,"
                + VRDbConstant.YEAR + " INTEGER DEFAULT 0,"
                + VRDbConstant.DETAILED_DESCRIPTION + " TEXT"
                + ");";
    }
}
