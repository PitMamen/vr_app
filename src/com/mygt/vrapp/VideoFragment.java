package com.mygt.vrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.mygt.vrapp.api.bean.MainIndexInfo;
import com.mygt.vrapp.api.bean.MainIndexResponse;
import com.mygt.vrapp.api.bean.VideoVo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class VideoFragment extends Fragment implements View.OnClickListener {
    private static String TAG = "VideoFragment";

    private SliderLayout sliderLayout;
    List<AdvertisVo> mAdvertisVos;  //头部广告

    //请求的网络数据
    private List<Object> mListVR = new ArrayList<>();
    private List<Object> mListJT = new ArrayList<>();
    private List<Object> mListVIP = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private String mImagePrefix;
    private String mCurrentType = ApiConstant.MAIN_INDEX_VR;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_layout, container, false);
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.video_rv_list);

        //第一次初始化数据，默认vr全景
        requestData(ApiConstant.MAIN_INDEX_VR);


        mAdapter = new HomeAdapter(mListVR, mImagePrefix);
        initRecyclerView();

        //初始化按钮
        view.findViewById(R.id.video_btn_vr).setOnClickListener(this);
        view.findViewById(R.id.video_btn_jt).setOnClickListener(this);
        view.findViewById(R.id.video_btn_vip).setOnClickListener(this);

        initAdapterListener();
        return view;
    }


    //请求数据
    public void requestData(final String type) {
        mCurrentType = type;
        if (!isDataNeedRequest(type)) {
            updateAdapter(type);
            return;
        }
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.getMainIndex(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MainIndexResponse>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        //开始请求网络

                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                        //数据获取完成，可以取消加载框
                        updateAdapter(type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(MainIndexResponse indexResponse) {
                        Log.d(TAG, "onNext: " + indexResponse);
                        //取数据展示，主线程
                        String isOk = indexResponse.getStatus();
                        Log.d(TAG, "isOk==: " + isOk);    //success
                        MainIndexInfo info = indexResponse.getResponse();
                        mImagePrefix = info.getImagePrefix();
                        mAdvertisVos = info.getAdvertis();
                        Log.d(TAG, "mAdvertisVos.size==: " + mAdvertisVos);
                        updateSliderData(mAdvertisVos);
                        fillAdapterData(type, info);
                    }
                });
    }


    private void updateSliderData(List<AdvertisVo> ads) {
        if (sliderLayout != null) {
            sliderLayout.removeAllSliders();
            for (AdvertisVo banner : ads) {
                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(mImagePrefix + banner.getImage());
                textSliderView.description(banner.getTitle());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                sliderLayout.addSlider(textSliderView);
            }
        }
        initSlider();

    }


    //初始化广告栏头部
    public void initSlider() {
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);    //设置广告栏下面的点的位置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);  //设置翻页时旋转效果
        sliderLayout.setDuration(3000);   //设置翻页的时间间隔
    }


    private void initRecyclerView() {
        resetRecyclerViewLayoutManager();
        mRecyclerView.addItemDecoration(new CardViewtemDecortion());
        mRecyclerView.setAdapter(mAdapter);
    }


    private void resetRecyclerViewLayoutManager() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return calSpan(mCurrentType, position);
            }
        });
        mRecyclerView.setLayoutManager(manager);
    }


    // 计算占比
    private int calSpan(String type, int position) {
        List<Object> list;
        switch (type) {
            case ApiConstant.MAIN_INDEX_VR:
                list = mListVR;
                break;
            case ApiConstant.MAIN_INDEX_JTYY:
                list = mListJT;
                break;
            case ApiConstant.MAIN_INDEX_VIP:
                list = mListVIP;
                break;
            default:
                list = new ArrayList<>();
                break;
        }
        if (list.isEmpty()) {
            return 0;
        }
        if (list.get(position) instanceof String) {
            return 2;
        }
        return 1;
    }

    //用户点击时，如果已有数据则不再请求网络，直接更新适配器
    private boolean isDataNeedRequest(String type) {
        boolean needRequest = true;
        switch (type) {
            case ApiConstant.MAIN_INDEX_VR:
                if (!checkIsNullOrNone(mListVR)) {
                    needRequest = false;
                }
                break;
            case ApiConstant.MAIN_INDEX_JTYY:
                if (!checkIsNullOrNone(mListJT)) {
                    needRequest = false;
                }
                break;
            case ApiConstant.MAIN_INDEX_VIP:
                if (!checkIsNullOrNone(mListVIP)) {
                    needRequest = false;
                }
                break;
        }
        return needRequest;
    }

    //填充适配器数据
    private void fillAdapterData(String type, MainIndexInfo info) {
        List<Object> list = new ArrayList<>();

        List<VideoVo> JZMJList = info.getJZMJ();   //极致美景
        System.out.println("JZMJList:" + JZMJList);
        List<VideoVo> XGMNlist = info.getXGMN();  //性感美女  [] type不同
        System.out.println("XGMNlist:" + XGMNlist);
        List<VideoVo> JBRWlist = info.getJBRW();  //劲爆热舞   [] type不同
        System.out.println("JBRWlist:" + JBRWlist);
        List<VideoVo> KXYLlist = info.getKXYL();  //开心娱乐
        System.out.println("KXYLlist:" + KXYLlist);
        List<VideoVo> KHJSlist = info.getKHJS();  //科幻惊悚
        System.out.println("KHJSlist:" + KHJSlist);
        List<VideoVo> GQMVlist = info.getGQMV();  //高清MV
        System.out.println("GQMVlist:" + GQMVlist);
        List<VideoVo> WYZYlist = info.getWYZY();  //文艺知音
        System.out.println("WYZYlist:" + WYZYlist);
        List<VideoVo> YXSPlist = info.getYXSP();  //游戏视频
        System.out.println("YXSPlist:" + YXSPlist);

        if (!checkIsNullOrNone(JZMJList)) {
            list.add("极致美景");
            list.addAll(JZMJList);
        }
        if (!checkIsNullOrNone(XGMNlist)) {
            list.add("性感美女");
            list.addAll(XGMNlist);
        }
        if (!checkIsNullOrNone(JBRWlist)) {
            list.add("劲爆热舞");
            list.addAll(JBRWlist);
        }
        if (!checkIsNullOrNone(KXYLlist)) {
            list.add("开心娱乐");
            list.addAll(KXYLlist);
        }
        if (!checkIsNullOrNone(KHJSlist)) {
            list.add("科幻惊悚");
            list.addAll(KHJSlist);
        }
        if (!checkIsNullOrNone(GQMVlist)) {
            list.add("高清MV");
            list.addAll(GQMVlist);
        }
        if (!checkIsNullOrNone(WYZYlist)) {
            list.add("文艺知音");
            list.addAll(WYZYlist);
        }
        if (!checkIsNullOrNone(YXSPlist)) {
            list.add("游戏视频");
            list.addAll(YXSPlist);
        }
        switch (type) {
            case ApiConstant.MAIN_INDEX_VR:
                mListVR.addAll(list);
                break;
            case ApiConstant.MAIN_INDEX_JTYY:
                mListJT.addAll(list);
                break;
            case ApiConstant.MAIN_INDEX_VIP:
                mListVIP.addAll(list);
                break;
        }
    }

    private boolean checkIsNullOrNone(List list) {
        return null == list || list.isEmpty();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.video_btn_vr == id) {
            requestData(ApiConstant.MAIN_INDEX_VR);
        } else if (R.id.video_btn_jt == id) {
            requestData(ApiConstant.MAIN_INDEX_JTYY);
        } else if (R.id.video_btn_vip == id) {
            requestData(ApiConstant.MAIN_INDEX_VIP);
        }
        resetRecyclerViewLayoutManager();
    }

    //更新适配器数据
    private void updateAdapter(String type) {
        switch (type) {
            case ApiConstant.MAIN_INDEX_VR:
                mAdapter.resetData(mListVR, mImagePrefix);
                break;
            case ApiConstant.MAIN_INDEX_JTYY:
                mAdapter.resetData(mListJT, mImagePrefix);
                break;
            case ApiConstant.MAIN_INDEX_VIP:
                mAdapter.resetData(mListVIP, mImagePrefix);
                break;
        }
    }

    private void initAdapterListener() {
        mAdapter.setOnHomeAdapterClickListener(new HomeAdapter.OnHomeAdapterClickListener() {
            @Override
            public void onHomeAdapterClick(int position) {
                //当前适配器数据
                List<Object> list = mAdapter.getAdapterData();
                //动作
                //用户点击了更多，跳转到更多
                if (list.get(position) instanceof String) {
                    //接口/video/ videoMore.do
                    int len = list.size();
                    if (position + 1 < len) {
                        VideoVo videoVo = (VideoVo) list.get(position + 1);
                        String type = mCurrentType;
                        String classification = videoVo.getClassification();
                        // 到更多
                        Intent intent = new Intent(getActivity(), ActivityMore.class);
                        intent.putExtra("type", type);
                        intent.putExtra("classification", classification);
                        intent.putExtra("imagePrefix", mImagePrefix);
                        startActivity(intent);
                    }
                }
                //用户点击了item
                else {
                    //到详情
                    VideoVo videoVo = (VideoVo) list.get(position);
                    String videoId = videoVo.getId();
                    String type = ApiConstant.SEARCH_YS;
                    Intent intent = new Intent(getContext(), ActivityDetailVideo.class);
                    intent.putExtra("videoId", videoId);
                    intent.putExtra("type", type);
                    startActivity(intent);
                }
            }
        });
    }


}

