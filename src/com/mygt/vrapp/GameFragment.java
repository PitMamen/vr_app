package com.mygt.vrapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.mygt.vrapp.adapter.GameAdapter;
import com.mygt.vrapp.api.ApiConstant;
import com.mygt.vrapp.api.OKHttpUtil;
import com.mygt.vrapp.api.VRRetrofitService;
import com.mygt.vrapp.api.bean.AdvertisVo;
import com.mygt.vrapp.api.bean.GameIndexInfo;
import com.mygt.vrapp.api.bean.GameIndexResponse;
import com.mygt.vrapp.api.bean.GameVo;
import com.mygt.vrapp.game.GameDetailActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.mygt.vrapp.R.id.game_btn_Scenes;
import static com.mygt.vrapp.R.id.game_btn_action;
import static com.mygt.vrapp.R.id.game_btn_terror;


public class GameFragment extends Fragment implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();
    private String[] CONTENT;
    private SliderLayout mSliderLayout;
    private RecyclerView mRecyclerView;
    private GameAdapter mAdapter;

    private List<AdvertisVo> mGamesAd = new ArrayList<>(); // 当前广告
    private List<AdvertisVo> mScenesAd = new ArrayList<>();// 场景
    private List<AdvertisVo> mTerrorAd = new ArrayList<>();// 恐怖
    private List<AdvertisVo> mActionAd = new ArrayList<>();// 动作
    private List<GameVo> mGameVos = new ArrayList<>();   // 当前数据
    private List<GameVo> mListScenes = new ArrayList<>();// 场景体验
    private List<GameVo> mListTerror = new ArrayList<>();// 恐怖冒险
    private List<GameVo> mListAction = new ArrayList<>();// 动作射击

    private final int mTypeScenes = 1;
    private final int mTypeTerror = 2;
    private final int mTypeAction = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_layout, container, false);

        view.findViewById(game_btn_Scenes).setOnClickListener(this);
        view.findViewById(R.id.game_btn_terror).setOnClickListener(this);
        view.findViewById(R.id.game_btn_action).setOnClickListener(this);

        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.frag_rv_game);


        initRecyclerView();
        initAdapterListener();

        requestData(mTypeScenes);
        return view;
    }

    private void requestData(final int type) {
//        if (!checkNeedRequest(type)) {
//            mGamesAd.clear();
//            mGamesAd = getCurrentAds(type);
////            updateSliderData(mGamesAd);
//
//            mGameVos.clear();
//            mGameVos = getCurrentData(type);
//            mAdapter.resetData(mGameVos);
//            return;
//        }
        VRRetrofitService service = OKHttpUtil.getBaseService();
        // 接口应该有请求类型，请求的页数
        service.getGameIndex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GameIndexResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(GameIndexResponse gameResponse) {
                        GameIndexInfo info = gameResponse.getResponse();
                        String isOkGameIndex = gameResponse.getStatus();    //success

                        Log.d(TAG, "onNext: isOkGameIndex" + isOkGameIndex);

                        List<AdvertisVo> ads = info.getAdvertis();
                        Log.d(TAG, "ads==: " + ads);
                        List<GameVo> games = info.getGames();
                        Log.d(TAG, "games==: " + games);

                        mGamesAd = ads;
                        mGameVos = games;

//                        switch (type){
//                            case mTypeScenes:
//                                if(null != ads){
//                                    mScenesAd.addAll(ads);
//                                }
//                                if(null != games){
//                                    mListScenes.addAll(games);
//                                }
//                                break;
//                            case mTypeTerror:
//                                if(null != ads){
//                                    mTerrorAd.addAll(ads);
//                                }
//                                if(null != games){
//                                    mListTerror.addAll(games);
//                                }
//                                break;
//                            case mTypeAction:
//                                if(null != ads){
//                                    mActionAd.addAll(ads);
//                                }
//                                if(null != games){
//                                    mListAction.addAll(games);
//                                }
//                                break;
//                        }

                        updateSliderData(mGamesAd);
                        mAdapter.resetData(mGameVos);
                    }
                });
    }


    public void initAdapterListener() {
        mAdapter.setOnHomeAdapterClickListener(new GameAdapter.OnGameAdapterClickListener() {
            @Override
            public void onGameAdapterClick(int position) {
                GameVo gameVo = mGameVos.get(position);
                String gameId = gameVo.getId();
                Intent intent = new Intent(getContext(), GameDetailActivity.class);
                intent.putExtra("gameId", gameId);
                getContext().startActivity(intent);
            }
        });
    }


    private void initRecyclerView() {
        Context context = getActivity().getApplicationContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new CardViewtemDecortion());
        mAdapter = new GameAdapter(mGameVos, context);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void updateSliderData(List<AdvertisVo> ads) {
        if (mSliderLayout != null) {
            mSliderLayout.removeAllSliders();
            for (AdvertisVo banner : ads) {
                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(ApiConstant.IMAGE_URL + banner.getImage());
                textSliderView.description(banner.getTitle());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);
            }
        }
        initSlider();

    }


    //初始化广告栏头部
    public void initSlider() {
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);    //设置广告栏下面的点的位置
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);  //设置翻页时旋转效果
        mSliderLayout.setDuration(3000);   //设置翻页的时间间隔
    }

    private boolean checkNeedRequest(int type) {
        switch (type) {
            case mTypeScenes:
                return checkNull(mListScenes);
            case mTypeTerror:
                return checkNull(mListTerror);
            case mTypeAction:
                return checkNull(mListAction);
            default:
                return false;
        }
    }

    private boolean checkNull(List<GameVo> list) {
        return list.isEmpty();
    }

    private List<GameVo> getCurrentData(int type) {
        switch (type) {
            case mTypeScenes:
                return mListScenes;
            case mTypeTerror:
                return mListTerror;
            case mTypeAction:
                return mListAction;
            default:
                return null;
        }
    }

    private List<AdvertisVo> getCurrentAds(int type) {
        switch (type) {
            case mTypeScenes:
                return mScenesAd;
            case mTypeTerror:
                return mTerrorAd;
            case mTypeAction:
                return mActionAd;
            default:
                return null;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case game_btn_Scenes:
                requestData(mTypeScenes);
                break;
            case  game_btn_terror:
                requestData(mTypeTerror);
                break;
            case  game_btn_action:
                requestData(mTypeAction);
                break;
            default:
                break;
        }


//        if (game_btn_Scenes == id) {
//            requestData(mTypeScenes);
//        } else if (R.id.game_btn_terror == id) {
//            requestData(mTypeTerror);
//        } else if (R.id.game_btn_action == id) {
//            requestData(mTypeAction);
//        }
    }
}

