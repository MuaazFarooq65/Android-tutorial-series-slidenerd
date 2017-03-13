package com.example.cacheplusexternal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.caxherplusexternal.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.editText1);
    }
    
    public void internalCache(View v){
    	File folder=getCacheDir();
    	File myFile=new File(folder, "mydata1.txt");
    	String data=editText.getText().toString();
    	writeData(myFile,data);
    }

    public void externalCache(View v){
    	File folder=getExternalCacheDir();
    	File myFile=new File(folder, "mydata2.txt");
    	String data=editText.getText().toString();
    	writeData(myFile,data);
    }
    
    public void externalPrivate(View v){
    	File folder=getExternalFilesDir("privateFolder");
    	File myFile=new File(folder, "mydata3.txt");
    	String data=editText.getText().toString();
    	writeData(myFile,data);
    }
    
    public void externalPublic(View v){
    	File folder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    	File myFile=new File(folder, "mydata4.txt");
    	String data=editText.getText().toString();
    	writeData(myFile,data);
    }
    
	private void writeData(File myFile, String data) {
		FileOutputStream fos= null;
    	
    	try {
			fos=new FileOutputStream(myFile);
			fos.write(data.getBytes());
			message(data+" was written successfully "+myFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	finally{
    		try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}
    
    public void next(View v){
    	Intent intent = new Intent(this, SecondActivity.class);
    	startActivity(intent);
    }
    
    public void message(String message){
    	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
