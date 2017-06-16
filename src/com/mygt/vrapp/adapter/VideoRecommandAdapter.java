package com.mygt.vrapp.adapter;

import java.util.List;
import java.util.Objects;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygt.vrapp.R;
import com.mygt.vrapp.api.ApiConstant;
import com.mygt.vrapp.api.bean.GameVo;
import com.mygt.vrapp.api.bean.VideoVo;
import com.mygt.vrapp.interfaces.onClickCustomListener;
import com.mygt.vrapp.view.HorizontalListView;
import com.mygt.vrapp.view.VideoItemView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class VideoRecommandAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private onClickCustomListener mCallback;
    private String TAG = getClass().getSimpleName();

    private String imagePrefix;

    private List<VideoVo> mChoiceList;
    private List<GameVo> mGameVoList;
    private String type;

    public VideoRecommandAdapter(Context context, String type, String imagePrefix, List<VideoVo> mChoiceList, List<GameVo> mGameVoList) {
        this.imagePrefix = imagePrefix;
        this.type = type;
        this.mChoiceList = mChoiceList;
        this.mGameVoList = mGameVoList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public VideoRecommandAdapter(String imagePrefix, List<GameVo> gameVoList) {
        this.imagePrefix = imagePrefix;
        mGameVoList = gameVoList;
    }

    @Override
    public int getCount() {
        if(type.equals(ApiConstant.SEARCH_YS)){
            return mChoiceList == null ? 0 : mChoiceList.size();
        }else{
            return mGameVoList == null ? 0 : mGameVoList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if(type.equals(ApiConstant.SEARCH_YS)){
            return mChoiceList.get(position);
        }else{
            return mGameVoList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;

        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_video_classify, parent, false);
            holder = new ViewHolder();
            holder.item = (ImageView) convertView.findViewById(R.id.iv_image);
            holder.videoName = (TextView) convertView.findViewById(R.id.tv_videoName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String name = "";
        String url = "";
        if (type.equals(ApiConstant.SEARCH_YS)) {
            VideoVo videoVo = mChoiceList.get(position);
            url = imagePrefix + videoVo.getThumbnails();
            name = videoVo.getVideoname();
            Log.d(TAG, "VideoName==: " + name);
        } else if (type.equals(ApiConstant.SEARCH_YX)) {
            GameVo gameVo = mGameVoList.get(position);
            url = imagePrefix + gameVo.getIcon();
            name = gameVo.getGamename();
            Log.d(TAG, "VideoName==: " + name);
        }
        holder.videoName.setText(name);
        Glide.with(mContext)
                .load(url)
                .asBitmap()
                // 仅缓存变换后的图片
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.item);


        /*final View view = convertView;
        final int p = position;
        final int item = holder.item.getId();
        holder.item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	mCallback.onClick(view, parent, p, item);
            }
        });*/
        return convertView;
    }

    static class ViewHolder {
        ImageView item;
        TextView videoName;
    }

}
