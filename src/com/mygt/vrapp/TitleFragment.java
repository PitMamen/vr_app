package com.mygt.vrapp;  
  
import com.mygt.vrapp.R;

import android.app.Fragment;  
import android.os.Bundle;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.View.OnClickListener;
import android.view.ViewGroup;  
import android.widget.ImageButton;
import android.widget.Toast;
  
public class TitleFragment extends Fragment  
{  
  
	 private ImageButton mSearchBtn;  

	 @Override  
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			 Bundle savedInstanceState)  
	 {  
		 View view = inflater.inflate(R.layout.title_layout, container, false);  
		 mSearchBtn = (ImageButton) view.findViewById(R.id.id_title_search_btn);  
		 mSearchBtn.setOnClickListener(new OnClickListener()  
		 {  
			 @Override  
			 public void onClick(View v)  
			 {  
				 Toast.makeText(getActivity(),  
						 "i am an SearchButton in TitleFragment ! ",  
						 Toast.LENGTH_SHORT).show();  
			 }  
		 });  
		 return view;  
	}  

  
}  

