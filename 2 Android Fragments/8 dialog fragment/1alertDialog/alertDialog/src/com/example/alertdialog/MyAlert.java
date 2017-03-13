package com.example.alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MyAlert extends DialogFragment {
	
	/*
	 * criar o dialog e retornar esse obejcto para que o sistema o possa mostrar
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		//construir o dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		//texto dialog
		builder.setTitle("My Dialog");
		//builder.setMessage("Do you guys like this Dialog?");
		
//		display a list inside Dialog
//		builder.setItems(R.array.days, new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(getActivity(), "was selected "+ which, Toast.LENGTH_SHORT).show();
//			}
//		});
		
		//com select boxes
		builder.setMultiChoiceItems(R.array.days, null, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				Toast.makeText(getActivity(), "Item from position "+ which+" was selected "+isChecked, Toast.LENGTH_SHORT).show();
			}
		});
		
		//adicionar botoes
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getActivity(), "Negative Button was clicked!", Toast.LENGTH_SHORT).show();
			}
		});
		
		builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getActivity(), "Positive Button was clicked!", Toast.LENGTH_SHORT).show();
			}
		});
		Dialog dialog = builder.create();
		return dialog;
	}
}
