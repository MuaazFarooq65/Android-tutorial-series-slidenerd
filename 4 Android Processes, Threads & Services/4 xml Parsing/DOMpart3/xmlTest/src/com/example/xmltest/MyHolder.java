package com.example.xmltest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyHolder {

	TextView title, date, description;
	ImageView image;
	
	public MyHolder(View view){
		title= (TextView) view.findViewById(R.id.articleTitleText);
		date = (TextView) view.findViewById(R.id.articlePublishedDate);
		description= (TextView) view.findViewById(R.id.articleDescriptionText);
		image = (ImageView) view.findViewById(R.id.articleImage);
	}
}
