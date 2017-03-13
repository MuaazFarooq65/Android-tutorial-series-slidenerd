package slidenerd.vivz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {

	RelativeLayout main;

	EditText userNameValue, passwordValue;
	TextView message, userName, password;
	Button login;

	// id
	int messageId = 1, userNameId = 2, userNameValueId = 3, passwordId = 4,
			passwordValueId = 5, logginId = 6;
	int paddingValue = 10;

	LayoutParams messageDimensions, userNameDimensions,
			userNameValueDimensions, passwordDimensions,
			passwordValueDimensions, logginDimensions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();

		createMessageTextView();
		createUserNameTextView();
		createUserNameEditText();
		createPasswordTextView();
		createPasswordEditText();
		createButton();

		main.addView(message, messageDimensions);
		main.addView(userName, userNameDimensions);
		main.addView(userNameValue, userNameValueDimensions);
		main.addView(password, passwordDimensions);
		main.addView(passwordValue, passwordValueDimensions);
		main.addView(login, logginDimensions);

		setContentView(main);
	}

	private void init() {
		main = new RelativeLayout(this);
		LayoutParams mainDimensions = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		main.setLayoutParams(mainDimensions);

		message = new TextView(this);// Please login first

		userName = new TextView(this);// User Name
		userNameValue = new EditText(this);// edit text with user name

		password = new TextView(this);// Password
		passwordValue = new EditText(this);// edit text with password

		login = new Button(this);
	}

	private void createMessageTextView() {
		messageDimensions = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		messageDimensions.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		message.setPadding(paddingValue, 100, paddingValue, paddingValue);
		message.setText("Please Loggin First");
		message.setId(messageId);
		message.setLayoutParams(messageDimensions);
	}

	private void createUserNameTextView() {
		userNameDimensions = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		userNameDimensions.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		userNameDimensions.addRule(RelativeLayout.BELOW, messageId);
		userName.setPadding(paddingValue, paddingValue, paddingValue,
				paddingValue);
		userName.setText("User Name");
		userName.setId(userNameId);
	}

	private void createUserNameEditText() {
		userNameValueDimensions = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		userNameValue.setId(userNameValueId);
		userNameValueDimensions.addRule(RelativeLayout.BELOW, messageId);
		userNameValueDimensions.addRule(RelativeLayout.RIGHT_OF, userNameId);
		userNameValueDimensions.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		userNameValueDimensions.addRule(RelativeLayout.ALIGN_BASELINE,
				userNameId);
	}

	private void createPasswordTextView() {
		passwordDimensions = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		passwordDimensions.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		passwordDimensions.addRule(RelativeLayout.BELOW, userNameValueId);
		passwordDimensions.addRule(RelativeLayout.ALIGN_RIGHT, userNameId);
		password.setPadding(paddingValue, paddingValue, paddingValue,
				paddingValue);
		password.setGravity(Gravity.RIGHT);
		password.setId(passwordId);
		password.setText("Password");
	}

	private void createPasswordEditText() {
		passwordValueDimensions = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		passwordValue.setId(passwordValueId);
		passwordValueDimensions.addRule(RelativeLayout.BELOW, userNameValueId);
		passwordValueDimensions.addRule(RelativeLayout.RIGHT_OF, passwordId);
		passwordValueDimensions.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		passwordValueDimensions.addRule(RelativeLayout.ALIGN_BASELINE,
				passwordId);
	}

	private void createButton() {
		logginDimensions = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		login.setId(logginId);
		login.setText("Login");
		logginDimensions.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		logginDimensions.addRule(RelativeLayout.BELOW, passwordValueId);
	}
}