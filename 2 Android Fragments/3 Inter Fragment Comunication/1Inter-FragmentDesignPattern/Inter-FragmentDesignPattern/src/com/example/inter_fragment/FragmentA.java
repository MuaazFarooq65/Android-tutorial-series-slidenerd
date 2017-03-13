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
	Communicator comm;
	
	/*
	 * quando a actividade no fragmento é reiniciada este motodo e chamado
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			//criamos fragmentoA pela 1 vez
			counter = 0;
		} else {
			//valor default = 0
			counter = savedInstanceState.getInt("counter", 0);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_a, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//so podemos acessar botao depois onCreate ter terminado
		button = (Button) getActivity().findViewById(R.id.button1);
		
		//getActivity retorna MainActivity e isto so e posisvel se MainActivity implemetar Interface
		comm = (Communicator) getActivity();
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		counter++;
		//percisamos saber quando alguem aciona respond method na actividade
		comm.respond("O Botao foi clicado "+counter+" vezes");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("counter", counter);
	}
}
