package com.example.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText username, password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		username=(EditText) findViewById(R.id.username);
		password=(EditText) findViewById(R.id.password);
	}
	
	public void save(View view) {
		SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		
		editor.putString("name", username.getText().toString());
		editor.putString("password", password.getText().toString());
		
		editor.commit();
		Toast.makeText(this, "Data was saved successfully", Toast.LENGTH_SHORT).show();
	}
	
	public void next(View view){
		Toast.makeText(this, "Next", Toast.LENGTH_SHORT).show();
		Intent intent =  new Intent(this, ActivityB.class);
		startActivity(intent);
	}
}