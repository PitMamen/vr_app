package com.mygt.vrapp.adapter;

import java.util.List;

import com.mygt.vrapp.R;
import com.mygt.vrapp.api.bean.VideoVo;
import com.mygt.vrapp.interfaces.onClickCustomListener;
import com.mygt.vrapp.view.VideoItemView;
import com.squareup.picasso.Picasso;

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

public class VideoClassifyAdapter extends BaseAdapter {

    private Context mContext;
    private VideoItemView[] items;
    private LayoutInflater mInflater;
    private List<String> mDataList;
    private onClickCustomListener mCallback;
    private String TAG = "com.mygt.vrapp";

    private List<VideoVo> mListVideo;


    /*public VideoClassifyAdapter(Context context, ListView gridview, List<String> dataList) {
        mContext = context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDataList = dataList;
    }*/
    public VideoClassifyAdapter(Context context, List<VideoVo> mList
            , onClickCustomListener callback) {
        mContext = context;
        mCallback = callback;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //mDataList = new List<String>;
        //mDataList.add(image);
    }

    @Override
    public int getCount() {
        //return mDataList.size();
        return mListVideo != null ? mListVideo.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        //String info = mDataList.get(position);
        //String info = mDataList.get(0);
        //String image = mContext.getResources().getString(R.drawable.video_default);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_video_classify, null);
            holder = new ViewHolder();
//            holder.more_text = (TextView) convertView.findViewById(R.id.tv_more);
//            holder.nameTitle = (TextView) convertView.findViewById(R.id.video_classify_txt);
//            holder.more = (Button) convertView.findViewById(R.id.more_btn);

//            holder.imageview = (ImageView) convertView.findViewWithTag(R.id.iv_0);
//            holder.tv_imageName = (TextView) convertView.findViewById(R.id.tv_imageName0);

            holder.items = new VideoItemView[mListVideo.size()];
//            holder.items[0] = (VideoItemView) convertView.findViewById(R.id.id_item0);
//            holder.items[1] = (VideoItemView) convertView.findViewById(R.id.id_item1);
//            holder.items[2] = (VideoItemView) convertView.findViewById(R.id.id_item2);
//            holder.items[3] = (VideoItemView) convertView.findViewById(R.id.id_item3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        VideoVo videoVo = mListVideo.get(position);
//        Picasso.with(mContext).load(videoVo.getThumbnails()).into(holder.imageview);
//        holder.tv_imageName.setText(videoVo.getVideoname());


        holder.items = new VideoItemView[mListVideo.size()];
		holder.items[0].setImage(videoVo.getCollectnumber());
        holder.items[0].setName(videoVo.getVideoname());
		holder.items[1].setImage(videoVo.getCollectnumber());
		holder.items[1].setName(videoVo.getVideoname());
		holder.items[2].setImage(videoVo.getCollectnumber());
        holder.items[2].setName(videoVo.getVideoname());
//
//		final View view = convertView;
//        final int p = position;
//        final int more = holder.more.getId();
//        holder.more.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            	mCallback.onClick(view, parent, p, more);
//            }
//        });
//
//        final int item0 = holder.items[0].getId();
//        holder.items[0].setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            	mCallback.onClick(view, parent, p, item0);
//            }
//        });
//
//        final int item1 = holder.items[1].getId();
//        holder.items[1].setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            	mCallback.onClick(view, parent, p, item1);
//            }
//        });
//
//
//        final int item2 = holder.items[2].getId();
//        holder.items[2].setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            	mCallback.onClick(view, parent, p, item2);
//            }
//        });
//
//
//        final int item3 = holder.items[3].getId();
//        holder.items[3].setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            	mCallback.onClick(view, parent, p, item3);
//            }
//        });

        return convertView;
    }

    public void notifySetChanged() {
        super.notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView nameTitle;
        Button more;
        TextView more_text;
		VideoItemView[] items;
    }

}
