package com.mygt.vrapp.view;

import com.mygt.vrapp.R;
import com.mygt.vrapp.util.UILApplication;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class VideoItemView extends RelativeLayout {
	private static String TAG = "com.mygt.vrapp";
	private Context   mContext;      
	private ImageView mIcon;         
	private TextView  mName;        	
	public VideoItemView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		mContext = context;
		init();
	}
	
	private void init() {
		inflate(mContext, R.layout.item_video, this);
		mIcon = (ImageView) findViewById(R.id.video_bg);
		mName = (TextView) findViewById(R.id.video_name);

	}
	
	public TextView getNameText(){
		return mName;
	}
	
	public void setName(String name) {
		mName.setText(name);
	}
	
	/**
	 * 
	 * @Title: setName
	 * @Description: 设置名称(关键字高亮显示)
	 * @param name
	 * @return: void
	 */
	public void setName(SpannableString name) {
		mName.setText(name);
	}
	
	/**
	 * 
	 * @Title: setImage
	 * @Description: 
	 * @param image
	 * @return: void
	 */
	public void setImage(int image) {	
		//UILApplication.imageLoader.displayImage(image,mIcon,UILApplication.videoOption);
		//Log.i(TAG,"mIcon"+mIcon.getHeight());	
		mIcon.setBackgroundResource(image);
	}
	
	public void setScaleType() {
		mIcon.setScaleType(ImageView.ScaleType.FIT_XY);
	}
	
		
}

