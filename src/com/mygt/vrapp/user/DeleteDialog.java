package com.mygt.vrapp.user;

import com.mygt.vrapp.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class DeleteDialog extends DialogFragment {
	
	Button cancel,determine;
	
	OnClickListener determineListener, cancelListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(0, R.style.FullHeightDialog); 
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.delete_dialog, container, false);
		cancel = (Button) view.findViewById(R.id.cancel);
		determine = (Button) view.findViewById(R.id.determine);
		determine.setOnClickListener(determineListener); 
		cancelListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		};
		cancel.setOnClickListener(cancelListener); 
		return view;
	}
	
	public void setDetermineListener(OnClickListener onClickListener) {
		determineListener = onClickListener;
		if(determine != null) {
			determine.setOnClickListener(determineListener); 
		}
	}
	
	public void setCancelListener(OnClickListener onClickListener) {
		cancelListener = onClickListener;
		if(cancel != null) {
			cancel.setOnClickListener(cancelListener); 
		}
	}
	
}
