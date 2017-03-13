package com.example.internalstorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        
        username=(EditText) findViewById(R.id.editText1);
        password=(EditText) findViewById(R.id.editText2);
    }
    
    public void save(View view){
    	String user=username.getText().toString();
    	String pass=password.getText().toString();
    	user+=" ";
    	File file=null;
    	
    	//escrever dados em files
    	//usamos metodo que recebe bytes, logo convertemos inout daods antes
    	FileOutputStream fos = null;
		try {
			fos = openFileOutput("result.txt", Context.MODE_PRIVATE);
			fos.write(user.getBytes());
	    	fos.write(pass.getBytes());
	    	file=getFilesDir();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//este metodo executa sempre
		finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	Toast.makeText(this, "Saved Succefully "+file+" /result.txt", Toast.LENGTH_SHORT).show();
    }
    
    public void next(View view){
    	
    	Toast.makeText(this, "Next called", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(this, SecondActivity.class);
    	startActivity(intent);
    }
}
