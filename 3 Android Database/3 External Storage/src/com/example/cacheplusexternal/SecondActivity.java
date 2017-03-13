package com.example.cacheplusexternal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.caxherplusexternal.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends Activity {

	EditText result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		result = (EditText) findViewById(R.id.result);
	}

	public void loadinternalCache(View view) {
		File folder = getCacheDir();
		File myFile = new File(folder, "mydata1.txt");
		String data=readData(myFile);
		checkNull(data);
	}

	public void loadexternalCache(View view) {
		File folder = getExternalCacheDir();
		File myFile = new File(folder, "mydata2.txt");
		String data=readData(myFile);
		checkNull(data);
	}

	public void loadexternalPrivate(View view) {
		File folder = getExternalFilesDir("privateFolder");
		File myFile = new File(folder, "mydata3.txt");
		String data=readData(myFile);
		checkNull(data);
	}

	public void loadexternalPublic(View view) {
		File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		File myFile = new File(folder, "mydata4.txt");
		String data=readData(myFile);
		checkNull(data);
	}
	
	private void checkNull(String data) {
		if (data!=null) {
			result.setText(data);
		} else {
			result.setText("No data was return");
		}
	}
	
	private String readData(File myFile){
		FileInputStream fis=null;
		try {
			fis= new FileInputStream(myFile);
			int read=-1;
			StringBuffer buffer = new StringBuffer();
			while((read=fis.read())!=-1){
				buffer.append((char)read);//7878 acd
			}
			return buffer.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if (fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public void previous(View view) {
		super.onBackPressed();
	}
}















