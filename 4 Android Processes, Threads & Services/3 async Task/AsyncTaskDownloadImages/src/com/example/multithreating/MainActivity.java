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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements OnItemClickListener {

	private EditText selectionText;
	private ListView chooseImagesList;
	private String[] listofImages;
	private ProgressBar downloadImagesProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		selectionText = (EditText) findViewById(R.id.dowanloadURL);
		chooseImagesList = (ListView) findViewById(R.id.urlList);
		listofImages = getResources().getStringArray(R.array.imageUrls);
		downloadImagesProgress = (ProgressBar) findViewById(R.id.downloadProgress);

		chooseImagesList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		selectionText.setText(listofImages[position]);
	}

	public void downloadImage(View view) {
		if (selectionText.getText().toString() != null
				&& selectionText.getText().toString().length() > 0) {
			MyTask mytask = new MyTask();
			mytask.execute(selectionText.getText().toString());
		}
	}
	
	// params, progress, result
	class MyTask extends AsyncTask<String, Integer, Boolean> {
		private int totalSize=-1, actualDownloaded=0, calculatedProgress=0;
		@Override
		protected void onPreExecute() {
			downloadImagesProgress.setVisibility(View.VISIBLE);
		}
		
		/*
		 * 1 create the url object that represents the url 
		 * 2 open connection using that url object 
		 * 3 read data using inputstream into a byte array 
		 * 4 open file output stream to save data on SD card 5 close the conection
		 */
		@Override
		protected Boolean doInBackground(String... params) {
			boolean successful = false;
			URL downloadURL = null;
			HttpURLConnection connection = null;
			InputStream inputstream = null;
			FileOutputStream fos = null;
			File file = null;
			try {
				downloadURL = new URL(params[0]);
				connection = (HttpURLConnection) downloadURL.openConnection();
				
				totalSize=connection.getContentLength();//tamanho total file
				inputstream = connection.getInputStream();
	
				file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+ "/" + Uri.parse(params[0]).getLastPathSegment());
				fos = new FileOutputStream(file);
				int read = -1;
				byte[] buffer = new byte[1024];
				while ((read = inputstream.read(buffer)) != -1) {
					fos.write(buffer, 0, read);
					actualDownloaded=actualDownloaded+read;
					publishProgress(actualDownloaded);
				}
				successful = true;
			} catch (MalformedURLException e) {
				// http:/www.(...)
				Log.d("andre", "" + e);
			} catch (IOException e) {
				Log.d("andre", "" + e);
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
				if (inputstream != null) {
					try {
						inputstream.close();
					} catch (IOException e) {
						Log.d("andre", "" + e);
					}
				}
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						Log.d("andre", "" + e);
					}
				}
			}
			return successful;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			calculatedProgress=(int)(((double)values[0]/totalSize)*100);
			downloadImagesProgress.setProgress(calculatedProgress);
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			downloadImagesProgress.setVisibility(View.GONE);
		}
	
	}
}
