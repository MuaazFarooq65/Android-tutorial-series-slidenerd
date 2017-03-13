package com.example.flexiblelayout;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity implements FragmentA.Communicator {

	FragmentManager manager;
	FragmentA f1;
	int AnotherActivityIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		manager = getFragmentManager();
		f1 = (FragmentA) manager.findFragmentById(R.id.fragment1);
		f1.setCommunicator(this);
	}

	@Override
	public void respond(int index) {
		FragmentB f2 = (FragmentB) manager.findFragmentById(R.id.fragment2);
		if (f2 != null && f2.isVisible()) {
			f2.changeData(index);
		} else {
			Intent intent = new Intent(this, AnotherActivity.class);
			intent.putExtra("index", index);
			startActivityForResult(intent, 1);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				AnotherActivityIndex = data.getIntExtra("position", -1);
				f1.receiveIndex(AnotherActivityIndex);
				f1.setCommunicator(this);
			}
		}
	}

	@Override
	public void setText(int index) {
		FragmentB f2 = (FragmentB) manager.findFragmentById(R.id.fragment2);
		f2.changeData(index);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("AnotherActivityIndex", AnotherActivityIndex);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		AnotherActivityIndex = savedInstanceState.getInt("AnotherActivityIndex");
		f1.setCommunicator(this);
	}

}