package com.mygt.vrapp.game;

import java.util.ArrayList;
import java.util.List;

import com.mygt.vrapp.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GameListFragment extends Fragment {

	ListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.game_list, container, false);
		
		listView = (ListView) view.findViewById(R.id.list);
		List<GameBean> list = new ArrayList<GameBean>();
		for(int i=0; i<10; i++) {
			GameBean bean = new GameBean(null, "精灵学院VR", "31.5MB", null, null, null);
			list.add(bean);
		}
		
		listView.setAdapter(new GameAdapter(getActivity(), list));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity(new Intent(getActivity(),GameDetailActivity.class));
				Log.d(getClass().getSimpleName(), "position:"+position);
			}
		});
		return view;
	}
	
	class GameAdapter extends BaseAdapter {

		List<GameBean> list;
		Context context;
		
		GameAdapter(Context context, List<GameBean> list) {
			this.list = list;
			this.context = context;
		}
		
		@Override
		public int getCount() {
			return list == null ? 0 : list.size();
		}

		@Override
		public GameBean getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.game_item, parent, false);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(getItem(position).name);
//			holder.icon  TODO add image
			holder.memory.setText(getItem(position).memory);
			
			return convertView;
		}
		
		class ViewHolder {
			ImageView icon;
			TextView name,memory;
			Button down;
			
			public ViewHolder(View view) {
				
				icon = (ImageView) view.findViewById(R.id.icon);
				name = (TextView) view.findViewById(R.id.name);
				memory = (TextView) view.findViewById(R.id.memory);
				down = (Button) view.findViewById(R.id.down);
			}
			
		}
		
	}
	
}
