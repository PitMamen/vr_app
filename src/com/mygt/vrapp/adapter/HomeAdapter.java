package com.mygt.vrapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Created on 2017/5/10.
 *
 * @author lkuan
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> list;
    private String imagePrefix;
    private OnHomeAdapterClickListener listener;

    public HomeAdapter(List<Object> list, String imagePrefix) {
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
        switch (viewType) {
            case 0:
                View headView = LayoutInflater.from(context).inflate(R.layout.adapter_home_head_layout, parent, false);
                return new HeadViewHolder(headView);
            case 1:
                View bodyView = LayoutInflater.from(context).inflate(R.layout.adapter_home_body_layout, parent, false);
                return new BodyViewHolder(bodyView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                String title = list.get(position).toString();
                HeadViewHolder headHolder = (HeadViewHolder) holder;
                headHolder.title.setText(title);
                break;
            case 1:
                BodyViewHolder bodyHolder = (BodyViewHolder) holder;
                String url = "", name = "", recording = "", size = "";
                if (list.get(position) instanceof VideoVo) {
                    VideoVo info = (VideoVo) list.get(position);
                    url = imagePrefix + info.getThumbnails();
                    name = info.getVideoname();
                    if (list.contains("精选视频")
                            || list.contains("用户推荐")
                            || list.contains("极致美景")
                            || list.contains("科幻惊悚")
                            || list.contains("文艺知音")
                            || list.contains("游戏视频")
                            || list.contains("性感美女")
                            || list.contains("开心娱乐")
                            || list.contains("劲爆热舞")
                            || list.contains("高清MV")) {
                        bodyHolder.tv_recording.setVisibility(View.VISIBLE);
                        bodyHolder.tv_size.setVisibility(View.GONE);
                        bodyHolder.mView.setVisibility(View.GONE);
                        recording = info.getPlaynumber() + "";
                    } else {
                        bodyHolder.tv_recording.setVisibility(View.GONE);
                        bodyHolder.tv_size.setVisibility(View.VISIBLE);
                    }
                } else if (list.get(position) instanceof GameVo) {
                    GameVo info = (GameVo) list.get(position);
                    url = imagePrefix + info.getPublicity();
                    name = info.getGamename();
                    long Conversionsize = Long.parseLong(info.getApksize());
                    size = getConversionSize(Conversionsize);
                    if (list.contains("精选游戏") || list.contains("用户游戏")) {
                        bodyHolder.tv_size.setVisibility(View.VISIBLE);
                        bodyHolder.tv_recording.setVisibility(View.GONE);
                        bodyHolder.mView.setVisibility(View.VISIBLE);
                    }
                }
                bodyHolder.name.setText(name);
                bodyHolder.tv_recording.setText(recording);
                bodyHolder.tv_size.setText("大小:" + size);

                Glide.with(bodyHolder.itemView.getContext())
                        .load(url)
                        .asBitmap()
                        // 仅缓存变换后的图片
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(bodyHolder.icon);
                break;
        }

        final int p = position;
        switch (viewType) {
            case 0:
                ((HeadViewHolder) holder).more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != listener) {
                            listener.onHomeAdapterClick(p);
                        }
                    }
                });
                ((HeadViewHolder) holder).btn_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onHomeAdapterClick(p);
                        }
                    }
                });
                break;
            default:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != listener) {
                            listener.onHomeAdapterClick(p);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (null != list) {
            if (list.get(position) instanceof String) {
                return 0;
            }
            return 1;
        }
        return super.getItemViewType(position);
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView more;
        private Button btn_more;

        private HeadViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.adapter_head_title);
            more = (TextView) itemView.findViewById(R.id.tv_more);
            btn_more = (Button) itemView.findViewById(R.id.adapter_head_more);
        }
    }

    private class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView name;
        private TextView tv_recording;
        private TextView tv_size;
        private View mView;

        private BodyViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.adapter_body_icon);
            name = (TextView) itemView.findViewById(R.id.adapter_body_name);
            tv_recording = (TextView) itemView.findViewById(R.id.tv_recording);
            tv_size = (TextView) itemView.findViewById(R.id.tv_size);
            mView = itemView.findViewById(R.id.view);
        }
    }

    public interface OnHomeAdapterClickListener {
        void onHomeAdapterClick(int position);
    }

    public void setOnHomeAdapterClickListener(OnHomeAdapterClickListener listener) {
        this.listener = listener;
    }
}
