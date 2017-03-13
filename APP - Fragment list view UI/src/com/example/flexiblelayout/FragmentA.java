package com.example.flexiblelayout;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentA extends Fragment implements AdapterView.OnItemClickListener {
	ListView list;
	Communicator communicator;
	int index;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_a, container, false);
		list = (ListView) view.findViewById(R.id.listView1);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.chapters, android.R.layout.simple_list_item_1);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void setCommunicator(Communicator communicator) {
		this.communicator = communicator;
	}
	
	public void receiveIndex(int position){
		this.index=position;
		communicator.setText(position);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		communicator.respond(i);
	}

	public interface Communicator {
		public void respond(int index);
		public void setText(int index);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("index", index);
	}

}