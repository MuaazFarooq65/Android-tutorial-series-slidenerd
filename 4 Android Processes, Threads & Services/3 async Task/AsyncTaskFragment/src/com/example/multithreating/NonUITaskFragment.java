package com.example.multithreating;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NonUITaskFragment extends android.support.v4.app.Fragment{
	MyTask mytask;
	private Activity activity;
	
	public NonUITaskFragment() {
	}
	
	//inicializar fragmento
	public void beginTask(String... arguments){
		mytask=new MyTask(activity);
		mytask.execute(arguments);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity=activity;//salvar referencia a actividade
		if (mytask!=null) {
			mytask.onAttach(activity);
		}
		Log.d("andre", "onAttach");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("andre", "onCreate");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("andre", "onActivityCreated");
		/*
		 * nao permite destruir o fragmento
		 * onDestroy nao é chamado
		 */
		setRetainInstance(true);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.d("andre", "onDetach");
		if (mytask!=null) {
			mytask.onDetach();
		}		
	}
}
