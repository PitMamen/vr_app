package com.mygt.vrapp;

import java.util.ArrayList;
import java.util.List;

import com.mygt.vrapp.search.SearchActivity;
import com.mygt.vrapp.user.CollectionActivity;
import com.mygt.vrapp.user.DownActivity;
import com.mygt.vrapp.user.HistoryActivity;
import com.mygt.vrapp.user.LocalMoviesActivity;
import com.mygt.vrapp.util.FileUtils;
import com.mygt.vrapp.util.UILApplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.mygt.vrapp.util.FileUtils.isNetworkAvailable;

public class MainActivity extends FragmentActivity implements OnGestureListener, OnClickListener {
    private String TAG = getClass().getSimpleName();
    final int DISTANT = 50; // 定义手势两点之间的最小距离
    final int NUMBER = 4; // fragment个数
    private Fragment[] fragments;
    private LinearLayout[] linearLayouts;
    private ImageView[] imageViews;
    private TextView[] textViews;
    private TextView titleView;
    private RadioGroup radioGroup;
    private RadioButton selectBtn;
    private RadioButton videoBtn;
    private RadioButton gameBtn;
    private RadioButton userBtn;

    private ViewPager viewPager;
    private FragmentPagerAdapter fpAdapter;
    private List<Fragment> listData;
    private RelativeLayout mLoadingView;

    private View titleBar;
    private View menu;

    private PopupWindow mPopupWindow;

    /**
     * 定义手势检测实例
     */
    public static GestureDetector detector;
    private FrameLayout Available_VISIBLE;
    /**
     * 做标签，记录当前是哪个fragment
     */
    public int MARK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        boolean Available = isNetworkAvailable(this);
        Log.d(TAG, "is==: "+Available);
        Available_VISIBLE =(FrameLayout) findViewById(R.id.frame_gone);
        if (Available==false){
            Available_VISIBLE.setVisibility(View.VISIBLE);
        }else {
            UILApplication.InitService(this.getApplicationContext());
            initViews();
            Log.i("onCreate", "onCreate1");
            // 分别实例化和初始化fragement、lineatlayout、textview
            // setfragment();
            setViewPager();
            Log.i("onCreate", "onCreate2");
            setlinearLayouts();
            Log.i("onCreate", "onCreate3");
            settextview(0);
            setBottomView(0);
            setListeners();
            // 创建手势检测器
            detector = new GestureDetector(this, this);
        }


        // detector=new GestureDetector(this);


    }




    @Override
    protected void onDestroy() {
        UILApplication.UnInitService();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initViews() {

        mLoadingView = (RelativeLayout) findViewById(R.id.loading_view);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        titleView = (TextView) findViewById(R.id.id_title_txt);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        selectBtn = (RadioButton) findViewById(R.id.selectBtn);
        videoBtn = (RadioButton) findViewById(R.id.videoBtn);
        gameBtn = (RadioButton) findViewById(R.id.gameBtn);
        userBtn = (RadioButton) findViewById(R.id.userBtn);
        titleBar = findViewById(R.id.title);
        menu = findViewById(R.id.more_menu);

        menu.setOnClickListener(this);
        findViewById(R.id.id_title_search_btn).setOnClickListener(this);

        initTabDrawable();

		/*
         * linearLayouts=new LinearLayout[NUMBER];
		 * linearLayouts[0]=(LinearLayout)findViewById(R.id.select_layout);
		 * linearLayouts[1]=(LinearLayout)findViewById(R.id.video_layout);
		 * linearLayouts[2]=(LinearLayout)findViewById(R.id.game_layout);
		 * linearLayouts[3]=(LinearLayout)findViewById(R.id.user_layout);
		 * textViews=new TextView[NUMBER];
		 * textViews[0]=(TextView)findViewById(R.id.select_txt);
		 * textViews[1]=(TextView)findViewById(R.id.video_txt);
		 * textViews[2]=(TextView)findViewById(R.id.game_txt);
		 * textViews[3]=(TextView)findViewById(R.id.user_txt); imageViews=new
		 * ImageView[NUMBER];
		 * imageViews[0]=(ImageView)findViewById(R.id.select_img);
		 * imageViews[1]=(ImageView)findViewById(R.id.video_img);
		 * imageViews[2]=(ImageView)findViewById(R.id.game_img);
		 * imageViews[3]=(ImageView)findViewById(R.id.user_img);
		 */

        mLoadingView.setVisibility(View.VISIBLE);
    }
    /*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */

    /**
     * 初始化fragment
     *//*
						 * public void setfragment(){ fragments=new
						 * Fragment[NUMBER]; //SelectFragment selectFragment =
						 * new SelectFragment(); //
						 * getSupportFragmentManager().beginTransaction().add(
						 * arg0, arg1) fragments[0]=getSupportFragmentManager().
						 * findFragmentById(R.id.selectfragment);
						 * fragments[1]=getSupportFragmentManager().
						 * findFragmentById(R.id.videofragment);
						 * fragments[2]=getSupportFragmentManager().
						 * findFragmentById(R.id.gamefragment);
						 * fragments[3]=getSupportFragmentManager().
						 * findFragmentById(R.id.userfragment);
						 * getSupportFragmentManager().beginTransaction().hide(
						 * fragments[0]).hide(fragments[1]).hide(fragments[2])
						 * .hide(fragments[3]).show(fragments[0]).commit(); }
						 */
    private void initTabDrawable() {
        int width = getResources().getDimensionPixelSize(R.dimen.main_tab_drawable);
        Drawable selectDrawable = getResources().getDrawable(R.drawable.bottom_select_selector);
        selectDrawable.setBounds(0, 0, width, width);// 第一0是距左边距离，第二0是距上边距离，40分别是长宽
        selectBtn.setCompoundDrawables(null, selectDrawable, null, null);// 只放上边
        Drawable videoDrawable = getResources().getDrawable(R.drawable.bottom_video_selector);
        videoDrawable.setBounds(0, 0, width, width);// 第一0是距左边距离，第二0是距上边距离，40分别是长宽
        videoBtn.setCompoundDrawables(null, videoDrawable, null, null);// 只放上边
        Drawable gameDrawable = getResources().getDrawable(R.drawable.bottom_game_selector);
        gameDrawable.setBounds(0, 0, width, width);// 第一0是距左边距离，第二0是距上边距离，40分别是长宽
        gameBtn.setCompoundDrawables(null, gameDrawable, null, null);// 只放上边
        Drawable userDrawable = getResources().getDrawable(R.drawable.bottom_user_selector);
        userDrawable.setBounds(0, 0, width, width);// 第一0是距左边距离，第二0是距上边距离，40分别是长宽
        userBtn.setCompoundDrawables(null, userDrawable, null, null);// 只放上边
    }

    private void setViewPager() {
        // 初始化数据

        listData = new ArrayList<Fragment>();
        SelectFragment2 fragmentSelect = new SelectFragment2();
        VideoFragment fragmentVideo = new VideoFragment();
        GameFragment fragmentGame = new GameFragment();
        UserFragment fragmentUser = new UserFragment();
        // 四个布局加入列表
        listData.add(fragmentSelect);
        listData.add(fragmentVideo);
        listData.add(fragmentGame);
        listData.add(fragmentUser);
        // ViewPager相当于一组件容器 实现页面切换
        fpAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return listData.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return listData.get(arg0);
            }
        };
        // 设置适配器

        viewPager.setAdapter(fpAdapter);
        viewPager.setOffscreenPageLimit(fpAdapter.getCount() - 1);
        viewPager.setCurrentItem(0, true);
        mLoadingView.setVisibility(View.GONE);
    }

    /**
     * 初始化linerlayout
     */
    public void setlinearLayouts() {

        // linearLayouts[0].setBackgroundResource(R.drawable.lay_select_bg);
    }

    /**
     * 初始化textview
     */
    public void settextview(int pos) {
        if (pos == 0) {
            titleView.setText(getResources().getText(R.string.select));
        } else if (pos == 1) {
            titleView.setText(getResources().getText(R.string.video));
        } else if (pos == 2) {
            titleView.setText(getResources().getText(R.string.game));
        } else if (pos == 3) {
            titleView.setText(getResources().getText(R.string.user));
        }
		/*
		 * textViews=new TextView[3];
		 * textViews[0]=(TextView)findViewById(R.id.fratext1);
		 * textViews[1]=(TextView)findViewById(R.id.fratext2);
		 * textViews[2]=(TextView)findViewById(R.id.fratext3);
		 * textViews[0].setTextColor(getResources().getColor(R.color.
		 * lightseagreen));
		 */
    }

    /**
     * 设置选择项
     */
    public void setBottomView(int pos) {
        if (pos == 0) {
            selectBtn.setChecked(true);
            // radioGroup.check(R.id.selectBtn);
        }
        if (pos == 1) {
            videoBtn.setChecked(true);
            // radioGroup.check(R.id.videoBtn);
        }
        if (pos == 2) {
            gameBtn.setChecked(true);
            // radioGroup.check(R.id.gameBtn);
        }
        if (pos == 3) {
            userBtn.setChecked(true);
            // radioGroup.check(R.id.userBtn);
        }

		/*
		 * imageViews[0].set;
		 * imageViews[0].setBackgroundResource(R.drawable.select_n);
		 * imageViews[1].setBackgroundResource(R.drawable.video_n);
		 * imageViews[2].setBackgroundResource(R.drawable.game_n);
		 * imageViews[3].setBackgroundResource(R.drawable.my_n); for(int i =
		 * 0;i<NUMBER;i++){
		 * textViews[i].setTextColor(getResources().getColor(R.color.black)); }
		 * 
		 * if(pos == 0){
		 * imageViews[0].setBackgroundResource(R.drawable.select_d);
		 * textViews[0].setTextColor(getResources().getColor(R.color.bottom_text
		 * )); } else if(pos == 1){
		 * imageViews[1].setBackgroundResource(R.drawable.video_d);
		 * textViews[1].setTextColor(getResources().getColor(R.color.bottom_text
		 * )); } else if(pos == 2){
		 * imageViews[2].setBackgroundResource(R.drawable.game_d);
		 * textViews[2].setTextColor(getResources().getColor(R.color.bottom_text
		 * )); } else if(pos == 3){
		 * imageViews[3].setBackgroundResource(R.drawable.my_d);
		 * textViews[3].setTextColor(getResources().getColor(R.color.bottom_text
		 * )); }
		 */
		/*
		 * textViews=new TextView[3];
		 * textViews[0]=(TextView)findViewById(R.id.fratext1);
		 * textViews[1]=(TextView)findViewById(R.id.fratext2);
		 * textViews[2]=(TextView)findViewById(R.id.fratext3);
		 * textViews[0].setTextColor(getResources().getColor(R.color.
		 * lightseagreen));
		 */
    }

    private void setListeners() {
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {

            /**
             * This method will be invoked when a new page becomes selected.
             */

            @Override
            public void onPageSelected(int position) {
                settextview(position);
                setBottomView(position);
                if (position == 3) {
                    titleBar.setVisibility(View.GONE);
                } else {
                    titleBar.setVisibility(View.VISIBLE);
                }
                if (position == 0) {
                    menu.setVisibility(View.VISIBLE);
                } else {
                    menu.setVisibility(View.GONE);
                }
            }

            /**
             * This method will be invoked when the current page is scrolled,
             * either as part of a programmatically initiated smooth scroll or a
             * user initiated touch scroll.
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //

            }

            /**
             * Called when the scroll state changes.
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                //

            }
        });

        // 给RadioGroup设置事件监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                radiobOnclick(checkedId);
            }
        });

    }

    /**
     * 点击底部linerlayout实现切换fragment的效果
     */
    public void radiobOnclick(int id) {
        // resetlaybg();//每次点击都重置linearLayouts的背景、textViews字体颜色
        switch (id) {
            case R.id.selectBtn:
			/*
			 * getSupportFragmentManager().beginTransaction().hide(fragments[0])
			 * .hide(fragments[1]).hide(fragments[2])
			 * .hide(fragments[3]).show(fragments[0]).commit();
			 * //linearLayouts[0].setBackgroundResource(R.drawable.lay_select_bg
			 * ); //textViews[0].setTextColor(getResources().getColor(R.color.
			 * lightseagreen));
			 */
                titleView.setText(getResources().getText(R.string.select));
                viewPager.setCurrentItem(0, true);
                MARK = 0;
                break;

            case R.id.videoBtn:
			/*
			 * getSupportFragmentManager().beginTransaction().hide(fragments[0])
			 * .hide(fragments[1]).hide(fragments[2])
			 * .hide(fragments[3]).show(fragments[1]).commit();
			 * //linearLayouts[1].setBackgroundResource(R.drawable.lay_select_bg
			 * ); //textViews[1].setTextColor(getResources().getColor(R.color.
			 * lightseagreen));
			 */
                titleView.setText(getResources().getText(R.string.video));
                viewPager.setCurrentItem(1, true);
                MARK = 1;
                break;

            case R.id.gameBtn:
			/*
			 * getSupportFragmentManager().beginTransaction().hide(fragments[0])
			 * .hide(fragments[1]).hide(fragments[2])
			 * .hide(fragments[3]).show(fragments[2]).commit();
			 * //linearLayouts[2].setBackgroundResource(R.drawable.lay_select_bg
			 * ); //textViews[2].setTextColor(getResources().getColor(R.color.
			 * lightseagreen));
			 */
                titleView.setText(getResources().getText(R.string.game));
                viewPager.setCurrentItem(2, true);
                MARK = 2;
                break;

            case R.id.userBtn:
			/*
			 * getSupportFragmentManager().beginTransaction().hide(fragments[0])
			 * .hide(fragments[1]).hide(fragments[2])
			 * .hide(fragments[3]).show(fragments[3]).commit();
			 * //linearLayouts[3].setBackgroundResource(R.drawable.lay_select_bg
			 * ); //textViews[3].setTextColor(getResources().getColor(R.color.
			 * lightseagreen));
			 */
                titleView.setText(getResources().getText(R.string.user));
                viewPager.setCurrentItem(3, true);

                MARK = 3;
                break;

            default:
                break;
        }
    }

    /**
     * 重置linearLayouts、textViews
     */
    public void resetlaybg() {
        for (int i = 0; i < 3; i++) {
			/*
			 * linearLayouts[i].setBackgroundResource(R.drawable.tabfootbg);
			 * textViews[i].setTextColor(getResources().getColor(R.color.black))
			 * ;
			 */
        }

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        // 将该Activity上触碰事件交给GestureDetector处理
//        return detector.onTouchEvent(event);
//    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 滑动切换效果的实现
     *//*
					 * @Override public boolean onFling(MotionEvent arg0,
					 * MotionEvent arg1, float arg2, float arg3) { // TODO
					 * Auto-generated method stub resetlaybg(); //当是Fragment0的时候
					 * if(MARK==0){ if(arg1.getX()<arg0.getX()+DISTANT){
					 * getSupportFragmentManager().beginTransaction().hide(
					 * fragments[0]).hide(fragments[1]).hide(fragments[2])
					 * .hide(fragments[3]).show(fragments[1]).commit();
					 * //linearLayouts[1].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[1].setTextColor(getResources().getColor(R.
					 * color.lightseagreen));
					 * titleView.setText(getResources().getText(R.string.video))
					 * ; MARK=1; } else{
					 * //linearLayouts[0].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[0].setTextColor(getResources().getColor(R.
					 * color.black)); }
					 * 
					 * } //当是Fragment1的时候 else if (MARK==1){
					 * if(arg1.getX()<arg0.getX()+DISTANT){
					 * getSupportFragmentManager().beginTransaction().hide(
					 * fragments[0]).hide(fragments[1]).hide(fragments[2])
					 * .hide(fragments[3]).show(fragments[2]).commit();
					 * //linearLayouts[2].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[2].setTextColor(getResources().getColor(R.
					 * color.lightseagreen));
					 * titleView.setText(getResources().getText(R.string.game));
					 * MARK=2; } else if(arg0.getX()<arg1.getX()+DISTANT){
					 * getSupportFragmentManager().beginTransaction().hide(
					 * fragments[0]).hide(fragments[1]).hide(fragments[2])
					 * .hide(fragments[3]).show(fragments[0]).commit();
					 * //linearLayouts[0].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[0].setTextColor(getResources().getColor(R.
					 * color.lightseagreen));
					 * titleView.setText(getResources().getText(R.string.select)
					 * ); MARK=0; } else{
					 * //linearLayouts[1].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[1].setTextColor(getResources().getColor(R.
					 * color.black)); } } //当是Fragment2的时候 else if(MARK==2){
					 * if(arg1.getX()<arg0.getX()+DISTANT){
					 * getSupportFragmentManager().beginTransaction().hide(
					 * fragments[0]).hide(fragments[1]).hide(fragments[2])
					 * .hide(fragments[3]).show(fragments[3]).commit();
					 * //linearLayouts[1].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[1].setTextColor(getResources().getColor(R.
					 * color.lightseagreen));
					 * titleView.setText(getResources().getText(R.string.user));
					 * MARK=3; } else if(arg0.getX()<arg1.getX()+DISTANT){
					 * getSupportFragmentManager().beginTransaction().hide(
					 * fragments[0]).hide(fragments[1]).hide(fragments[2])
					 * .hide(fragments[3]).show(fragments[1]).commit();
					 * //linearLayouts[0].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[3].setTextColor(getResources().getColor(R.
					 * color.lightseagreen));
					 * titleView.setText(getResources().getText(R.string.video))
					 * ; MARK=1; } else{
					 * //linearLayouts[2].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[3].setTextColor(getResources().getColor(R.
					 * color.black)); } } //当是Fragment3的时候 else if(MARK==3){
					 * if(arg0.getX()<arg1.getX()+DISTANT){
					 * getSupportFragmentManager().beginTransaction().hide(
					 * fragments[0]).hide(fragments[1]).hide(fragments[2])
					 * .hide(fragments[3]).show(fragments[2]).commit();
					 * //linearLayouts[1].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[1].setTextColor(getResources().getColor(R.
					 * color.lightseagreen));
					 * titleView.setText(getResources().getText(R.string.game));
					 * MARK=2; } else{
					 * //linearLayouts[2].setBackgroundResource(R.drawable.
					 * lay_select_bg);
					 * //textViews[3].setTextColor(getResources().getColor(R.
					 * color.black)); } } return false; }
					 */
    @Override
    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_title_search_btn:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.more_menu:
                Log.d(getClass().getSimpleName(), "onClick");
                if (!isShowMenu()) {
                    showPopMenu();
                }
                break;
        }
    }

    private boolean isShowMenu() {
        if (mPopupWindow == null) {
            return false;
        }
        return mPopupWindow.isShowing();
    }

    private void showPopMenu() {
        if (mPopupWindow == null) {
            View popupView = LayoutInflater.from(this).inflate(R.layout.pop_more_menu, null);

            mPopupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(false);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
            popupView.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        mPopupWindow.dismiss();
                        return true;
                    }
                    return false;
                }
            });
            popupView.findViewById(R.id.history).setOnClickListener(menuItem);
            popupView.findViewById(R.id.collection).setOnClickListener(menuItem);
            popupView.findViewById(R.id.down).setOnClickListener(menuItem);
            popupView.findViewById(R.id.movies).setOnClickListener(menuItem);

        }

        mPopupWindow.showAsDropDown(menu, 0, 20);

    }

    OnClickListener menuItem = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.history:
                    startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                    break;
                case R.id.collection:
                    startActivity(new Intent(MainActivity.this, CollectionActivity.class));
                    break;
                case R.id.down:
                    startActivity(new Intent(MainActivity.this, DownActivity.class));
                    break;
                case R.id.movies:
                    startActivity(new Intent(MainActivity.this, LocalMoviesActivity.class));
                    break;

            }


        }
    };

}