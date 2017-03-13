package com.example.inter_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentA extends Fragment implements OnClickListener{
	
	Button button;
	int counter=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_a, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//so podemos acessar botao depois onCreate ter terminado
		button = (Button) getActivity().findViewById(R.id.button1);
	}

	@Override
	public void onClick(View v) {
		counter++;
	}
	
}
