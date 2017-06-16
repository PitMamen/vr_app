package com.mygt.vrapp.login;

import com.mygt.vrapp.R;
import com.mygt.vrapp.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener{

	EditText account,password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
		findViewById(R.id.clear_account).setOnClickListener(this);
		findViewById(R.id.clear_password).setOnClickListener(this);
		findViewById(R.id.login).setOnClickListener(this);
		findViewById(R.id.register).setOnClickListener(this);
		findViewById(R.id.lost_password).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);
		findViewById(R.id.title_line).setVisibility(View.INVISIBLE);
		
		initTabDrawable();
	}

	private void initTabDrawable() {
		int width = getResources().getDimensionPixelSize(R.dimen.login_drawable);
        Drawable accountDrawable = getResources().getDrawable(R.drawable.accont_icon);
        accountDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        account.setCompoundDrawables(accountDrawable, null,  null, null);//只放上边
        Drawable passwordDrawable = getResources().getDrawable(R.drawable.password_icon);
        passwordDrawable.setBounds(0, 0, width, width);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        password.setCompoundDrawables(passwordDrawable, null,  null, null);//只放上边
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.back:
				finish();
				break;
			case R.id.clear_account:
				account.setText("");
				break;
			case R.id.clear_password:
				password.setText("");
				break;
			case R.id.login:
				break;
			case R.id.register:
				startActivity(new Intent(this,RegisterActivity.class));
				break;
			case R.id.lost_password:
				break;
		}
	}
}
