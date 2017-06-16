package com.mygt.vrapp.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygt.vrapp.R;
import com.mygt.vrapp.api.ApiConstant;
import com.mygt.vrapp.api.bean.GameVo;

import java.util.List;

import static com.mygt.vrapp.util.FileUtils.getConversionSize;

/**
 * Created by Richie on 2017/5/17.
 */

public class GameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GameVo> mGameVoList;
    private Context mContext;
    private OnGameAdapterClickListener listener;

    public GameAdapter(List<GameVo> list, Context context) {
        this.mGameVoList = list;
        this.mContext = context;
    }

    public void resetData(List<GameVo> list){
        this.mGameVoList = list;
        notifyDataSetChanged();
    }

    //TODO 增量更新
    private void updateData(List<GameVo> list){

    }

    private class GameDiffCallback extends DiffUtil.Callback{

        private List<GameVo> list; // new data

        public GameDiffCallback(List<GameVo> list){
            this.list = list;
        }

        @Override
        public int getOldListSize() {
            return null == mGameVoList ? 0 : mGameVoList.size();
        }

        @Override
        public int getNewListSize() {
            return null == list ? 0 : list.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            //TODO 判断是否相等
            return false;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.game_item, parent, false);
        return new ViewHodle(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ViewHodle hodle = (ViewHodle) holder;

        GameVo gameVo = mGameVoList.get(position);
        long Conversionsize = Long.parseLong(gameVo.getApksize());
        String Apk_size = getConversionSize(Conversionsize);
        hodle.tv_gameName.setText(gameVo.getGamename());
        hodle.tv_gameSize.setText(Apk_size);
        hodle.tv_gameType.setText(gameVo.getType());
        hodle.tv_info.setText(gameVo.getDescinfo());
        hodle.tv_downCount.setText(String.valueOf(gameVo.getDownloadnumber()));
        hodle.tv_gameVersion.setText(gameVo.getVersion());
        Glide.with(mContext)
                .load(ApiConstant.IMAGE_URL + gameVo.getIcon())
                .asBitmap()
                // 仅缓存变换后的图片
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(hodle.iv_icon);

        final int p = position;
        hodle.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onGameAdapterClick(p);
            }
        });

        hodle.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onGameAdapterClick(p);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mGameVoList != null ? mGameVoList.size() : 0;
    }

    private class ViewHodle extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private Button btn_down;
        private TextView tv_gameName;
        private TextView tv_gameSize;
        private TextView tv_gameType;
        private TextView tv_downCount;
        private TextView tv_info;
        private TextView tv_gameVersion;
        private LinearLayout layout;
        private ImageView iv_collection;

        public ViewHodle(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.icon);
            btn_down = (Button) itemView.findViewById(R.id.down);
            tv_gameName = (TextView) itemView.findViewById(R.id.name);
            tv_gameSize = (TextView) itemView.findViewById(R.id.memory);
            tv_gameType = (TextView) itemView.findViewById(R.id.type);
            tv_downCount = (TextView) itemView.findViewById(R.id.count);
            tv_info = (TextView) itemView.findViewById(R.id.info);
            tv_gameVersion = (TextView) itemView.findViewById(R.id.tv_gameVersion);
            iv_collection = (ImageView) itemView.findViewById(R.id.iv_collection);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
        }
    }

    public interface OnGameAdapterClickListener {
        void onGameAdapterClick(int position);
    }

    public void setOnHomeAdapterClickListener(GameAdapter.OnGameAdapterClickListener listener) {
        this.listener = listener;
    }
}
