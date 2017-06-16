package com.mygt.vrapp.database;

/**
 * Created on 2017/5/19.
 *
 * @author lkuan
 */

public class VRDbConstant {

    // 数据库
    public static final String DB_NAME = "vr.db";
    public static final int DB_VERSION = 1;

    // 数据表
    public static final String TABLE_HISTORY = "history";
    public static final String TABLE_COLLECT = "collect";

    // 字段
    public static final String _ID = "_id";//键值，任何操作都尽量不要修改该值
    public static final String ID = "id";
    public static final String VIDEO_NAME = "videoName";
    public static final String IMAGE_PREFIX = "imagePrefix";
    public static final String THUMBNAILS = "thumbnails";
    public static final String CLASSIFICATION = "classification";
    public static final String COLLECT_NUMBER = "collectNumber";
    public static final String DESCRIPTION = "description";
    public static final String DOWNLOAD_NUMBER = "downloadNumber";
    public static final String DURATION = "duration";
    public static final String LABELS = "labels";
    public static final String LAUD_NUMBER = "laudNumber";
    public static final String PLAY_NUMBER = "playNumber";
    public static final String YEAR = "year";
    public static final String DETAILED_DESCRIPTION = "detailedDescription";


    // 列数 _id = 0
    public static final int T_COLUMN_KEY_ID = 0;
    public static final int T_COLUMN_ID = 1;
    public static final int T_COLUMN_VIDEO_NAME = 2;
    public static final int T_COLUMN_IMAGE_PREFIX = 3;
    public static final int T_COLUMN_THUMBNAILS = 4;
    public static final int T_COLUMN_CLASSIFICATION = 5;
    public static final int T_COLUMN_COLLECT_NUMBUER = 6;
    public static final int T_COLUMN_DESCRIPTION = 7;
    public static final int T_COLUMN_DOWNLOAD_NUMBER = 8;
    public static final int T_COLUMN_DURATION = 9;
    public static final int T_COLUMN_LABELS = 10;
    public static final int T_COLUMN_LAUD_NUMBER = 11;
    public static final int T_COLUMN_PLAY_NUMBER = 12;
    public static final int T_COLUMN_YEAR = 13;
    public static final int T_COLUMN_DETAILED_DESCRIPTION = 14;
}
