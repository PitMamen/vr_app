package com.mygt.vrapp;  
  
import com.mygt.vrapp.R;
import com.mygt.vrapp.login.LoginActivity;
import com.mygt.vrapp.setting.QuestionActivity;
import com.mygt.vrapp.setting.SettingActivity;
import com.mygt.vrapp.user.CollectionActivity;
import com.mygt.vrapp.user.DownActivity;
import com.mygt.vrapp.user.HistoryActivity;
import com.mygt.vrapp.user.LocalMoviesActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;  
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;  
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;  
  
public class UserFragment extends Fragment implements OnClickListener 
{  
  
	Button history;
	Button collection;
	Button down;
	Button movies;
	
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {  
    	View view = inflater.inflate(R.layout.user_layout, container, false);
    	Button login = (Button) view.findViewById(R.id.login);
    	login.setOnClickListener(this);
    
    	view.findViewById(R.id.setting).setOnClickListener(this);
    	view.findViewById(R.id.question).setOnClickListener(this);
    	history = (Button) view.findViewById(R.id.history);
    	collection = (Button) view.findViewById(R.id.collection);
    	down = (Button) view.findViewById(R.id.down);
    	movies = (Button) view.findViewById(R.id.movies);
    	history.setOnClickListener(this);
    	collection.setOnClickListener(this);
    	down.setOnClickListener(this);
    	movies.setOnClickListener(this);
    	
    	initDrawable();
    	
        return  view; 
    }

    private void initDrawable() {
		int width = getResources().getDimensionPixelSize(R.dimen.user_fg_drawable);
		Drawable historyDrawable = getResources().getDrawable(R.drawable.btn_history);
		historyDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
		history.setCompoundDrawables(null, historyDrawable, null, null);//只放上边
        Drawable collectionDrawable = getResources().getDrawable(R.drawable.collection_z);
        collectionDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        collection.setCompoundDrawables(null, collectionDrawable, null, null);//只放上边
        Drawable downDrawable = getResources().getDrawable(R.drawable.btn_down);
        downDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        down.setCompoundDrawables(null, downDrawable, null, null);//只放上边
        Drawable moviesDrawable = getResources().getDrawable(R.drawable.btn_video);
        moviesDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        movies.setCompoundDrawables(null, moviesDrawable, null, null);//只放上边
	}
    
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.login:
				startActivity(new Intent(getActivity(),LoginActivity.class));
				break;
			case R.id.history:
				startActivity(new Intent(getActivity(),HistoryActivity.class));
				break;
			case R.id.collection:
				startActivity(new Intent(getActivity(),CollectionActivity.class));
				break;
			case R.id.down:
				startActivity(new Intent(getActivity(),DownActivity.class));
				break;
			case R.id.movies:
				startActivity(new Intent(getActivity(),LocalMoviesActivity.class));
				break;
			case R.id.setting:
				startActivity(new Intent(getActivity(),SettingActivity.class));
				break;
			case R.id.question:
				startActivity(new Intent(getActivity(),QuestionActivity.class));
				break;
		}
	}  
  
}  

