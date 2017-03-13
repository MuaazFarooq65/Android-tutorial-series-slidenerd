package com.example.xmltest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter{

	ArrayList<HashMap<String, String>> dataSource= new ArrayList<HashMap<String,String>>();
	Context context;
	LayoutInflater inflater;
	
	public MyAdapter(Context context, ArrayList<HashMap<String, String>> dataSource){
		this.dataSource= dataSource;
		this.context= context;
		inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return dataSource.size();
	}

	@Override
	public Object getItem(int position) {
		return dataSource.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row= convertView;
		MyHolder holder= null;
		if (row==null) {
			row=inflater.inflate(R.layout.custom_row, parent, false);
			holder= new MyHolder(row);
			row.setTag(holder);
		} else {
			holder=(MyHolder) row.getTag();
		}
		HashMap<String, String> currentItem= dataSource.get(position);
		holder.title.setText(currentItem.get("title"));
		holder.date.setText(currentItem.get("pubDate"));
//		holder.image.setImageURI(Uri.parse(currentItem.get("imageURL")));
		File image = new File(Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES).getAbsolutePath()
				+ "/" + position + ".png");
		Bitmap myBitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
		holder.image.setImageBitmap(myBitmap);
		holder.description.setText(currentItem.get("description"));
		return row;
	}
}













