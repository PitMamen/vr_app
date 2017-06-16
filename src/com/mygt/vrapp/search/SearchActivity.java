package com.mygt.vrapp.search;

import java.util.ArrayList;
import java.util.List;

import com.mygt.vrapp.App;
import com.mygt.vrapp.R;
import com.mygt.vrapp.db.bean.SearchHistory;
import com.mygt.vrapp.db.dao.SearchHistoryDao;
import com.mygt.vrapp.db.dao.SearchHistoryDao.Properties;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends Activity implements OnClickListener{

	private String TAG = getClass().getSimpleName();
	
	private TextView clearHistory;
	private TextView btnSearch;
	private ListView listView; 
	private EditText input;
	
	private SearchHistoryDao searchDao;
	
	private SearchAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_search);
		
		listView = (ListView) findViewById(R.id.list);
		clearHistory = (TextView) findViewById(R.id.clear_history);
		btnSearch = (TextView) findViewById(R.id.btn_search);
		input = (EditText) findViewById(R.id.input);
		
		findViewById(R.id.clear_input).setOnClickListener(this);
		btnSearch.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
		
		searchDao = App.getDaoSession().getSearchHistoryDao();
		
		initTabDrawable();
		
		input.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				Log.d(TAG, "onTextChanged,"+s+",start:"+start+",before:"+before+",count:"+count);
				if(count == 0) {
					btnSearch.setText(R.string.cancel);
				}else {
					btnSearch.setText(R.string.search);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//				Log.d(TAG, "beforeTextChanged,"+s+",start:"+start+",after:"+after+",count:"+count);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
//				Log.d(TAG, "afterTextChanged,"+s.toString());
			}
		});
		
		List<SearchHistory> list = getHistoryList();
		
		if(list == null) {
			list = new ArrayList<>();
		}
		adapter = new SearchAdapter(list);
		listView.setAdapter(adapter);
		
	}
	
	private void initTabDrawable() {
		int width = getResources().getDimensionPixelSize(R.dimen.search_delete);
		Drawable deleteDrawable = getResources().getDrawable(R.drawable.btn_delete);
		deleteDrawable.setBounds(0, 0, width, width);// 第一0是距左边距离，第二0是距上边距离，40分别是长宽
		clearHistory.setCompoundDrawables(deleteDrawable, null, null, null);// 只放上边
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear_input:
			input.setText("");
			break;
		case R.id.clear_history:
			clearHistory();
			break; 
		case R.id.btn_search:
			if(TextUtils.isEmpty(input.getText().toString())) {
				finish();
			}else {
				search();
			}
			break;

		default:
			break;
		}
		
	}

	private List<SearchHistory> getHistoryList() {
		return searchDao.queryBuilder().orderDesc(Properties.Id).limit(10).list();
	}
	
	private void clearHistory() {
		adapter.clear();
		searchDao.deleteAll(); 
	}

	private void search() {
		save();
	}
	
	private void save() {
		String inputTxt = input.getText().toString();
		
		SearchHistory search = new SearchHistory(null, inputTxt);
		adapter.addItem(search);
		
		List<SearchHistory> list = searchDao.queryBuilder().where(Properties.Content.eq(inputTxt)).list();
		if(list != null && list.size() > 0) {
			searchDao.deleteInTx(list);
		}
		
		searchDao.insertOrReplace(search);
	}
	
	class SearchAdapter extends BaseAdapter {
		
		List<SearchHistory> list;
		
		SearchAdapter(List<SearchHistory> list) {
			this.list = list;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView == null) {
				convertView = LayoutInflater.from(SearchActivity.this).inflate(R.layout.search_item, parent,false);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(getItem(position).getContent());
			
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			return list.get(position).getId();
		}
		
		private void clear() {
			list.clear();
			notifyDataSetChanged();
		}
		
		@Override
		public SearchHistory getItem(int position) {
			return list.get(position); 
		}
		
		public void addItem(SearchHistory item) {
			list.add(item);
			notifyDataSetChanged(); 
		}
		
		@Override
		public int getCount() {
			return list != null ? list.size() : 0;
		}
		
		class ViewHolder {
			TextView name;
			
			ViewHolder (View view) {
				name = (TextView) view.findViewById(R.id.name);
			}
			
		}
		
	};
	
}
