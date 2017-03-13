package com.example.dialogfragmeng;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MyDialog extends DialogFragment implements View.OnClickListener{
	Button yes, no;
	Comunicator comunicator;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		//isto nao vai funcionar se a nossa actividade nao implementar interface
		comunicator=(Comunicator) activity;
	}
	
	/*
	 * DialogFragment é uma janela totamente diferente
	 * logo nao precesamos do ViewGroup parent 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.my_dialog, null);
		yes=(Button) view.findViewById(R.id.yes);
		no=(Button) view.findViewById(R.id.no);
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		
		getDialog().setTitle("MyDialog");
		
		setCancelable(false);
		return view;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==yes.getId()){
			//Toast.makeText(getActivity(), "Yes Button was clicked", Toast.LENGTH_SHORT).show();
			comunicator.onDialogMessage("Yes was clicked!");
			dismiss();
		} else if(v.getId()==no.getId()){
			//Toast.makeText(getActivity(), "No Button was clicked", Toast.LENGTH_SHORT).show();
			comunicator.onDialogMessage("No was clicked!");
			dismiss();
		}
	}
	/*
	 * comunicaçao entre fragemntos
	 */
	interface Comunicator{
		public void onDialogMessage(String message);
	}
}	
