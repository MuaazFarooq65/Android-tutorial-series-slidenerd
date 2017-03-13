package com.example.addfragmentsxml;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("VIVZ", "onCreate from Activity");
    }
    
    /*
     * link ao Fragment
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
    	Log.i("VIVZ", "onAttachFragment from Activity");
    	super.onAttachFragment(fragment);
    }
    
    /*
     * Quando vemos a APP
     */
    @Override
    protected void onStart() {
    	super.onStart();
    	Log.i("VIVZ", "onStart from Activity");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Log.i("VIVZ", "onResume from Activity");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.i("VIVZ", "onPause from Activity");
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	Log.i("VIVZ", "onSaveInstanceState from Activity");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	Log.i("VIVZ", "onStop from Activity");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.i("VIVZ", "onDestroy from Activity");
    }
}