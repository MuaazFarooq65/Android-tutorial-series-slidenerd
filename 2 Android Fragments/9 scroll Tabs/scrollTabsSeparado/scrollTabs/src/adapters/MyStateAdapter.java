package adapters;

import com.example.scrolltabs.FragmentA;
import com.example.scrolltabs.FragmentB;
import com.example.scrolltabs.FragmentC;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/*
 * FragmentStatePagerAdapter destroi fragmentos
 * UI do fragemento é destruida e fragemnto tbm
 * Podemos guardar dados em OnSaveInstanteState
 */
public class MyStateAdapter extends FragmentStatePagerAdapter{

	public MyStateAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = null;
		if (arg0==0) {
			fragment=new FragmentA();
		} else if(arg0==1){
			fragment=new FragmentB();
		}else if(arg0==2){
			fragment=new FragmentC();
		}
		//Log.d("andre", "get Item is called "+arg0);
		return fragment;
	}

	@Override
	public int getCount() {
		return 3;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		if(position==0){
			return "Tab 1";
		} else if(position==1){
			return "Tab 2";
		} else if(position==2){
			return "Tab 3";
		}
		return null;
	}
}