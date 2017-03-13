package com.example.navigationdrawer;

import tabs.MyTabsAdapter;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import drawer.MyDrawerAdapter;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {
	
	private FragmentManager manager;
	private DrawerLayout drawerLayout;
	private ListView listView;
	private String[] social;
	private ActionBarDrawerToggle drawerListener;
	private MyDrawerAdapter adapter;
	
	ViewPager viewPager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager=getFragmentManager();

		// ober dados
		social = getResources().getStringArray(R.array.social);

		// drawer lado esquerdo
		listView = (ListView) findViewById(R.id.drawerList);
		adapter=new MyDrawerAdapter(this);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(this);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		// mudanca icon quando abrimos e fechamos drawer
		drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				Toast.makeText(MainActivity.this, "Drawer open", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				Toast.makeText(MainActivity.this, "Drawer close", Toast.LENGTH_SHORT).show();
			}
		};
		drawerLayout.setDrawerListener(drawerListener);
		//meter home button cliclavel
		getSupportActionBar().setHomeButtonEnabled(true);
		//meter o home button comportarse como back button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		//tabs
		viewPager = (ViewPager) findViewById(R.id.pager);
		android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
		// viewPager.setAdapter(new MyPagerAdapter(manager));
		viewPager.setAdapter(new MyTabsAdapter(manager));
	}
	
	/*
	 * sincronizar o estado drawer
	 * vai tbm meter o icon a funcionar
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}
	
	/*
	 * fazer o drawer aparecer quando clicamos no home button
	 * vai tratar de quando clicamos no item
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//este eveno foi tratado com sucesso
		//se sim retornamos verdade
		if(drawerListener.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 * vamos passar as configuraçoes do drawerListener
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//as novas configuraçoes devem ser metidas
		drawerListener.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, social[position] + " was selected at position " + position, Toast.LENGTH_SHORT).show();
		selectItem(position);
		if(position%2==0){
			viewPager.setCurrentItem(0);
			drawerLayout.closeDrawers();
		} else {
			viewPager.setCurrentItem(1);
			drawerLayout.closeDrawers();
		}
	}

	private void selectItem(int position) {
		listView.setItemChecked(position, true);
		setTitle(social[position]);
	}

	private void setTitle(String title) {
		getSupportActionBar().setTitle(title);
	}
}