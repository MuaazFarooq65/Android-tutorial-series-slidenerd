package slidenerd.vivz;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/*
	 * como definimos no manifest file este metodo vai ser chamado
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.d("VIVZ", "orientation changed");
		// Log.d("VIVZ", ""+newConfig.orientation);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.d("VIVZ", "Welcome to Landscape mode");
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Log.d("VIVZ", "Welcome to Portrait mode");
		}
	}
}