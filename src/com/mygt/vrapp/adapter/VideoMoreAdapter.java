package com.mygt.vrapp.adapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.bumptech.glide.Glide;
import com.mygt.vrapp.R;
import com.mygt.vrapp.api.bean.IndexInfo;
import com.mygt.vrapp.api.bean.VideoVo;
import com.mygt.vrapp.interfaces.onClickCustomListener;
import com.mygt.vrapp.view.VideoItemView;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
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

public class VideoMoreAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private onClickCustomListener mCallback;
	private String TAG = "com.mygt.vrapp";

	private  List<VideoVo> mVideoVos;

	public VideoMoreAdapter(Context context,List<VideoVo> mVideoVos
			,onClickCustomListener callback) {
		mContext = context;
		mCallback = callback;
		this.mVideoVos = mVideoVos;
		mInflater = LayoutInflater.from(context);
		
	}
	
	@Override
	public int getCount() {
		return mVideoVos.size();
	}

	@Override
	public Object getItem(int position) {
		return mVideoVos.get(position);
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
			convertView = mInflater.inflate(R.layout.item_video_classify, parent,false);
			holder = new ViewHolder();
            holder.item = (ImageView) convertView.findViewById(R.id.iv_image);
			holder.videoName = (TextView) convertView.findViewById(R.id.tv_videoName);
            convertView.setTag(holder);
		}
		else {
            holder = (ViewHolder) convertView.getTag();
		}
		VideoVo videoVo = mVideoVos.get(position);

		Glide.with(mContext).load("http://192.168.2.46:8088/"+videoVo.getThumbnails()).centerCrop().into(holder.item);
		holder.videoName.setText(videoVo.getVideoname());
//		final View view = convertView;
//        final int p = position;
//        final int item = holder.item.getId();
//        holder.item.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            	mCallback.onClick(view, parent, p, item);
//            }
//        });
		return convertView;
	}

	public void notifySetChanged() { 
	    super.notifyDataSetChanged(); 
	}

	static class ViewHolder {
		ImageView item;
		TextView videoName;
    }
	
}
