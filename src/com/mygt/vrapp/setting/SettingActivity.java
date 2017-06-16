package com.mygt.vrapp.setting;

import com.mygt.vrapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingActivity extends Activity implements OnClickListener{

	ToggleButton news;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_setting);
		
		findViewById(R.id.back).setOnClickListener(this);
		news = (ToggleButton) findViewById(R.id.change_news);
		Button save = (Button) findViewById(R.id.menu1);
		
		((TextView) findViewById(R.id.title_txt)).setText(R.string.setting);
		
		save.setVisibility(View.VISIBLE);
		save.setOnClickListener(this);
		save.setText(R.string.save);
		
		news.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back: 
			finish(); 
			break;
		}
	}
	
}
