package com.example.listfragment;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyListFragment extends ListFragment implements OnItemClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.my_list_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.titles, R.layout.single_row);
		setListAdapter(new AndreAdapter(getActivity()));
		getListView().setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Resources res = getResources();
		String[] titles =  res.getStringArray(R.array.titles);
		String[] year = res.getStringArray(R.array.year);
		Toast.makeText(getActivity(), "Item "+position+"\n"+titles[position]+" "+year[position], Toast.LENGTH_SHORT).show();
	}
}

class SingleRow {
	String title, descripton;

	public SingleRow(String title, String descripton) {
		this.descripton = descripton;
		this.title = title;
	}
}

class AndreAdapter extends BaseAdapter {
	ArrayList<SingleRow> list;
	Context context;
	
	public AndreAdapter(Context c) {
		context=c;
		list = new ArrayList<SingleRow>();
		//temos meter dados dentro arrayList
		Resources res = c.getResources();
		
		String[] titles =  res.getStringArray(R.array.titles);
		String[] year = res.getStringArray(R.array.year);
		for (int i = 0; i < 4; i++) {
			list.add(new SingleRow(titles[i], year[i]));
		}
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.single_row, parent, false);
		}
		TextView title = (TextView) row.findViewById(R.id.textView1);
		TextView description = (TextView) row.findViewById(R.id.textView2);
		
		SingleRow temp = list.get(position);
		
		title.setText(temp.title);
		description.setText(temp.descripton);
		return row;
	}

}
