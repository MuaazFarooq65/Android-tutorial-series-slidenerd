package com.example.scrolltabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentA extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d("andre", "onCreateView");
		return inflater.inflate(R.layout.fragment_a, container, false);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("andre", "onAttach");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState==null) {
			Log.d("andre", "onCreate FIRST TIME");
		} else {
			Log.d("andre", "onCreate SECOND TIME");
		}
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("andre", "onActivityCreated");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.d("andre", "onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d("andre", "onResume");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.d("andre", "onPause");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("andre", "onSaveInstanceState");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.d("andre", "onStop");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("andre", "onDestroy");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d("andre", "onDestroyView");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.d("andre", "onDetach");
	}
}
