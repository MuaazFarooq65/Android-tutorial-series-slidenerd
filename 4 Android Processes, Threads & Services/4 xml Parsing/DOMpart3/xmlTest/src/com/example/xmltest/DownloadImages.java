package com.example.xmltest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class DownloadImages extends AsyncTask<String, Void, Boolean> {
	
	int position;
	
	public DownloadImages(int position){
		this.position=position;
	}
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

			inputstream = connection.getInputStream();

			file = new File(Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES).getAbsolutePath()
					+ "/" + position+".png");
			fos = new FileOutputStream(file);
			int read = -1;
			byte[] buffer = new byte[1024];
			while ((read = inputstream.read(buffer)) != -1) {
				fos.write(buffer, 0, read);
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
}
