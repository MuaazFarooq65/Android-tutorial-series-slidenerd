package intent.android.pens.edu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity1 extends Activity {
	TextView label1;
	EditText text1;
	Button btnCallActivity1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity1);
			label1 = (TextView) findViewById(R.id.label1);
			text1 = (EditText) findViewById(R.id.text1);
			btnCallActivity1 = (Button) findViewById(R.id.btnCallActivity1);
			btnCallActivity1.setOnClickListener(new ClickHandler());
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}// onCreate

	private class ClickHandler implements OnClickListener {
		public void onClick(View v) {
			try {
				// myActivity2 places a phone call
				// for ACTION_CALL or ACTION_DIAL
				// use 'tel:' formatted data: "tel:555-1234"
				// for ACTION_VIEW use data: "http://www.youtube.com"
				// (you also need INTERNET permission -see Manifest)
				String myData = text1.getText().toString();
				Intent myActivity2 = new Intent(Intent.ACTION_DIAL,
						Uri.parse(myData));
				startActivity(myActivity2);
			} catch (Exception e) {
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
		}// onClick
	}// ClickHandler
}// IntentDemo1