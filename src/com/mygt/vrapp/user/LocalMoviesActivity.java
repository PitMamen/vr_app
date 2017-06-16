package com.mygt.vrapp.user;

import java.util.ArrayList;
import java.util.List;

import com.mygt.vrapp.App;
import com.mygt.vrapp.R;
import com.mygt.vrapp.player.PlayerActivity;
import com.mygt.vrapp.user.VideoSearchManager.Callback;
import com.mygt.vrapp.util.VideoThumbnailLoader;
import com.mygt.vrapp.util.VideoThumbnailLoader.ThumbnailListener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LocalMoviesActivity extends Activity implements OnClickListener,OnItemClickListener{

	private static final String TAG = "LocalMoviesActivity";
	
	private View bottom;
	private ListView listView;
	private MyMoviesAdapter adapter;
	private Button seleteAll;
	
	boolean isSelectAll = true;
	
	private ProgressDialog progressDialog;
	private VideoSearchManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_movies);
		
		findViewById(R.id.back).setOnClickListener(this);
		bottom = findViewById(R.id.bottom);
		Button preDelete = (Button) findViewById(R.id.menu1);
		Button refresh = (Button) findViewById(R.id.menu2);
		listView = (ListView) findViewById(R.id.list);
		
		((TextView)findViewById(R.id.title_txt)).setText(R.string.movies);
		seleteAll = (Button) findViewById(R.id.select_all);
		seleteAll.setOnClickListener(this);
		findViewById(R.id.delete).setOnClickListener(this);
		
		int width = getResources().getDimensionPixelSize(R.dimen.selete_all_drawable);
		Drawable seleteDrawable = getResources().getDrawable(R.drawable.check_p);
		seleteDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
		seleteAll.setCompoundDrawables(seleteDrawable, null,  null, null);//只放上边
		
		preDelete.setVisibility(View.VISIBLE);
		preDelete.setBackgroundResource(R.drawable.btn_delete);
		preDelete.setOnClickListener(this);
		
		refresh.setVisibility(View.VISIBLE);
		refresh.setBackgroundResource(R.drawable.refresh);
		refresh.setOnClickListener(this);
		
		adapter = new MyMoviesAdapter(LocalMoviesActivity.this, new ArrayList<VideoInfo>());
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(this);
		
		showProgress();
		
		manager = new VideoSearchManager();
		final long time1 = System.currentTimeMillis();
		manager.searchVideo(this, new Callback() {
			
			@Override
			public void onFinish(List<VideoInfo> list, boolean isFinish) {
				if(isFinish) {
					dismissProgress();
				}
				if(list == null) {
					return;
				}
				long time2 = System.currentTimeMillis();
				long countTime = time2 - time1;
				Log.d(TAG, "time:"+countTime);
				adapter.getList().addAll(list);
				adapter.notifyDataSetChanged(); 
				if(isFinish){
					getBitmap(adapter.getList());
				}
				/*Log.d(TAG, "size:"+list.size());
				for(VideoInfo videoInfo:list) {
					Log.d(TAG, "name:"+videoInfo.getDisplayName()+",path:"+videoInfo.getPath());
				}*/
				
			}
		});
		
		
	}
	
	private void showProgress() {
		if(progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setTitle("正在加载...");
			progressDialog.setMessage("正在加载..."); 
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setOnDismissListener(new OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					manager.setCancel();
				}
			});
		}
		progressDialog.show();
	}
	
	private void dismissProgress() {
		if(progressDialog != null) {
			progressDialog.dismiss();
		}
	} 
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.back:
				finish();
				break;
			case R.id.delete:
				final DeleteDialog dialog = new DeleteDialog();
				dialog.show(getFragmentManager(), "delete");
				dialog.setDetermineListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						List<VideoInfo> list = adapter.getList();
						for(int i=0; i<list.size(); ) {
							if(list.get(i).isSelect()) {
								list.remove(i);
							}else {
								i++;
							}
						}
						adapter.notifyDataSetChanged();
						dialog.dismiss(); 
					}
				});
				
				break;
			case R.id.select_all:
				int count = adapter.getCount();
				for(int i=0; i<count; i++) {
					VideoInfo bean = adapter.getItem(i);
					bean.setSelect(isSelectAll);
				}
				adapter.notifyDataSetChanged(); 
				if(isSelectAll) {
					seleteAll.setText(R.string.no_select_all);
				}else {
					seleteAll.setText(R.string.select_all);
				}
				isSelectAll = !isSelectAll;
				break;
			case R.id.menu1:
				if(bottom.getVisibility()==View.VISIBLE) {
					bottom.setVisibility(View.GONE);
				}else {
					bottom.setVisibility(View.VISIBLE);
				}
				adapter.notifyDataSetChanged();
				break;
			case R.id.menu2:
				showProgress(); 
				VideoThumbnailLoader.getIns().setCancel();
				adapter.getList().clear();
				manager.searchVideo(this, new Callback() {
					@Override
					public void onFinish(List<VideoInfo> list, boolean isFinish) {
						if(isFinish) {
							dismissProgress();
						}
						if(list == null) {
							return;
						}
						adapter.getList().addAll(list);
						adapter.notifyDataSetChanged();
						if(isFinish) {
							getBitmap(adapter.getList());
						}
					}
				});
				break;
			
		}
	}
	
	private void getBitmap(List<VideoInfo> list) {
		String dir = App.getImageCache(this).getAbsolutePath();
		Log.d(TAG, dir); 
		VideoThumbnailLoader.getIns().setCancel();
		for(final VideoInfo videoInfo : list) {
			Log.d(TAG, "path:"+videoInfo.getPath());
			VideoThumbnailLoader.getIns().display(dir, videoInfo.getPath(), 100, 100, new ThumbnailListener() {
				
				@Override
				public void onThumbnailLoadCompleted(Bitmap bitmap) {
					videoInfo.setBitmap(bitmap); 
					adapter.notifyDataSetChanged(); 
				}
			});
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		VideoThumbnailLoader.getIns().setCancel();
		manager.setCancel();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		VideoThumbnailLoader.getIns().setCancel();
		manager.setCancel(); 
	}
	
	class MyMoviesAdapter extends BaseAdapter {

		List<VideoInfo> list;
		Context context;
		
		MyMoviesAdapter(Context context, List<VideoInfo> list) {
			this.list = list;
			this.context = context;
		}
		
		public void setList(List<VideoInfo> list) {
			this.list = list;
			notifyDataSetChanged();
		}
		
		public List<VideoInfo> getList() {
			return list;
		}
		
		@Override
		public int getCount() {
			return list == null ? 0 : list.size();
		}

		@Override
		public VideoInfo getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.local_movies_item, parent, false);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(getItem(position).getDisplayName());
			holder.memory.setText(getItem(position).getMemory());
			holder.select.setVisibility(bottom.getVisibility());
			holder.select.setChecked(getItem(position).isSelect());

			Bitmap bitmap = getItem(position).getBitmap();
			if(bitmap != null) {
				holder.icon.setImageBitmap(bitmap);
			}else {
				holder.icon.setImageResource(R.drawable.video_default);
			}
			
			holder.select.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					getItem(position).setSelect(((CheckBox)v).isChecked());
				}
			}); 
			
			return convertView;
		}
		
		class ViewHolder {
			ImageView icon;
			TextView name, memory;
			CheckBox select;
			
			public ViewHolder(View view) {
				
				icon = (ImageView) view.findViewById(R.id.icon);
				name = (TextView) view.findViewById(R.id.name);
				memory = (TextView) view.findViewById(R.id.memory);
				select = (CheckBox) view.findViewById(R.id.select);
			}
			
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		VideoInfo videoInfo = adapter.getList().get(position);
		PlayerActivity.startThis(this, videoInfo.getPath());
	}
	
}
