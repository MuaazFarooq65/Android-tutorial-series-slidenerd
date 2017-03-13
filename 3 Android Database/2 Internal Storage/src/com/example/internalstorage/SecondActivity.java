package com.example.internalstorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity {
	
	EditText username, password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		username=(EditText) findViewById(R.id.resultuser);
		password=(EditText) findViewById(R.id.resultpass);
	}
	
	public void previous(View view){
		super.onBackPressed();
	}
	
	/*
	 * o q vemos quando abrimos o file - user pass
	 * o que contem realmente - 45 484 487 878 23 12 
	 * se nao for lido sucesso -1
	 */
	public void load(View view){
		try {
			FileInputStream fis = openFileInput("result.txt");
			int read=-1;
			StringBuffer buffer = new StringBuffer();
			while ((read=fis.read())!=-1) {
				buffer.append((char)read);
			}
			Log.d("andre", buffer.toString());
			String user = buffer.substring(0, buffer.indexOf(" "));
			String pass = buffer.substring(buffer.indexOf(" ")+1);
			username.setText(user);
			password.setText(pass);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(this, "Not data", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
