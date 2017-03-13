package com.example.layoutinflation;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FrameLayout frameLayout = (FrameLayout) findViewById(R.id.my_frame_layout);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.my_linear_layout);
		
		//addFrameLayout(frameLayout);
		//addLinearLayout(linearLayout);
		addLinearWithParent(linearLayout, true);
	}

	/*
	 * aqui convertermos e metemos dentro FrameLayout
	 * como linear nao contem nada, Sub layout ocupa tudo
	 * nao definimos pai logo temos que adicionalo manualmente
	 */
	private void addFrameLayout(FrameLayout frameLayout) {
		LayoutInflater inflater = getLayoutInflater();
		View newView = inflater.inflate(R.layout.sub_layout, null);
		frameLayout.addView(newView);
	}
	
	/*
	 * aqui convertermos e metemos dentro LinearLayout
	 * O SubLayout vai ocupar algum espaco do Linear Layout logo aparecem os 2
	 * nao definimos pai logo temos que adicionalo manualmente
	 */
	private void addLinearLayout(LinearLayout linearLayout){
		LayoutInflater inflater = getLayoutInflater();
		View newView = inflater.inflate(R.layout.sub_layout, null);
		linearLayout.addView(newView);
	}
	
	/*
	 * aqui convertermos e metemos dentro LinearLayout
	 * Como definimos o pai nao pecisamos de adiciona-lo
	 * Aparecem os 2
	 */
	private void addLinearWithParent(LinearLayout linearLayout, boolean attachToRoot){
		LayoutInflater inflater = getLayoutInflater();
		View newView = inflater.inflate(R.layout.sub_layout, linearLayout, attachToRoot);
	}
}