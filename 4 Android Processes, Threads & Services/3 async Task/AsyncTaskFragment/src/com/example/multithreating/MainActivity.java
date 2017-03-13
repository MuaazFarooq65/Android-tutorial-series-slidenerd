package com.example.multithreating;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {

	private EditText selectionText;
	private ListView chooseImagesList;
	private String[] listofImages;
	private ProgressBar downloadImagesProgress;
	private NonUITaskFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		selectionText = (EditText) findViewById(R.id.dowanloadURL);
		chooseImagesList = (ListView) findViewById(R.id.urlList);
		listofImages = getResources().getStringArray(R.array.imageUrls);
		downloadImagesProgress = (ProgressBar) findViewById(R.id.downloadProgress);
	
		chooseImagesList.setOnItemClickListener(this);
		
		//so adicionamos o fragmento se nao foi adicionado anteriormente
		if (savedInstanceState==null) {
			fragment = new NonUITaskFragment();
			getSupportFragmentManager().beginTransaction().add(fragment,"task").commit();
		} else {
			fragment=(NonUITaskFragment) getSupportFragmentManager().findFragmentByTag("task");
		}
		//mostar a barra
		if (fragment!=null) {
			//se a task esta correr
			if (fragment.mytask!=null && fragment.mytask.getStatus()==AsyncTask.Status.RUNNING) {
				downloadImagesProgress.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		selectionText.setText(listofImages[position]);
	}

	public void downloadImage(View view) {
		if (selectionText.getText().toString() != null && selectionText.getText().toString().length() > 0) {
			//inicializamos fragmento
			fragment.beginTask(selectionText.getText().toString());
		}
	}
	
	public void showProgressBar(){
		//se qeremos mostrar mytask nao pode ser null
		if (fragment.mytask!=null) {
			//se qeremos mostrar mytask nao pode estar visivel anteriormente nem estar em 100%
			if (downloadImagesProgress.getVisibility()!=View.VISIBLE 
					&& downloadImagesProgress.getProgress()!= downloadImagesProgress.getMax()) {
				downloadImagesProgress.setVisibility(View.VISIBLE);
			}
		}
	}
	
	public void hideProgressBar(){
		if (fragment.mytask!=null) {
			if (downloadImagesProgress.getVisibility()==View.VISIBLE) {
				downloadImagesProgress.setVisibility(View.GONE);
			}
		}
	}
	
	public void updateProgressBar(int value){
		downloadImagesProgress.setProgress(value);
	}
	
}
