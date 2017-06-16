package com.mygt.vrapp.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygt.vrapp.R;
import com.mygt.vrapp.api.bean.VideoVo;
import com.mygt.vrapp.database.VRDbConstant;
import com.mygt.vrapp.database.VRDbManager;
import com.mygt.vrapp.database.VRDbVideo;
import com.mygt.vrapp.user.DownActivity.DownAdapter;
import com.mygt.vrapp.user.DownActivity.DownAdapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class HistoryActivity extends Activity implements OnClickListener {

    private static final String TAG = "HistoryActivity";

    View bottom;
    ListView listView;
    HistoryAdapter adapter;
    Button seleteAll, preDelete;
    List<VRDbVideo> mDbVideoList;
    private TextView title_txt;

    boolean isSelectAll = true;
    private VRDbVideo mVRDbVideo;
    private RelativeLayout re_noDataLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mDbVideoList = VRDbManager.getInstance().queryHistory();
        Log.d(TAG, "mDbVideoList==: " + mDbVideoList);


        initView();

        initDrawable();


        preDelete.setBackgroundResource(R.drawable.btn_delete);
        initListener();

        if (mDbVideoList.size()>0) {
            preDelete.setVisibility(View.VISIBLE);
            re_noDataLayout.setVisibility(View.GONE);
            adapter = new HistoryAdapter(this, mDbVideoList);
            listView.setAdapter(adapter);
        } else {
            re_noDataLayout.setVisibility(View.VISIBLE);
            preDelete.setVisibility(View.GONE);

        }

    }

    private void initListener() {
        findViewById(R.id.back).setOnClickListener(this);
        seleteAll.setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);

        preDelete.setOnClickListener(this);
    }

    private void initDrawable() {
        int width = getResources().getDimensionPixelSize(R.dimen.selete_all_drawable);
        Drawable seleteDrawable = getResources().getDrawable(R.drawable.check_p);
        seleteDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        seleteAll.setCompoundDrawables(seleteDrawable, null, null, null);//只放上边
    }

    private void initView() {
        re_noDataLayout = (RelativeLayout) findViewById(R.id.re_noDataLayout);
        bottom = findViewById(R.id.bottom);
        preDelete = (Button) findViewById(R.id.menu1);
        listView = (ListView) findViewById(R.id.list);

        title_txt = (TextView) findViewById(R.id.title_txt);
        title_txt.setText(R.string.history);
        seleteAll = (Button) findViewById(R.id.select_all);
    }


    // 查询历史记录
//    private void queryHistory() {
//        Subscription subscription =
//                Observable
//                        .fromCallable(new Callable<List<VRDbVideo>>() {
//                            @Override
//                            public List<VRDbVideo> call() throws Exception {
//                                return VRDbManager.getInstance().queryHistory();
//                            }
//                        })
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<List<VRDbVideo>>() {
//                            @Override
//                            public void onCompleted() {
//                                Log.d(TAG, "onCompleted: ");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.d(TAG, "onError: " + e.toString());
//                            }
//
//                            @Override
//                            public void onNext(List<VRDbVideo> vrDbVideos) {
//                                System.out.println("执行！");
//                                mDbVideoList = vrDbVideos;
//                                Log.d(TAG, "mDbVideoList=: " + mDbVideoList);
//                                if (mDbVideoList.size()>0){
//                                    list.clear();
//                                    list.addAll(mDbVideoList);
//                                }
//
//                                adapter.notifyDataSetChanged();
//                            }
//                        });
//
//        //取消
////        subscription.unsubscribe();
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.delete:
                final DeleteDialog dialog = new DeleteDialog();
                dialog.show(getFragmentManager(), "delete");
                dialog.setDetermineListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mDbVideoList = adapter.getList();
                        for (int i = 0; i < mDbVideoList.size(); ) {
                            if (mDbVideoList.get(i).isSelect) {
                                VRDbVideo V = mDbVideoList.get(i);
                                VRDbManager.getInstance().deleteHistory(V);
                                mDbVideoList.remove(i);
                                if (mDbVideoList.size()==0){
                                    preDelete.setVisibility(View.GONE);
                                    re_noDataLayout.setVisibility(View.VISIBLE);
                                }
                            } else {
                                i++;
                            }
                        }
                        adapter.notifyDataSetChanged();
                         bottom.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.select_all:
                int count = adapter.getCount();
                for (int i = 0; i < count; i++) {
                    mVRDbVideo = adapter.getItem(i);
                    mVRDbVideo.isSelect = isSelectAll;
                }
                adapter.notifyDataSetChanged();
                if (isSelectAll) {
                    seleteAll.setText(R.string.no_select_all);
                } else {
                    seleteAll.setText(R.string.select_all);
                }
                isSelectAll = !isSelectAll;
                break;
            case R.id.menu1:

                if (bottom.getVisibility() == View.VISIBLE) {
                    bottom.setVisibility(View.GONE);
                } else {
                    bottom.setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
                break;

        }
    }


    class HistoryAdapter extends BaseAdapter {

        List<VRDbVideo> list;
        Context context;

        HistoryAdapter(Context context, List<VRDbVideo> list) {
            this.list = list;
            this.context = context;
        }

        public void setList(List<VRDbVideo> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        public List<VRDbVideo> getList() {
            return list;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public VRDbVideo getItem(int position) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(getItem(position).getVideoname());
            String url = getItem(position).getThumbnails();
            Log.d(TAG, "url3==: " + url);
            Glide.with(context)
                    .load(getItem(position).getThumbnails())
                    .asBitmap()
                    // 仅缓存变换后的图片
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.icon);
            holder.select.setVisibility(bottom.getVisibility());
            holder.select.setChecked(getItem(position).isSelect);
            holder.select.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    getItem(position).isSelect = ((CheckBox) v).isChecked();
                }
            });

            return convertView;
        }

        class ViewHolder {
            ImageView icon;
            TextView name, progress;
            CheckBox select;

            public ViewHolder(View view) {

                icon = (ImageView) view.findViewById(R.id.icon);
                name = (TextView) view.findViewById(R.id.name);
//				progress = (TextView) view.findViewById(R.id.progress);
                select = (CheckBox) view.findViewById(R.id.select);
            }

        }

    }

}
