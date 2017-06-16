package com.mygt.vrapp.player;

import com.mygt.vrapp.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;


/**
 * Created by xilin on 2016/8/10.
 */
public class PlayerFragmentAcivity extends FragmentActivity
{
	public static final String PATH_PARAM = "path";
	
    private PowerManager.WakeLock mWakeLock = null;
    private PlayerFragment mPlayerFragment;
    private String path ;
    
    public static void startThis(Context context, String path) {
		Intent intent = new Intent(context, PlayerActivity.class);
		intent.putExtra(PATH_PARAM, path);
		context.startActivity(intent);

	}
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_fragment_activity);
        path = getIntent().getStringExtra(PATH_PARAM);
        if(path == null) {
        	return;
        }
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "mytag");
        mWakeLock.acquire();
        mPlayerFragment = PlayerFragment.newInstance(path);
        getSupportFragmentManager().beginTransaction().add(R.id.fragactivity_flPlayer, mPlayerFragment).commit();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mWakeLock.release();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mPlayerFragment != null) {
                mPlayerFragment.releasePlayer();
                finish();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

}
