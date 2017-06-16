package com.mygt.vrapp.user;

import java.util.ArrayList;
import java.util.List;

import com.mygt.vrapp.R;
import com.mygt.vrapp.game.GameBean;
import com.mygt.vrapp.login.RegisterActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DownActivity extends Activity implements OnClickListener{

	private static final String TAG = "DownActivity";
	
	View bottom;
	ListView listView;
	DownAdapter adapter;
	Button seleteAll;
	private  ImageView iv_nulData;
	private RelativeLayout re_NoDataRelout;
	
	boolean isSelectAll = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_down);
		
		findViewById(R.id.back).setOnClickListener(this);
		iv_nulData = (ImageView) findViewById(R.id.im_nullData);
		re_NoDataRelout = (RelativeLayout) findViewById(R.id.re_noDataLayout);
//		bottom = findViewById(R.id.bottom);
//		Button preDelete = (Button) findViewById(R.id.menu1);
//		listView = (ListView) findViewById(R.id.list);
//
		((TextView)findViewById(R.id.title_txt)).setText(R.string.my_down);
//		seleteAll = (Button) findViewById(R.id.select_all);
//		seleteAll.setOnClickListener(this);
//		findViewById(R.id.delete).setOnClickListener(this);
//
//		int width = getResources().getDimensionPixelSize(R.dimen.selete_all_drawable);
//		Drawable seleteDrawable = getResources().getDrawable(R.drawable.check_p);
//		seleteDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
//		seleteAll.setCompoundDrawables(seleteDrawable, null,  null, null);//只放上边
//
//		preDelete.setVisibility(View.VISIBLE);
//		preDelete.setBackgroundResource(R.drawable.btn_delete);
//		preDelete.setOnClickListener(this);
//
//		List<DownBean> list = new ArrayList<DownBean>();
//		for(int i=0;i<10;i++) {
//			DownBean bean = new DownBean(null, "长城之战", "56MB", null, null, false);
//			list.add(bean);
//		}
//		adapter = new DownAdapter(this, list);
//		listView.setAdapter(adapter);

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
						List<DownBean> list = adapter.getList();
						for(int i=0; i<list.size(); ) {
							if(list.get(i).isSelect) {
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
					DownBean bean = adapter.getItem(i);
					bean.isSelect = isSelectAll;
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

		}
	}
	
	class DownAdapter extends BaseAdapter {

		List<DownBean> list;
		Context context;
		
		DownAdapter(Context context, List<DownBean> list) {
			this.list = list;
			this.context = context;
		}
		
		public void setList(List<DownBean> list) {
			this.list = list;
			notifyDataSetChanged();
		}
		
		public List<DownBean> getList() {
			return list;
		}
		
		@Override
		public int getCount() {
			return list == null ? 0 : list.size();
		}

		@Override
		public DownBean getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.down_item, parent, false);
				holder = new ViewHolder(convertView);
				Drawable pauseDrawable = getResources().getDrawable(R.drawable.btn_pause);
				int width = getResources().getDimensionPixelSize(R.dimen.pause_drawable);
				pauseDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
				holder.pause.setCompoundDrawables(null, pauseDrawable, null, null);//只放上边
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(getItem(position).name);
//			holder.icon  TODO add image
			holder.memory.setText(getItem(position).memory);
			holder.select.setVisibility(bottom.getVisibility());
			holder.select.setChecked(getItem(position).isSelect);
			holder.select.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					getItem(position).isSelect = ((CheckBox)v).isChecked();
				}
			}); 
			holder.pause.setChecked(getItem(position).isPause);
			holder.pause.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					CheckBox pause = ((CheckBox)v);
					getItem(position).isPause = pause.isChecked();
					if(getItem(position).isPause) {
						pause.setText(R.string.pause);
					}else {
						pause.setText(R.string.start);
					}
				}
			}); 
			if(getItem(position).isPause) {
				holder.pause.setText(R.string.pause);
			}else {
				holder.pause.setText(R.string.start);
			}
			holder.progress.setProgress(10);
			return convertView;
		}
		
		class ViewHolder {
			ImageView icon;
			TextView name, memory;
			CheckBox pause;
			CheckBox select;
			ProgressBar progress;
			
			public ViewHolder(View view) {
				
				icon = (ImageView) view.findViewById(R.id.icon);
				name = (TextView) view.findViewById(R.id.name);
				memory = (TextView) view.findViewById(R.id.memory);
				pause = (CheckBox) view.findViewById(R.id.pause);
				select = (CheckBox) view.findViewById(R.id.select);
				progress = (ProgressBar) view.findViewById(R.id.progress);
			}
			
		}
		
	}
	
}
