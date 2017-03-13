package com.example.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityB extends Activity {
	public static final String DEFAULT="";
	TextView username, password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		
		username=(TextView) findViewById(R.id.resultUsar);
		password= (TextView) findViewById(R.id.resultPass);
		
	}
	
	public void load(View view){
		SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
		String name = sharedPreferences.getString("name", DEFAULT);
		String pass = sharedPreferences.getString("password", DEFAULT);
		
		if (name.equals(DEFAULT) || password.equals(DEFAULT)) {
			Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show();
			username.setText(name);
			password.setText(pass);
		}
	}
	
	public void previous(View view){
		Toast.makeText(this, "Previous", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
