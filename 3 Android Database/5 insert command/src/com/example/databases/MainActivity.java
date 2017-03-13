package com.example.databases;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText username, password;
	DatabaseAdapter vivzHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        username=(EditText) findViewById(R.id.user);
        password=(EditText) findViewById(R.id.pass);
        
        vivzHelper= new DatabaseAdapter(this);
    }
    
    public void addUser(View view){
    	String getUser=username.getText().toString();
    	String getPass=password.getText().toString();
    	
    	long id=vivzHelper.inserData(getUser, getPass);
    	if (id<0) {
			Message.message(this, "Error");
		} else {
			Message.message(this, "Success "+id);
		}
    }
}
