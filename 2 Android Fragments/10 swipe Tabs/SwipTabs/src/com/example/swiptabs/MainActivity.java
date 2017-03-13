package com.example.swiptabs;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

public class MainActivity extends FragmentActivity implements TabListener {
	
	ViewPager viewPager;
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//viewPager
		viewPager = (ViewPager) findViewById(R.id.pager);
		android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
		viewPager.setAdapter(new MyAdpter(manager));
		
		//Temos sincronizar as TABS com viewpager
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			/*
			 * quando fazemos scroll obtemos proxima pagina aqui
			 */
			@Override
			public void onPageSelected(int arg0) {
//				Log.d("andre", "onPageSelected at position "+arg0);
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
//				Log.d("andre", "onPageScrolled at position "+arg0+" from "+arg1+ " with number of pixels "+arg2);
			}
			
			//estado da tab
			@Override
			public void onPageScrollStateChanged(int arg0) {
//				if (arg0==ViewPager.SCROLL_STATE_IDLE) {
//					Log.d("andre", "onPageScrollStateChanged IDLE");
//				} else if (arg0==ViewPager.SCROLL_STATE_DRAGGING) {
//					Log.d("andre", "onPageScrollStateChanged DRAGGING");
//				} else if (arg0==ViewPager.SCROLL_STATE_SETTLING) {
//					Log.d("andre", "onPageScrollStateChanged SETTLING");
//				}
			}
		});
		
		//usamos ActionBar na navegaçao como header
		actionBar=getActionBar();
		//definir modo navegacao
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//fazer tabs
		ActionBar.Tab tab1=actionBar.newTab();
		tab1.setText("Tab 1");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2=actionBar.newTab();
		tab2.setText("Tab 2");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3=actionBar.newTab();
		tab3.setText("Tab 3");
		tab3.setTabListener(this);
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
	}
	
	/*
	 * quando selecionamos TAB este metodo é chamado
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
//		Log.d("andre", "onTabSelected at position "+tab.getPosition()+" name "+tab.getText());
		viewPager.setCurrentItem(tab.getPosition());
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
//		Log.d("andre", "onTabUnselected at position "+tab.getPosition()+" name "+tab.getText());
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
//		Log.d("andre", "onTabReselected at position "+tab.getPosition()+" name "+tab.getText());
	}
}
