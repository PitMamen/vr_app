package com.mygt.vrapp.video;  

import java.util.List;

import com.mygt.vrapp.ActivityDetailVideo;
import com.mygt.vrapp.ActivityMore;
import com.mygt.vrapp.R;
import com.mygt.vrapp.adapter.VideoClassifyAdapter;
import com.mygt.vrapp.interfaces.onClickCustomListener;
import com.mygt.vrapp.view.CycleDot;
import com.mygt.vrapp.view.ImagePager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;  
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.View.OnClickListener;
import android.view.ViewGroup;  
import android.widget.ListView;
  
public class VideoClassifyFragment1 extends Fragment  implements onClickCustomListener
{  
	private static String TAG = "com.mygt.vrapp";
	private Context mContext;
	private ListView mClassifyListView;
	private VideoClassifyAdapter mClassifyAdapter;
	
	@Override
	public void onAttach(Activity activity) {
		this.mContext = activity;
		super.onAttach(activity);
	}
	
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {  
        View view =  inflater.inflate(R.layout.video_fragment1_layout, container, false);  
        Log.i(TAG,"VideoClassifyFragment1 onCreateView");
        //mClickbtn = (Button) view.findViewById(R.id.id_click_btn);  
        //mClassifyLayout = (RelativeLayout) view.findViewById(R.id.classify_layout);
        mClassifyListView = (ListView)view.findViewById(R.id.video_classify_listview);
        /*mClickbtn.setOnClickListener(new OnClickListener()  {  
        		@Override  
        		public void onClick(View v)  
        		{  
        			Log.i("","onCreateView");
        			Intent intent = new Intent();  
			        intent.setClass(mContext, ActivityDetailVideo.class);
			        startActivity(intent); 
        		}  
        	});  */
        
		initView();
        return view;
    }  
  
    private void initView(){
    	if (null == mClassifyAdapter) {
    		//mClassifyAdapter = new VideoClassifyAdapter(mContext, mClassifyListView, image);
//    		mClassifyAdapter = new VideoClassifyAdapter(mContext, mClassifyListView,this);
//			mClassifyListView.setAdapter(mClassifyAdapter);
		}
//    	mClassifyAdapter.notifyDataSetChanged();
    }

	@Override
	public void onClick(View item, View widget, int position, int which) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(); 
		switch (which) {
//	        case R.id.more_btn:
//	        	Log.i(TAG,"more_btn position "+position);
//
//		        intent.setClass(mContext, ActivityMore.class);
//		        startActivity(intent);
//	            break;
//	        case R.id.id_item0:
//	        	Log.i(TAG,"id_item0 position "+position);
//		        intent.setClass(mContext, ActivityDetailVideo.class);
//		        startActivity(intent);
//	            break;
//	        case R.id.id_item1:
//	        	Log.i(TAG,"id_item1 position "+position);
//		        intent.setClass(mContext, ActivityDetailVideo.class);
//		        startActivity(intent);
//	            break;
//	        case R.id.id_item2:
//	        	Log.i(TAG,"id_item2 position "+position);
//	        	intent.setClass(mContext, ActivityDetailVideo.class);
//	        	startActivity(intent);
//	            break;
//	        case R.id.id_item3:
//	        	Log.i(TAG,"id_item3 position "+position);
//	        	intent.setClass(mContext, ActivityDetailVideo.class);
//	        	startActivity(intent);
//	            break;
	        default:
	            break;
	        }
	}
}  

