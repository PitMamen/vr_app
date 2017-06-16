package com.mygt.vrapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.mygt.vrapp.adapter.CardViewtemDecortion;
import com.mygt.vrapp.adapter.HomeAdapter;
import com.mygt.vrapp.adapter.MoreAdapter;
import com.mygt.vrapp.api.ApiConstant;
import com.mygt.vrapp.api.OKHttpUtil;
import com.mygt.vrapp.api.VRRetrofitService;
import com.mygt.vrapp.api.bean.GameVo;
import com.mygt.vrapp.api.bean.MoreGameResponse;
import com.mygt.vrapp.api.bean.MoreVideoResponse;
import com.mygt.vrapp.api.bean.VideoMoreInfo;
import com.mygt.vrapp.api.bean.VideoMoreResponse;
import com.mygt.vrapp.api.bean.VideoVo;
import com.mygt.vrapp.game.GameDetailActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


//更多
public class ActivityMore extends Activity {

    private static final String TAG = "ActivityMore";

    private RecyclerView mVideoList;
    private List<Object> mList = new ArrayList<>();
    private String mImagePrefix;
//    private HomeAdapter mAdapter;
    private MoreAdapter mAdapter;
    private List<VideoVo> mVideoVos;
    private List<GameVo> mGameVos;

    private SwipeRefreshLayout mSwipe;
    private final int mTypeYS = 1;
    private final int mTypeSP = 2;
    private final int mTypeYX = 3;
    private int mCurrentType = mTypeYS;//1、影视； 2、首页视频； 3、首页游戏
    private int mYsPage = 1;// 影视请求的页数
    private int mSpPage = 1;// 视频请求的页数
    private int mYxPage = 1;// 游戏请求的页数


    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);


        initView();

        Intent intent = getIntent();
        if (null == intent) {
            return;
        }
        int type = intent.getIntExtra("type", -1);
        //说明type是String类型
        if (type == -1) {
            initData();                // 影视界面 更多
            mCurrentType = mTypeYS;
        } else {
            // type 为正数
            if (type < 3) {
                initSelectMoreVideoData();  //首页  视频更多
                mCurrentType = mTypeSP;
            } else {
                initSelectMoreGameData();   //首页 游戏更多
                mCurrentType = mTypeYX;
            }
        }
    }

    private void initView() {
        TextView title = (TextView) findViewById(R.id.title_txt);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mVideoList = (RecyclerView) findViewById(R.id.rv_video_more_list);
        title.setText("更多");

        findViewById(R.id.back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swipeDownRefresh();
    }

    private void initData() {
        requestData(1);
        mAdapter = new MoreAdapter(mList, mImagePrefix);
        mVideoList.setAdapter(mAdapter);
        mVideoList.setLayoutManager(new GridLayoutManager(this, 3));
        mVideoList.addItemDecoration(new CardViewtemDecortion());
        mAdapter.setOnMoreAdapterClickListener(new HomeAdapter.OnHomeAdapterClickListener() {
            @Override
            public void onHomeAdapterClick(int position) {
                Intent intent = new Intent(ActivityMore.this, ActivityDetailVideo.class);
                String type = ApiConstant.SEARCH_YS;
                VideoVo videoVo = (VideoVo) mList.get(position);
                String videoId = videoVo.getId();
                intent.putExtra("videoId", videoId);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }

    //请求影视更多数据
    private void requestData(int page) {
        Intent intent = getIntent();
        if (null == intent) {
            return;
        }
        mImagePrefix = intent.getStringExtra("imagePrefix");
        String type = intent.getStringExtra("type");
        String classification = intent.getStringExtra("classification");

        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getVideoMore(type, classification, page)//这个参数1目前写死，以后若要实现上拉刷新将其变成可变即可
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoMoreResponse>() {
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
                        VideoMoreInfo moreInfo = videoMoreResponse.getResponse();
                        List<VideoVo> videoVos = moreInfo.getData();
                        mList.clear();
                        mList.addAll(videoVos);
                        mAdapter.resetData(mList, mImagePrefix);
                        mSwipe.setRefreshing(false);
                    }
                });
    }

    //视频更多
    private void initSelectMoreVideoData() {
        requestSelcetVideoHomeData(1);
        mAdapter = new MoreAdapter(mList, mImagePrefix);
        mVideoList.setAdapter(mAdapter);
        mVideoList.setLayoutManager(new GridLayoutManager(this, 3));
        mVideoList.addItemDecoration(new CardViewtemDecortion());
        mAdapter.setOnMoreAdapterClickListener(new HomeAdapter.OnHomeAdapterClickListener() {
            @Override
            public void onHomeAdapterClick(int position) {
                Intent intent = new Intent(ActivityMore.this, ActivityDetailVideo.class);
                String type = ApiConstant.SEARCH_YS;
                VideoVo videoVo = (VideoVo) mList.get(position);
                String videoId = videoVo.getId();
                intent.putExtra("videoId", videoId);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }


    //请求首页视频更多数据
    private void requestSelcetVideoHomeData(int page) {
        Intent intent = getIntent();
        if (null == intent) {
            return;
        }
        Bundle bundle = this.getIntent().getExtras();
        int type = bundle.getInt("type");
        Log.d(TAG, "type: " + type);

        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getMoreVideo(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoreVideoResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(MoreVideoResponse morevideoResponse) {
                        MoreVideoResponse.MoreVideoInfo info = morevideoResponse.getResponse();
                        Log.d(TAG, "info2=: " + info);
                        mVideoVos = info.getData();
                        Log.d(TAG, "mVideoData=: " + mVideoVos);
                        mImagePrefix = info.getImagePrefix();
                        if (mVideoVos != null) {
                            mList.clear();
                            mList.addAll(mVideoVos);
                            mAdapter.resetData(mList, mImagePrefix);
                            mSwipe.setRefreshing(false);
                        }
                    }
                });
    }

    //首页游戏更多
    private void initSelectMoreGameData() {

        requestSelcetGameHomeData(1);
        mAdapter = new MoreAdapter(mList, mImagePrefix);
        mVideoList.setAdapter(mAdapter);
        mVideoList.setLayoutManager(new GridLayoutManager(this, 3));
        mVideoList.addItemDecoration(new CardViewtemDecortion());
        mAdapter.setOnMoreAdapterClickListener(new HomeAdapter.OnHomeAdapterClickListener() {
            @Override
            public void onHomeAdapterClick(int position) {
                Intent intent = new Intent(ActivityMore.this, GameDetailActivity.class);
                String type = ApiConstant.SEARCH_YX;
                GameVo gameVo = (GameVo) mList.get(position);
                String gameId = gameVo.getId();
                intent.putExtra("gameId", gameId);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }

    //请求首页游戏更多数据
    private void requestSelcetGameHomeData(int page) {
        Intent intent = getIntent();
        if (null == intent) {
            return;
        }
        int type = intent.getIntExtra("type", -1);
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getMoreGame(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoreGameResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(MoreGameResponse moregameResponse) {
                        MoreGameResponse.MoreGameInfo info = moregameResponse.getResponse();
                        Log.d(TAG, "info2=: " + info);
                        mGameVos = info.getData();
                        Log.d(TAG, "mGameData==: " + mGameVos);
                        mImagePrefix = info.getImagePrefix();
                        if (mGameVos != null) {
                            mList.clear();
                            mList.addAll(mGameVos);
                            mAdapter.resetData(mList, mImagePrefix);
                            mSwipe.setRefreshing(false);
                        }
                    }
                });
    }

    private void swipeDownRefresh() {
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (mCurrentType) {
                    case mTypeYS:
                        mYsPage++;
                        requestData(mYsPage);
                        break;
                    case mTypeSP:
                        mSpPage++;
                        requestSelcetVideoHomeData(mSpPage);
                        break;
                    case mTypeYX:
                        mYxPage++;
                        requestSelcetGameHomeData(mYxPage);
                        break;
                }
            }
        });
    }
}
