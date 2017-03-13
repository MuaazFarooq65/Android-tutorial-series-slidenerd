package com.example.flexiblelayout;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment {
	TextView text;
	int index;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_b, container, false);
		text = (TextView) view.findViewById(R.id.textView1);
		if(savedInstanceState==null){
			Log.d("andre", "fragmentoB null "+index);
		} else {
			index = savedInstanceState.getInt("index");
			Log.d("andre", "fragmentoB NAO null "+index);
			Log.d("andre", "indexxxx "+index);
			changeData(index);
		}
		return view;
	}

	public void changeData(int index) {
		this.index=index;
		String[] descriptions = getResources().getStringArray(R.array.descriptons);
		text.setText(descriptions[index]);
		Log.d("andre", "indexxxx "+index);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("index", index);
	}
}
