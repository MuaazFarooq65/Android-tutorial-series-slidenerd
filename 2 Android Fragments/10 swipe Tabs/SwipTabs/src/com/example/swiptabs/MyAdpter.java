package com.example.swiptabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdpter extends FragmentPagerAdapter {

	public MyAdpter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment=null;
		if (arg0==0) {
			fragment=new FragmentA();
		} else if(arg0==1){
			fragment=new FragmentB();
		} else if(arg0==2){
			fragment=new FragmentC();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
