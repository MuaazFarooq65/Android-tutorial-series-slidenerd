package adapters;

import com.example.scrolltabs.FragmentA;
import com.example.scrolltabs.FragmentB;
import com.example.scrolltabs.FragmentC;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
 * FragmentPagerAdapter nao destroi fragmentos
 * UI do fragemento é destruida mas o fragmento NAO
 * Assim que os fragementos sao construidos nao sao completamente destruidos
 */

public class MyPagerAdapter extends FragmentPagerAdapter{

	public MyPagerAdapter(FragmentManager fm) {		
		super(fm);
	}
	
	/*
	 * retornar fragemento na posicao dada
	 */
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
		//Log.d("andre", "get Count is called");
		return 3;
	}
}