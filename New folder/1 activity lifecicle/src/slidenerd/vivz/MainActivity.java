package slidenerd.vivz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	String tag = "VIVZ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(tag, "onCreate was called");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(tag, "onResume was called");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(tag, "onStart was called");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(tag, "onPause was called");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(tag, "onStop was called");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(tag, "onRestart was called");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(tag, "onDestroy was called");
	}

}