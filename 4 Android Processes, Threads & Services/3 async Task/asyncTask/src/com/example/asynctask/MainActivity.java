package com.example.asynctask;

import java.util.ArrayList;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView mainList;
	private String[] texts = { "Lorem", "ipsum", "dolor", "sit", "amet",
			"consectetur", "adipiscing", "elit.", "Suspendisse", "ultrices",
			"orci", "sed", "elementum", "egestas", "lacus", "nibh", "dapibus",
			"velit", "vitae", "gravida", "nibh", "nibh", "a", "magna.", "Nunc",
			"nec", "vehicula", "libero.", "Aliquam", "aliquam", "lorem",
			"eget", "porttitor", "pellentesque.", "Cras", "rhoncus", "purus",
			"sed", "pellentesque", "dignissim", "dolor", "massa", "venenatis",
			"diam", "ac", "molestie", "lorem", "erat", "eget", "mi.",
			"Suspendisse", "fermentum", "elementum", "imperdiet.", "Nam",
			"luctus", "vel", "eros", "vel", "laoreet.", "Vivamus", "vel",
			"purus", "id", "odio", "ornare", "facilisis.", "Etiam", "luctus",
			"nisi", "non", "feugiat", "bibendum.", "Integer" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//activar progress bar dentro actividade e antes xml parse
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_main);
		mainList = (ListView) findViewById(R.id.listView1);
		//empty arraylist pois vamos aicionar depois
		mainList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));
		new Mytask().execute();

	}

	class Mytask extends AsyncTask<Void, String, Void> {
		
		private ArrayAdapter<String> adapter;
		private int count=0;
		@Override
		//1,º a ser chamado
		protected void onPreExecute() {
			adapter=(ArrayAdapter<String>) mainList.getAdapter();
			setProgressBarIndeterminate(false);
			setProgressBarVisibility(true);
		}
		
		@Override
		//2.º a ser chamado
		protected Void doInBackground(Void... params) {
			for (String item: texts) {
				publishProgress(item);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}
			return null;

		}
		
		@Override
		//depois de publishProgress(item)
		protected void onProgressUpdate(String... values) {
			adapter.add(values[0]);
			count++;
			setProgress((int)(((double)count/texts.length)*10000));
		}
		
		@Override
		//ultimo a ser chamado
		protected void onPostExecute(Void result) {
			setProgressBarVisibility(false);
			Toast.makeText(MainActivity.this, "All items were added succesfully", Toast.LENGTH_SHORT).show();
		}
	}
}
