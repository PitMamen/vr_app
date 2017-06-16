package com.mygt.vrapp;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygt.vrapp.adapter.VideoRecommandAdapter;
import com.mygt.vrapp.api.ApiConstant;
import com.mygt.vrapp.api.OKHttpUtil;
import com.mygt.vrapp.api.VRRetrofitService;
import com.mygt.vrapp.api.bean.GameVo;
import com.mygt.vrapp.api.bean.IndexInfo;
import com.mygt.vrapp.api.bean.IndexResponse;
import com.mygt.vrapp.api.bean.SearchIDYSResponse;
import com.mygt.vrapp.api.bean.SearchIDYXResponse;
import com.mygt.vrapp.api.bean.SignalUrl;
import com.mygt.vrapp.api.bean.VideoByIdResponse;
import com.mygt.vrapp.api.bean.VideoVo;
import com.mygt.vrapp.database.VRDbManager;
import com.mygt.vrapp.database.VRDbVideo;
import com.mygt.vrapp.interfaces.onClickCustomListener;
import com.mygt.vrapp.player.PlayerActivity;
import com.mygt.vrapp.view.HorizontalListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.zip.InflaterInputStream;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.mygt.vrapp.util.FileUtils.getConversionSize;


//视频详情类
public class ActivityDetailVideo extends Activity implements OnClickListener {
    private static String TAG = "com.mygt.vrapp";
    private Context mContext;
    private VideoRecommandAdapter mRcdAdapter;
    private HorizontalListView mRcdListView;

    private ImageView im_thum;
    private Button play;
    private Button collection;
    private TextView tv_Year;
    private TextView tv_videoTime;
    private TextView tv_collectionCount;
    private TextView tv_videoName;
    private TextView tv_info;
    private TextView tv_downCount;
    private TextView tv_classType;
    private TextView tv_lable;
    private TextView tv_playCount;


    private String videoId;
    private String type;
    private String mUrl;
    private List<VideoVo> mListChoiceVideo;
    private List<GameVo> mListChoiceGame;
    private VideoVo mVideoVo;
    private GameVo mGameVo;
    private VRDbVideo mVrDbVideoC;
    private String mImage_url_prefix;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_video_detail);

        Intent intent = getIntent();
        videoId = intent.getStringExtra("videoId");
        type = intent.getStringExtra("type");
        Log.d(TAG, "type1==: " + type);
        Log.d(TAG, "videoId==: " + videoId);
        initView();
        requestDataByVideoId(); //请求单个视频播放地址 数据 (请求成功)
        String type = intent.getStringExtra("type");
        if (ApiConstant.SEARCH_YS.equals(type)) {
            requestSearchIdYS(); //请求影视类型数据 (请求成功)
        } else if (ApiConstant.SEARCH_YX.equals(type)) {
            requestSearchIdYX(); //请求游戏类型数据 (请求成功)
        }

        VRDbVideo vrVideo = buildVRVideo(mVideoVo, mImage_url_prefix);
        boolean collect = VRDbManager.getInstance().checkCollect(vrVideo);
        int width = getResources().getDimensionPixelSize(R.dimen.video_detail_drawable);
        int left = getResources().getDimensionPixelSize(R.dimen.video_detail_drawable_left);
        if (collect) {
            Drawable collectionDrawable = getResources().getDrawable(R.drawable.collection_p);
            collectionDrawable.setBounds(left, 0, left + width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
            collection.setCompoundDrawables(collectionDrawable, null, null, null);
        } else {
            Drawable collectionDrawable = getResources().getDrawable(R.drawable.collection_n);
            collectionDrawable.setBounds(left, 0, left + width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
            collection.setCompoundDrawables(collectionDrawable, null, null, null);
        }
        play.setOnClickListener(this);
        collection.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    //请求单个视频播放地址 数据
    public void requestDataByVideoId() {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getVideoById(videoId, ApiConstant.VIDEO_BY_ID_TYPE_1)  //获取单个视频播放地址
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoByIdResponse>() {
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
                    public void onNext(VideoByIdResponse videoByIdResponse) {
                        Log.d(TAG, "onNext: " + videoByIdResponse);
                        //取数据展示，主线程
                        VideoByIdResponse indexResponse = videoByIdResponse;
                        SignalUrl url = indexResponse.getResponse();
                        mUrl = url.getUrl();
                        Log.d(TAG, "ur==: " + mUrl);
                        String isOk = indexResponse.getStatus();
                        Log.d(TAG, "url==: " + url);
                        Log.d(TAG, "isOk==: " + isOk);
                        initView();
                    }
                });


    }

    //影视请求
    public void requestSearchIdYS() {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.searchIDYS(videoId, ApiConstant.SEARCH_YS)  //影视
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchIDYSResponse>() {
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
                    public void onNext(SearchIDYSResponse searchIDYSResponse) {
                        Log.d(TAG, "onNext: " + searchIDYSResponse);
                        //取数据展示，主线程
                        SearchIDYSResponse indexResponse = searchIDYSResponse;
                        String isOk = indexResponse.getStatus();
                        Log.d(TAG, "isOk1==: " + isOk);  //success
                        SearchIDYSResponse.YSResponse ysResponse = indexResponse.getResponse();
                        mImage_url_prefix = ysResponse.getIMAGE_URL_PREFIX();
                        mVideoVo = ysResponse.getVideo();
                        mListChoiceVideo = ysResponse.getChoice_video();
                        Log.d(TAG, "mListChoiceVideo==: " + mListChoiceVideo);

                        tv_videoName.setText(mVideoVo.getVideoname());
                        tv_Year.setText("年份：" + mVideoVo.getYear());
                        tv_videoTime.setText("时长：" + mVideoVo.getDuration());
                        tv_downCount.setText("下载次数：" + mVideoVo.getDownloadnumber());
                        tv_classType.setText("所属分类：" + mVideoVo.getClassification());
                        tv_collectionCount.setText("收藏次数：" + mVideoVo.getCollectnumber());
                        if (mVideoVo.getDetaileddescription() == null) {
                            tv_info.setText("简介：暂无");
                        } else {
                            tv_info.setText("简介：" + mVideoVo.getDetaileddescription());
                        }
                        tv_lable.setText("标签：" + mVideoVo.getLabels());
                        tv_playCount.setText("播放次数：" + mVideoVo.getPlaynumber());
                        Glide.with(mContext).load(mImage_url_prefix + mVideoVo.getThumbnails()).into(im_thum);

                        initView();
                        initMyHorizontalListView(mImage_url_prefix);

                    }
                });
    }


    //游戏请求
    public void requestSearchIdYX() {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.searchIDYX(videoId, ApiConstant.SEARCH_YX)  //游戏
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchIDYXResponse>() {
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
                    public void onNext(SearchIDYXResponse searchIDYXResponse) {
                        Log.d(TAG, "onNext: " + searchIDYXResponse);
                        //取数据展示，主线程
                        SearchIDYXResponse indexResponse = searchIDYXResponse;
                        String isOk = indexResponse.getStatus();
                        Log.d(TAG, "isOk2==: " + isOk);  //success
                        SearchIDYXResponse.YXResponse yxResponse = indexResponse.getResponse();
                        mImage_url_prefix = yxResponse.getIMAGE_URL_PREFIX();
                        mGameVo = yxResponse.getGame();
                        mListChoiceGame = yxResponse.getChoice_game();
                        Log.d(TAG, "mListChoiceGame==: " + mListChoiceGame);  //有数据
//
                        tv_videoName.setText(mGameVo.getGamename());
                        long Conversionsize = Long.parseLong(mGameVo.getApksize());
                        String Apk_size = getConversionSize(Conversionsize);
                        tv_Year.setText("大小：" + Apk_size);
                        tv_videoTime.setText("版本：" + mGameVo.getVersion());
                        tv_downCount.setText("下载次数：" + mGameVo.getDownloadnumber());
                        tv_classType.setText("游戏类型：" + mGameVo.getType());
                        tv_collectionCount.setText("分组：" + mGameVo.getGroups());
                        tv_info.setText("简介：" + mGameVo.getDescinfo());
                        tv_lable.setText("标签：" + mGameVo.getLables());
                        tv_playCount.setVisibility(View.GONE);
                        Glide.with(ActivityDetailVideo.this)
                                .load(mImage_url_prefix + mGameVo.getPublicity())
                                .asBitmap()
                                // 仅缓存变换后的图片
                                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                .into(im_thum);
//                        Glide.with(mContext).load("http://192.168.2.46:8088/" + mGameVo.getPublicity()).into(im_thum);
                        initView();
                        initMyHorizontalListView(mImage_url_prefix);
                    }
                });
    }


    private void initView() {
        play = (Button) findViewById(R.id.play);
        tv_lable = (TextView) findViewById(R.id.tv_lable);
        tv_downCount = (TextView) findViewById(R.id.id_detail_video_counts);
        tv_playCount = (TextView) findViewById(R.id.tv_palyCount);
        tv_classType = (TextView) findViewById(R.id.class_type);
        tv_videoName = (TextView) findViewById(R.id.id_detail_video_name);
        im_thum = (ImageView) findViewById(R.id.pic);
        collection = (Button) findViewById(R.id.collection);
        tv_Year = (TextView) findViewById(R.id.id_detail_video_year);
        tv_collectionCount = (TextView) findViewById(R.id.tv_collectionCount);
        tv_videoTime = (TextView) findViewById(R.id.id_detail_video_times);
        tv_info = (TextView) findViewById(R.id.id_detail_video_summary);
        findViewById(R.id.back).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_txt)).setText(R.string.video_detail);
        ((CheckBox) findViewById(R.id.more_text)).setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tv_info.setMaxLines(Integer.MAX_VALUE);
                } else {
                    tv_info.setMaxLines(2);
                }
            }
        });
        initDrawable();

    }

    private void initMyHorizontalListView(String image_url) {
        mRcdListView = (HorizontalListView) findViewById(R.id.recommand_listview);
        if (null == mRcdAdapter) {
            //详情页底下相关推荐adapter
            mRcdAdapter = new VideoRecommandAdapter(this, type, image_url, mListChoiceVideo, mListChoiceGame);
            mRcdListView.setAdapter(mRcdAdapter);
        }
        mRcdAdapter.notifyDataSetChanged();
        mRcdListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long arg3) {
                Log.i(TAG, "more position " + position);
                PlayerActivity.startThis(ActivityDetailVideo.this, mUrl);   //可播放
            }
        });
    }


    private void initDrawable() {
        int width = getResources().getDimensionPixelSize(R.dimen.video_detail_drawable);
        int left = getResources().getDimensionPixelSize(R.dimen.video_detail_drawable_left);
        Drawable playDrawable = getResources().getDrawable(R.drawable.btn_play);
        playDrawable.setBounds(left, 0, left + width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        play.setCompoundDrawables(playDrawable, null, null, null);
//        Drawable collectionDrawable = getResources().getDrawable(R.drawable.btn_collection);
//        collectionDrawable.setBounds(left, 0, left + width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
//        collection.setCompoundDrawables(collectionDrawable, null, null, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.play:
                PlayerActivity.startThis(this, mUrl);   //可播放
                VRDbVideo vrDbVideoH = buildVRVideo(mVideoVo, mImage_url_prefix);
                Log.d(TAG, "currentVideoID==: " + videoId);
                if (null != vrDbVideoH) {
                    //addHistory(vrDbVideoH);
                    VRDbManager.getInstance().insertHistory(vrDbVideoH);
                }
                break;
            case R.id.collection:
                mVrDbVideoC = buildVRVideo(mVideoVo, mImage_url_prefix);
                if (null != mVrDbVideoC) {
                    Log.d(TAG, "updateCollect ********************");

                    boolean collect = VRDbManager.getInstance().updateCollect(mVrDbVideoC);
                    int width = getResources().getDimensionPixelSize(R.dimen.video_detail_drawable);
                    int left = getResources().getDimensionPixelSize(R.dimen.video_detail_drawable_left);
                    if (collect) {
                        Drawable collectionDrawable = getResources().getDrawable(R.drawable.collection_p);
                        collectionDrawable.setBounds(left, 0, left+width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
                        collection.setCompoundDrawables(collectionDrawable, null, null, null);
                    } else {
                        Drawable collectionDrawable = getResources().getDrawable(R.drawable.collection_n);
                        collectionDrawable.setBounds(left, 0, left+width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
                        collection.setCompoundDrawables(collectionDrawable, null, null, null);
                    }
                }
                break;
        }
    }


    public VRDbVideo buildVRVideo(VideoVo vo, String imagePex) {
        if (vo != null && imagePex != null) {
            VRDbVideo vrDbVideo = new VRDbVideo();
            vrDbVideo.setId(vo.getId());
            vrDbVideo.setVideoname(vo.getVideoname());
            vrDbVideo.setYear(vo.getYear());
            vrDbVideo.setClassification(vo.getClassification());
            vrDbVideo.setCollectnumber(vo.getCollectnumber());
            vrDbVideo.setDownloadnumber(vo.getDownloadnumber());
            vrDbVideo.setPlaynumber(vo.getPlaynumber());
            vrDbVideo.setLabels(vo.getLabels());
            vrDbVideo.setDescription(vo.getDescription());
            vrDbVideo.setThumbnails(imagePex + vo.getThumbnails());
            return vrDbVideo;
        }
        return null;
    }


}
