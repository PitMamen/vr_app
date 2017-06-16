package com.mygt.vrapp.game;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygt.vrapp.ActivityDetailVideo;
import com.mygt.vrapp.R;
import com.mygt.vrapp.adapter.VideoRecommandAdapter;
import com.mygt.vrapp.api.ApiConstant;
import com.mygt.vrapp.api.OKHttpUtil;
import com.mygt.vrapp.api.VRRetrofitService;
import com.mygt.vrapp.api.bean.GameInfoResponse;
import com.mygt.vrapp.api.bean.GameVo;
import com.mygt.vrapp.api.bean.SearchIDYXResponse;
import com.mygt.vrapp.player.PlayerActivity;
import com.mygt.vrapp.view.HorizontalListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.mygt.vrapp.util.FileUtils.getConversionSize;

public class GameDetailActivity extends Activity implements OnClickListener {
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private HorizontalListView mRcdListView;

    private Button down;
    private ImageView iv_pic, iv_icon;
    private TextView gameinfo;
    private TextView name;
    private TextView memory;
    private TextView tv_version;
    private TextView tv_type;
    private String gameId;
    private TextView tv_downCount;
    private String magePrefix;
    private VideoRecommandAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_game_detail);

        Intent intent = getIntent();
        gameId = intent.getStringExtra("gameId");
        Log.d(TAG, "gameId==: " + gameId);

        initView();
        requestData();
        requestGameRecommend();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void initView() {
        down = (Button) findViewById(R.id.play);
        tv_type = (TextView) findViewById(R.id.type);
        tv_downCount = (TextView) findViewById(R.id.count);
        iv_pic = (ImageView) findViewById(R.id.pic);
        iv_icon = (ImageView) findViewById(R.id.icon);
        gameinfo = (TextView) findViewById(R.id.id_detail_video_summary);
        tv_version = (TextView) findViewById(R.id.tv_gameVersion);
        findViewById(R.id.info).setVisibility(View.GONE);
        findViewById(R.id.back).setOnClickListener(this);
        name = (TextView) findViewById(R.id.name);
        memory = (TextView) findViewById(R.id.memory);
        ((TextView) findViewById(R.id.title_txt)).setText(R.string.game_detail);
        ((CheckBox) findViewById(R.id.more_text)).setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gameinfo.setMaxLines(Integer.MAX_VALUE);
                } else {
                    gameinfo.setMaxLines(2);
                }
            }
        });

        mRcdListView = (HorizontalListView) findViewById(R.id.recommand_listview);
        mRcdListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long arg3) {
                Log.i(TAG, "more position " + position);
                Intent intent = new Intent();
                intent.setClass(mContext, GameDetailActivity.class);
                startActivity(intent);
            }
        });
    }


    public void requestData() {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        service.searchIDYX(gameId, ApiConstant.SEARCH_YX)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchIDYXResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(SearchIDYXResponse gameResponse) {
                        String isOkGameIndex = gameResponse.getStatus();    //success
                        Log.d(TAG, "isOkGameIndex2=: " + isOkGameIndex);
                        SearchIDYXResponse.YXResponse yxResponse = gameResponse.getResponse();

                        magePrefix = yxResponse.getIMAGE_URL_PREFIX();
                        GameVo gameInfo = yxResponse.getGame();

                        name.setText("名称：" + gameInfo.getGamename());
                        tv_version.setText("版本：" + gameInfo.getVersion());
                        if (gameInfo.getDescinfo()==null){
                            gameinfo.setText("简介：暂无");
                        }else {
                            gameinfo.setText("简介：" + gameInfo.getDescinfo());
                        }
                        long gameSize = Long.parseLong(gameInfo.getApksize());
                        String Apk_size = getConversionSize(gameSize);
                        memory.setText("大小：" + Apk_size);
                        tv_downCount.setText("下载次数：" + gameInfo.getDownloadnumber() + "");
                        tv_type.setText("类型：" + gameInfo.getType());


                        Glide.with(mContext)
                                .load(magePrefix + gameInfo.getPublicity())
                                .asBitmap()
                                // 仅缓存变换后的图片
                                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                .into(iv_pic);
                        Glide.with(mContext)
                                .load(magePrefix + gameInfo.getIcon())
                                .asBitmap()
                                // 仅缓存变换后的图片
                                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                .into(iv_icon);


                        initView();
                    }
                });

    }


    //详情页底下 相关推荐  游戏searchId
    private void requestGameRecommend() {
        VRRetrofitService service = OKHttpUtil.getBaseService();
        String id = gameId;
        Log.d(TAG, "id=: " + id);
        String type = ApiConstant.SEARCH_YX;
        service.searchIDYX(id, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchIDYXResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchIDYXResponse searchIDYXResponse) {
                        SearchIDYXResponse.YXResponse yx = searchIDYXResponse.getResponse();
                        List<GameVo> gameRemList = yx.getChoice_game();
                        String gameRemImagePrefix = yx.getIMAGE_URL_PREFIX();
                        Log.d(TAG, "gameRemImagePrefix==: " + gameRemImagePrefix);
                        initMyHorizontalListView(gameRemImagePrefix, gameRemList);
                    }
                });
    }


    private void initMyHorizontalListView(String image_url, List<GameVo> gameRemList) {
        mRcdListView = (HorizontalListView) findViewById(R.id.recommand_listview);
        if (null == mAdapter) {
            //详情页底下相关推荐adapter
            mAdapter = new VideoRecommandAdapter(this, ApiConstant.SEARCH_YX, image_url, null, gameRemList);
            mRcdListView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();
        mRcdListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long arg3) {
                Log.i(TAG, "more position " + position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.play:
                break;
            case R.id.collection:
                break;
        }
    }

}
