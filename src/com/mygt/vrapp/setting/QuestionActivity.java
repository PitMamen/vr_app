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

public class QuestionActivity extends Activity implements OnClickListener{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_question);
		
		findViewById(R.id.back).setOnClickListener(this);
		Button send = (Button) findViewById(R.id.menu1);
		
		((TextView) findViewById(R.id.title_txt)).setText(R.string.question);
		
		send.setVisibility(View.VISIBLE);
		send.setOnClickListener(this);
		send.setText(R.string.send);
		
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
