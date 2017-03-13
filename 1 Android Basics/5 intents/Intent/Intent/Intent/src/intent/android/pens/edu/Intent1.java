package intent.android.pens.edu;

import android.app.Activity;
import android.app.SearchManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

public class Intent1 extends Activity {
	/** Called when the activity is first created. */
	PopupWindow cp;
	boolean click = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button myCall = (Button) findViewById(R.id.myCall);
		Button myWebSearch = (Button) findViewById(R.id.myWebSearch);
		Button mySendTo = (Button) findViewById(R.id.mySendTo);
		Button myGetContent = (Button) findViewById(R.id.myGetContent);
		Button myActivity1 = (Button) findViewById(R.id.myActivity1);
		Button myViewContact = (Button) findViewById(R.id.myViewContact);
		Button myViewContactEdit = (Button) findViewById(R.id.myViewContactEdit);
		Button myViewWebpage = (Button) findViewById(R.id.myViewWebpage);
		Button myViewGeo = (Button) findViewById(R.id.myViewGeo);
		Button myMusic = (Button) findViewById(R.id.myMusic);
		Button mySendEmail = (Button) findViewById(R.id.mySendEmail);
		Button mySystem = (Button) findViewById(R.id.mySystem);
		Button myLocale = (Button) findViewById(R.id.myLocale);
		Button myActivity2 = (Button) findViewById(R.id.myActivity2);

		myCall.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:031-92468749"));
				startActivity(myIntent);
			}
		});

		myWebSearch.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(Intent.ACTION_WEB_SEARCH);
				myIntent.putExtra(SearchManager.QUERY, "yustian");
				startActivity(myIntent);
			}
		});

		mySendTo.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(Intent.ACTION_SENDTO, Uri
						.parse("smsto:031-92468749"));
				myIntent.putExtra("sms_body", "Kerjakan tugas intent android!");
				startActivity(myIntent);
			}
		});

		myGetContent.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent();
				myIntent.setType("image/picture/*");
				myIntent.setAction(Intent.ACTION_GET_CONTENT);
				startActivity(myIntent);
			}
		});

		myActivity1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntentActivity1 = new Intent(Intent1.this,
						Activity1.class);
				// call Activity1 and wait for result
				startActivity(myIntentActivity1);
			}
		});

		myViewContact.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String myContact = "content://contacts/people/";
				Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(myContact));
				startActivity(myIntent);
			}
		});

		myViewContactEdit.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String myContact = "content://com.android.contacts/raw_contacts/1";
				Intent myIntent = new Intent(Intent.ACTION_EDIT, Uri
						.parse(myContact));
				startActivity(myIntent);
			}
		});

		myViewWebpage.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String myWeb = "http://www.youtube.com";
				Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(myWeb));
				startActivity(myIntent);
			}
		});

		myViewGeo.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				String myGeo = "geo:0,0?q=1860+east+18th+street+cleveland+";
				Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(myGeo));
				startActivity(myIntent);
			}
		});

		myMusic.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(
						"android.intent.action.MUSIC_PLAYER");
				startActivity(myIntent);
			}
		});

		mySendEmail.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(Intent.ACTION_SEND);
				myIntent.putExtra(Intent.EXTRA_EMAIL,
						new String[] { "yustian@student.eepis-its.edu" });
				myIntent.putExtra(Intent.EXTRA_SUBJECT, "Topik");
				myIntent.setType("plain/text");
				myIntent.putExtra(Intent.EXTRA_TEXT, "Isi Email Disini.");
				startActivity(myIntent);
			}
		});

		mySystem.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(
						android.provider.Settings.ACTION_SETTINGS);
				startActivity(myIntent);
			}
		});

		myLocale.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(
						android.provider.Settings.ACTION_LOCALE_SETTINGS);
				startActivity(myIntent);
			}
		});

		myActivity2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				// create an Intent to talk to Activity2
				Intent myIntentActivity2 = new Intent(Intent1.this,
						Activity2.class);
				// call Activity2 and wait for results
				startActivity(myIntentActivity2);
			}
		});
		// Credit Here
		final LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		cp = new PopupWindow(inflater.inflate(R.layout.credit,
				(ViewGroup) findViewById(R.id.main)));
		Button myCredit = (Button) findViewById(R.id.myCredit);
		myCredit.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (click) {
					cp.showAtLocation(v, Gravity.CENTER, 0, 0);
					cp.update(0, 0, 310, 220);
					click = false;
				} else {
					cp.dismiss();
					click = true;
				}
			}
		});

	}
}