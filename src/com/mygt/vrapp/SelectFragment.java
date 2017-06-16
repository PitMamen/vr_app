package com.mygt.vrapp;

import java.util.List;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mygt.vrapp.adapter.VideoClassifyAdapter;
import com.mygt.vrapp.adapter.VideoMoreAdapter;
import com.mygt.vrapp.api.ApiConstant;
import com.mygt.vrapp.api.Example;
import com.mygt.vrapp.api.VRRetrofitService;
import com.mygt.vrapp.api.bean.AdvertisVo;
import com.mygt.vrapp.api.bean.GameVo;
import com.mygt.vrapp.api.bean.IndexInfo;
import com.mygt.vrapp.api.bean.IndexResponse;
import com.mygt.vrapp.api.bean.MainIndexInfo;
import com.mygt.vrapp.api.bean.MainIndexResponse;
import com.mygt.vrapp.api.bean.VideoVo;
import com.mygt.vrapp.bean.VideoBean;
import com.mygt.vrapp.interfaces.onClickCustomListener;
import com.mygt.vrapp.util.OkHttpHelper;
import com.mygt.vrapp.view.CycleDot;
import com.mygt.vrapp.view.ImagePager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SelectFragment extends Fragment implements onClickCustomListener {
    private static String TAG = "com.mygt.vrapp";
    private static int NUMBER = 4;
    private Context mContext;
    private String[] strlist;
    private TextView[] textViews;
    private VideoMoreAdapter mVideoMoreAdapter;
    private SliderLayout sliderLayout;
    private GridView mGridView;


    private  List<AdvertisVo> mListAdvertisVo;  //广告栏
    private  List<VideoVo>   mListVideo;  //精选视频
    private  List<VideoVo>  mListPlayVideo;  //用户推荐视频
    private  List<GameVo>   mListChoiceGame;  //精选游戏
    private  List<GameVo>   mListdownGame;   //用户游戏



    @Override
    public void onAttach(Activity activity) {
        this.mContext = activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_layout, container, false);
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);

        Log.i(TAG, "onCreateView");
        initText(view);
        mGridView = (GridView) view.findViewById(R.id.more_gridview);


        requestData();
        return view;
    }

    //请求数据
    public void requestData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        final VRRetrofitService mService = retrofit.create(VRRetrofitService.class);


        mService.getHomeIndex()
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
                        //取数据展示，主线程
                        IndexInfo info = indexResponse.getResponse();
                        mListAdvertisVo= info.getAdvertis();  //广告页
                        mListVideo  = info.getChoiceVideo();   //精选视频
                        mListPlayVideo = info.getPlayVideo();  //用户推荐视频
                        mListChoiceGame = info.getChoiceGame();      //精选游戏
                        mListdownGame = info.getDownGame();  //用户游戏
                        Log.d(TAG, "mListPlayVideo.size==: "+mListPlayVideo);
                        initSlider();
                        initView();

                    }
                });
    }



    //初始化广告栏头部
    public void initSlider() {
        if (sliderLayout != null) {
            for (AdvertisVo banner : mListAdvertisVo) {
                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image("http://192.168.2.46:8088/"+banner.getImage());
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
                    intent.setClass(mContext, ActivityMore.class);
                    startActivity(intent);
                }
            });
        }
    }


    private void initView() {
        if (null == mVideoMoreAdapter) {
            mVideoMoreAdapter = new VideoMoreAdapter(mContext, mListVideo, this);
            mGridView.setAdapter(mVideoMoreAdapter);
        }
        mVideoMoreAdapter.notifyDataSetChanged();
        mGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),ActivityDetailVideo.class);

                VideoVo videoVo = mListVideo.get(position);
                intent.putExtra("videoId",videoVo.getId());
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View item, View widget, int position, int which) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        switch (which) {
//            case R.id.more_btn:
//                Log.i(TAG, "more_btn position " + position);
//
//                intent.setClass(mContext, ActivityMore.class);
//                startActivity(intent);
//                break;
//            case R.id.id_item0:
//                Log.i(TAG, "id_item0 position " + position);
//                intent.setClass(mContext, ActivityDetailVideo.class);
//                startActivity(intent);
//                break;
//            case R.id.id_item1:
//                Log.i(TAG, "id_item1 position " + position);
//                intent.setClass(mContext, ActivityDetailVideo.class);
//                startActivity(intent);
//                break;
//            case R.id.id_item2:
//                Log.i(TAG, "id_item2 position " + position);
//                intent.setClass(mContext, ActivityDetailVideo.class);
//                startActivity(intent);
//                break;
//            case R.id.id_item3:
//                Log.i(TAG, "id_item3 position " + position);
//                intent.setClass(mContext, ActivityDetailVideo.class);
//                startActivity(intent);
//                break;
            default:
                break;
        }

    }
}  

