package com.example.fragmenttransaction;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

    FragmentManager manager;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=getFragmentManager();
    }
	
	public void addA(View v) {
		//referencia ao fragmento A
		FragmentA f1 =  new FragmentA();
		//precisamos referencia ao nosso FragmentManager
		FragmentTransaction transaction = manager.beginTransaction();
		//1.º elemento e onde vamos mostrar fragmento
		transaction.add(R.id.group, f1, "A");
		transaction.commit();
	}
	
	public void addB(View v) {
		FragmentB f2 =  new FragmentB();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.group, f2, "B");
		transaction.commit();
	}
	
	public void removeA(View v) {
		FragmentA f1 =  (FragmentA) manager.findFragmentByTag("A");
		FragmentTransaction transaction = manager.beginTransaction();
		//temos testar se fragmento existe 
		//caso contrario retorn null
		if (f1!=null) {
			transaction.remove(f1);
			transaction.commit();
		} else {
			Toast.makeText(this, "The Frgament A was not added before", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void removeB(View v) {
		FragmentB f2 =  (FragmentB) manager.findFragmentByTag("B");
		FragmentTransaction transaction = manager.beginTransaction();
		//temos testar se fragmento existe 
		//caso contrario retorn null
		if (f2!=null) {
			transaction.remove(f2);
			transaction.commit();
		} else {
			Toast.makeText(this, "The Frgament B was not added before", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void replaceAwithB(View v){
		FragmentB f2 = new FragmentB();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.group, f2, "B");
		transaction.commit();
	}
	
	public void replaceBwithA(View v){
		FragmentA f1 = new FragmentA();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.group, f1, "A");
		transaction.commit();
	}
	
	/*
	 * tornar fragmento visivel
	 */
	public void attachA(View v){
		FragmentA f1= (FragmentA) manager.findFragmentByTag("A");
		FragmentTransaction transaction = manager.beginTransaction();
		
		if(f1!=null){
			transaction.attach(f1);
			transaction.commit();
		}
	}
	
	public void detachA(View v){
		FragmentA f1= (FragmentA) manager.findFragmentByTag("A");
		FragmentTransaction transaction = manager.beginTransaction();
		
		if(f1!=null){
			transaction.detach(f1);
			transaction.commit();
		}
	}
	
}