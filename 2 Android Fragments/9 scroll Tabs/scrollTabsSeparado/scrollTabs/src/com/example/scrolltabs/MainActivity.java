package com.example.scrolltabs;

import adapters.MyPagerAdapter;
import adapters.MyStateAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
	
	ViewPager viewPager=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 * ViewPager usa um Adapter
		 * Pode extend  FragmentStatePagerAdapter ou FragmentPagerAdapter
		 */
		viewPager = (ViewPager) findViewById(R.id.pager);
		FragmentManager manager = getSupportFragmentManager();
		
		//se usar este nao mostro titulo
		//viewPager.setAdapter(new MyPagerAdapter(manager));
		
		viewPager.setAdapter(new MyStateAdapter(manager));
	}
}


