package com.mygt.vrapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygt.vrapp.R;
import com.mygt.vrapp.api.bean.GameVo;
import com.mygt.vrapp.api.bean.VideoVo;

import java.util.List;

import static com.mygt.vrapp.util.FileUtils.getConversionSize;

/**
 * Created by Richie on 2017/6/1.
 */

public class MoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> list;
    private String imagePrefix;
    private HomeAdapter.OnHomeAdapterClickListener listener;

    public MoreAdapter(List<Object> list, String imagePrefix) {
        this.list = list;
        this.imagePrefix = imagePrefix;
    }

    public void resetData(List<Object> list, String imagePrefix) {
        this.list = list;
        this.imagePrefix = imagePrefix;
        notifyDataSetChanged();
    }

    public List<Object> getAdapterData() {
        return list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View bodyView = LayoutInflater.from(context).inflate(R.layout.item_more_adapter, parent, false);
        return new MoreAdapter.BodyViewHolder(bodyView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MoreAdapter.BodyViewHolder bodyHolder = (MoreAdapter.BodyViewHolder) holder;
        String url = "", name = "";
        if (list.get(position) instanceof VideoVo) {
            VideoVo info = (VideoVo) list.get(position);
            url = imagePrefix + info.getThumbnails();
            name = info.getVideoname();
        } else if (list.get(position) instanceof GameVo) {
            GameVo info = (GameVo) list.get(position);
            url = imagePrefix + info.getPublicity();
            name = info.getGamename();
        }
        bodyHolder.name.setText(name);

        Glide.with(bodyHolder.itemView.getContext())
                .load(url)
                .asBitmap()
                // 仅缓存变换后的图片
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(bodyHolder.icon);

       final  int p = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onHomeAdapterClick(p);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return   null == list ? 0 : list.size();
    }


    private class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView name;

        private BodyViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.adapter_body_icon);
            name = (TextView) itemView.findViewById(R.id.adapter_body_name);
        }
    }


    public interface OnMoreAdapterClickListener {
        void onHomeAdapterClick(int position);
    }

    public void setOnMoreAdapterClickListener(HomeAdapter.OnHomeAdapterClickListener listener) {
        this.listener = listener;
    }
}
