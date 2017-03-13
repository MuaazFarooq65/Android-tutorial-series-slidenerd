package com.example.listfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener{
	
	/*
	 * temos usar id já definidos nos my_list_fragment
	 * <ListView android:id="@android:id/list"
	 * <EditText android:id="@android:id/empty"
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.my_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.heroes, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getActivity(), "Item "+position, Toast.LENGTH_SHORT).show();
	}
}
