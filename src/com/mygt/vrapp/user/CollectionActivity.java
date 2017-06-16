package com.mygt.vrapp.user;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mygt.vrapp.R;
import com.mygt.vrapp.database.VRDbManager;
import com.mygt.vrapp.database.VRDbVideo;
import com.mygt.vrapp.user.DownActivity.DownAdapter;
import com.mygt.vrapp.user.DownActivity.DownAdapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CollectionActivity extends Activity implements OnClickListener {

    private static final String TAG = "CollectionActivity";

    View bottom;
    ListView listView;
    CollectionAdapter adapter;
    Button seleteAll;
    private TextView tv_nullCollect, title_txt;
    private ImageView iv_nullData;
    private RelativeLayout re_layoutNoData;
    private Button preDelete;
    boolean isSelectAll = true;
    List<VRDbVideo> mDbVideoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        mDbVideoList = VRDbManager.getInstance().queryCollect();
        Log.d(TAG, "mDbVideoList==: " + mDbVideoList);


        initView();

        initDrawable();

        preDelete.setVisibility(View.GONE);
        preDelete.setBackgroundResource(R.drawable.btn_delete);
        initListenner();

        if (mDbVideoList.size() > 0) {
            re_layoutNoData.setVisibility(View.GONE);
            adapter = new CollectionAdapter(this, mDbVideoList);
            listView.setAdapter(adapter);
        } else {
            re_layoutNoData.setVisibility(View.VISIBLE);
        }


    }

    private void initListenner() {
        findViewById(R.id.delete).setOnClickListener(this);
        preDelete.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        seleteAll.setOnClickListener(this);
    }

    private void initView() {
        re_layoutNoData = (RelativeLayout) findViewById(R.id.re_noDataLayout);
        bottom = findViewById(R.id.bottom);
        preDelete = (Button) findViewById(R.id.menu1);
        listView = (ListView) findViewById(R.id.list);
//        iv_nullData = (ImageView) findViewById(R.id.im_nullData);
//        tv_nullCollect = (TextView) findViewById(R.id.tv_nullCollect);

        title_txt = (TextView) findViewById(R.id.title_txt);
        title_txt.setText(R.string.my_collection);
        seleteAll = (Button) findViewById(R.id.select_all);
    }

    private void initDrawable() {
        int width = getResources().getDimensionPixelSize(R.dimen.selete_all_drawable);
        Drawable seleteDrawable = getResources().getDrawable(R.drawable.check_p);
        seleteDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        seleteAll.setCompoundDrawables(seleteDrawable, null, null, null);//只放上边
    }

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
                        List<VRDbVideo> list = adapter.getList();
                        for (int i = 0; i < list.size(); ) {
                            if (list.get(i).isSelect) {
                                list.remove(i);
                            } else {
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
                for (int i = 0; i < count; i++) {
                    VRDbVideo bean = adapter.getItem(i);
                    bean.isSelect = isSelectAll;
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

    class CollectionAdapter extends BaseAdapter {

        List<VRDbVideo> list;
        Context context;

        CollectionAdapter(Context context, List<VRDbVideo> list) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.collection_item, parent, false);
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
//			holder.icon  TODO add image
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
            TextView name;
            CheckBox select;

            public ViewHolder(View view) {

                icon = (ImageView) view.findViewById(R.id.icon);
                name = (TextView) view.findViewById(R.id.name);
                select = (CheckBox) view.findViewById(R.id.select);
            }

        }

    }

}
