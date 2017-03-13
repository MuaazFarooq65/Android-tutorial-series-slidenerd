package com.example.inter_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class FragmentB extends Fragment {
	
	TextView text;
	String data;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_b, container, false);
		if (savedInstanceState==null) {
			//fragmentoB pela 1 vez
		} else {
			data = savedInstanceState.getString("text");
			TextView myText = (TextView) view.findViewById(R.id.textView1);
			myText.setText(data);
		}
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		text =  (TextView) getActivity().findViewById(R.id.textView1);
		
	}
	
	public void changeText(String data){
		this.data = data;
		text.setText(data);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("text", data);
	}
}
