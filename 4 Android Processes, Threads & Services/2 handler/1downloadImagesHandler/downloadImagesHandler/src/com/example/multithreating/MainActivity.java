package com.example.multithreating;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements OnItemClickListener{
	
	private EditText editText;
	private ListView listView;
	private String[] listofImages;
	private ProgressBar progressBar;
	private LinearLayout loadingSection=null;
	private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.dowanloadURL);
        listView=(ListView) findViewById(R.id.urlList);
        listView.setOnItemClickListener((OnItemClickListener) this);
        listofImages=getResources().getStringArray(R.array.imageUrls);
        progressBar=(ProgressBar) findViewById(R.id.downloadProgress);
        loadingSection=(LinearLayout) findViewById(R.id.loadingSection);
        handler=new Handler();
    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		editText.setText(listofImages[position]);
	}
	
	public void downloadImage(View view){
		String url=editText.getText().toString();
		Thread myThread = new Thread(new DownloadImagesThread(url));
		myThread.start();//o codigo dentro da classe corre numa thread diferente
	}
	
	/*
	 * 1 create the url object that represents the url
	 * 2 open connection using that url object
	 * 3 read data using inputstream into a byte array
	 * 4 open file output stream to save data on SD card
	 * 5 close the conection
	 */
	public boolean downloadImageUsingThreads(String url){
		boolean successful=false;
		URL downloadURL =null;
		HttpURLConnection connection =null;
		InputStream inputstream=null;
		FileOutputStream fos = null;
		File file = null;
		try {
			downloadURL = new URL(url);
			connection = (HttpURLConnection) downloadURL.openConnection();
			inputstream=connection.getInputStream();
			
			file = new File(Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES).getAbsolutePath()
					+ "/" + Uri.parse(url).getLastPathSegment());
			fos=new FileOutputStream(file);
			int read=-1;
			byte[] buffer = new byte[1024];
			while ((read=inputstream.read(buffer))!=-1) {
				fos.write(buffer, 0, read);
			}
			successful=true;
		} catch (MalformedURLException e) {
			//http:/www.(...)
			Log.d("andre", ""+e);
		} catch (IOException e) {
			Log.d("andre", ""+e);
		}
		finally{
			handler.post(new Runnable() {
				@Override
				public void run() {
					loadingSection.setVisibility(View.GONE);
				}
			});
			if (connection!=null) {
				connection.disconnect();
			}
			if (inputstream!=null) {
				try {
					inputstream.close();
				} catch (IOException e) {
					Log.d("andre", ""+e);
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					Log.d("andre", ""+e);
				}
			}
		}
		return successful;
		
	}
	
	private class DownloadImagesThread implements Runnable{
		private String url;
		public DownloadImagesThread(String url) {
			this.url=url;
		}

		@Override
		public void run() {
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					loadingSection.setVisibility(View.VISIBLE);
				}
			});
			
			downloadImageUsingThreads(url);
		}
	}
}
