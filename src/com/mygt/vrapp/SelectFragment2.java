package com.mygt.vrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mygt.vrapp.adapter.CardViewtemDecortion;
import com.mygt.vrapp.adapter.HomeAdapter;
import com.mygt.vrapp.api.ApiConstant;
import com.mygt.vrapp.api.OKHttpUtil;
import com.mygt.vrapp.api.VRRetrofitService;
import com.mygt.vrapp.api.bean.AdvertisVo;
import com.mygt.vrapp.api.bean.GameVo;
import com.mygt.vrapp.api.bean.IndexInfo;
import com.mygt.vrapp.api.bean.IndexResponse;
import com.mygt.vrapp.api.bean.VideoVo;
import com.mygt.vrapp.game.GameDetailActivity;
import com.mygt.vrapp.interfaces.onClickCustomListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.mygt.vrapp.api.ApiConstant.GAME_MORE_TYPE_3;
import static com.mygt.vrapp.api.ApiConstant.GAME_MORE_TYPE_4;
import static com.mygt.vrapp.api.ApiConstant.VIDEO_MORE_TYPE_1;
import static com.mygt.vrapp.api.ApiConstant.VIDEO_MORE_TYPE_2;
import static com.mygt.vrapp.util.FileUtils.isNetworkAvailable;

public class SelectFragment2 extends Fragment implements onClickCustomListener {
    private static String TAG = "SelectFragment2";
    private static int NUMBER = 4;
    private String[] strlist;
    private TextView[] textViews;
    private SliderLayout sliderLayout;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private List<Object> mList = new ArrayList<>();
    private String mImagePrefix;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    private List<AdvertisVo> mListAdvertisVo;  //广告栏
    private List<VideoVo> mListVideo;  //精选视频
    private List<VideoVo> mListPlayVideo;  //用户推荐视频
    private List<GameVo> mListChoiceGame;  //精选游戏
    private List<GameVo> mListdownGame;   //用户游戏


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_layout_2, container, false);
            sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
            initText(view);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.frag_rv_home);
            mAdapter = new HomeAdapter(mList, mImagePrefix);
            initRecyclerView();

            requestData();

        return view;
    }

    //请求数据
    public void requestData() {

        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getHomeIndex()
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
                        String encoString = indexResponse.getResponse().toString();
                        Log.d(TAG, "encoString: " + encoString);

                        String de = Des3.decode(encoString);

                        Log.d(TAG, "de==: " + de);

                        //取数据展示，主线程
                        IndexInfo info = indexResponse.getResponse();

                        mImagePrefix = info.getImagePrefix();

                        mListAdvertisVo = info.getAdvertis();  //广告页

                        mListVideo = info.getChoiceVideo();   //精选视频
                        mListPlayVideo = info.getPlayVideo();  //用户推荐视频
                        mListChoiceGame = info.getChoiceGame();      //精选游戏
                        mListdownGame = info.getDownGame();  //用户游戏

                        if (!mListVideo.isEmpty()) {
                            mList.add("精选视频");
                            mList.addAll(mListVideo);
                        }
                        if (!mListPlayVideo.isEmpty()) {
                            mList.add("用户推荐");
                            mList.addAll(mListPlayVideo);
                        }
                        if (!mListChoiceGame.isEmpty()) {
                            mList.add("精选游戏");
                            mList.addAll(mListChoiceGame);
                        }
                        if (!mListdownGame.isEmpty()) {
                            mList.add("用户游戏");
                            mList.addAll(mListdownGame);
                        }

                        mAdapter.resetData(mList, mImagePrefix);

                        initSlider();
                    }
                });
    }


    //初始化广告栏头部
    public void initSlider() {
        if (sliderLayout != null) {
            for (AdvertisVo banner : mListAdvertisVo) {
                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(mImagePrefix + banner.getImage());
                textSliderView.description(banner.getTitle());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                sliderLayout.addSlider(textSliderView);
            }

        }

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);    //设置广告栏下面的点的位置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);  //设置翻页时旋转效果
        sliderLayout.setDuration(3000);   //设置翻页的时间间隔
    }

    //广告栏 下面的4个Text
    private void initText(View view) {
        strlist = getResources().getStringArray(R.array.select_list);
        textViews = new TextView[NUMBER];
        textViews[0] = (TextView) view.findViewById(R.id.textview1);
        textViews[1] = (TextView) view.findViewById(R.id.textview2);
        textViews[2] = (TextView) view.findViewById(R.id.textview3);
        textViews[3] = (TextView) view.findViewById(R.id.textview4);
        for (int i = 0; i < NUMBER; i++) {
            textViews[i].setText(strlist[i]);
            textViews[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ActivityMore.class);
//                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onClick(View item, View widget, int position, int which) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        switch (which) {
            default:
                break;
        }

    }


    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return calSpan(position);
            }
        });
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.addItemDecoration(new CardViewtemDecortion());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnHomeAdapterClickListener(new HomeAdapter.OnHomeAdapterClickListener() {
            @Override
            public void onHomeAdapterClick(int position) {
                String type = checkType(position);
                if (null == type) {
                    String nextType = checkType(position + 1);
                    if (null == nextType) {
                        //越界，异常情况可能会出现
                        return;
                    }
                    //正常点击，更多  跳转更多界面
                    String title = (String) mList.get(position);
                    int type_more = -1;
                    if (title.equals("精选视频")) {
                        type_more = VIDEO_MORE_TYPE_1;
                    } else if (title.equals("用户推荐")) {
                        type_more = VIDEO_MORE_TYPE_2;
                    } else if (title.equals("精选游戏")) {
                        type_more = GAME_MORE_TYPE_3;
                    } else if (title.equals("用户游戏")) {
                        type_more = GAME_MORE_TYPE_4;
                    }

                    Log.d(TAG, "title: " + title.toString());
                    Intent intent = new Intent(getContext(), ActivityMore.class);
                    intent.putExtra("type", type_more);
                    getContext().startActivity(intent);

                    return;
                }

                //如果还没返回，说明点击的是item，type不是YS就是YX

                Intent intent = new Intent();
                //这里需要处理 根据不同的类型来判断
                switch (type) {
                    case ApiConstant.SEARCH_YS:
                        VideoVo videoVo = (VideoVo) mList.get(position);
                        intent.setClass(getContext(),ActivityDetailVideo.class);
                        intent.putExtra("videoId", videoVo.getId());
                        break;
                    case ApiConstant.SEARCH_YX:
                        GameVo gameVo = (GameVo) mList.get(position);
                        intent.setClass(getContext(),GameDetailActivity.class);
                        intent.putExtra("gameId", gameVo.getId());
                        break;
                }
                //传入type
                intent.putExtra("type", type);
                getContext().startActivity(intent);
            }
        });


    }


    private int calSpan(int position) {
        if (mList.get(position) instanceof String) {
            return 2;
        }
        return 1;
    }

    private String checkType(int position) {
        String type = null;
        int len = mList.size();
        if (position >= len) {
            return null;
        }
        if (mList.get(position) instanceof VideoVo) {
            type = ApiConstant.SEARCH_YS;
        } else if (mList.get(position) instanceof GameVo) {
            type = ApiConstant.SEARCH_YX;
        }
        return type;
    }
}  

