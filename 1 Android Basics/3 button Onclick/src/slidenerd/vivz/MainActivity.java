package slidenerd.vivz;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

/*
 * 1) tells android you are interested in listening to a button click
 * 2) bring your xml button inside java
 * 3) tell your java button whose responding when its clicked
 * 4) what should happen when button is clicked
 */

public class MainActivity extends Activity implements OnClickListener {

	Button button;
	Button another;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button = (Button) findViewById(R.id.button1);
		another = (Button) findViewById(R.id.button2);
		// this==MainActivity é a actividade que responde quando o botao é
		// clicado
		button.setOnClickListener(this);
		another.setOnClickListener(this);
	}

	/*
	 * quando clicamos no botao este metodo é chamado automaticamente
	 */
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			Log.d("VIVZ", "first button was clicked");
		} else if (v.getId() == R.id.button2) {
			Log.d("VIVZ", "second button was clicked");
		}
	}
}
