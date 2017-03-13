package intent.android.pens.edu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity2 extends Activity {
	TextView label1;
	EditText text1;
	Button btnCallActivity2;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity2);
			label1 = (TextView) findViewById(R.id.label1);
			text1 = (EditText) findViewById(R.id.text1);
			btnCallActivity2 = (Button) findViewById(R.id.btnPickContact);
			btnCallActivity2.setOnClickListener(new ClickHandler());
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}// onCreate

	private class ClickHandler implements OnClickListener {
		public void onClick(View v) {
			try {
				// myDatarefer to: content://contacts/people/
				String myData = text1.getText().toString();
				// you may also try ACTION_VIEW instead
				Intent myActivity2 = new Intent(Intent.ACTION_PICK,
						Uri.parse(myData));
				// start myActivity2.
				// Tell it that our requestCodeID(or nickname) is 222
				startActivityForResult(myActivity2, 222);
				// Toast.makeText(getApplicationContext(),
				// "I can't wait for you", 1).show();
			} catch (Exception e) {
				label1.setText(e.getMessage());
			}
		}// onClick
	}// ClickHandler

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			// use requestCodeto find out who is talking back to us
			switch (requestCode) {
			case (222): {
				// 222 is our friendly contact-picker activity
				if (resultCode == Activity.RESULT_OK) {
					String selectedContact = data.getDataString();
					// it will return an URI that looks like:
					// content://contacts/people/n
					// where n is the selected contacts' ID
					label1.setText(selectedContact.toString());
					// show a 'nice' screen with the selected contact
					Intent myAct3 = new Intent(Intent.ACTION_VIEW,
							Uri.parse(selectedContact));
					startActivity(myAct3);
				} else {
					// user pressed the BACK button
					label1.setText("Selection CANCELLED " + requestCode + " "
							+ resultCode);
				}
				break;
			}
			}// switch
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}// onActivityResult
}// IntentDemo2