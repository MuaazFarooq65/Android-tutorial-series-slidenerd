package com.example.flexiblelayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

public class AnotherActivity extends Activity {

	int index;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another);

		intent = getIntent();
		index = intent.getIntExtra("index", 0);
		FragmentB f2 = (FragmentB) getFragmentManager().findFragmentById(R.id.fragment2);
		if (f2 != null) {
			f2.changeData(index);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			//super.onBackPressed();
			Intent intent = new Intent();
			intent.putExtra("position",index);
			setResult(RESULT_OK, intent);        
			finish();			
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
		}
	}
}
