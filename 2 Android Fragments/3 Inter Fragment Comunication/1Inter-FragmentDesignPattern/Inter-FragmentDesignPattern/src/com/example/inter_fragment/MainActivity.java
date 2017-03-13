package com.example.inter_fragment;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

	@Override
	public void respond(String data) {
		//aqui e acionado click
		FragmentManager manager=getFragmentManager();
		FragmentB f2 = (FragmentB) manager.findFragmentById(R.id.fragment2);
		f2.changeText(data);
	}
}