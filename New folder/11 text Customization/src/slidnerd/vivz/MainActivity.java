package slidnerd.vivz;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	
	TextView t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		t = (TextView) findViewById(R.id.my_text);
		
		//transormar rm objecto a font face
		Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/VOY_0.ttf");
		t.setTypeface(myCustomFont);
	}
}