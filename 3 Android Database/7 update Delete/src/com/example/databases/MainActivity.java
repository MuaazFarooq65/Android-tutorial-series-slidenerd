package com.example.databases;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText username, password, name, namePassword;
	DatabaseAdapter vivzHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        username=(EditText) findViewById(R.id.user);
        password=(EditText) findViewById(R.id.pass);
        name=(EditText) findViewById(R.id.textView3);
        namePassword=(EditText) findViewById(R.id.textView4);
        
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
    
    public void viewDetails(View view){
    	String result=vivzHelper.getAllData();
    	Message.message(this, result);
    }
    
    public void getDetails(View view){
    	String s1=name.getText().toString();
    	String result=vivzHelper.getName(s1);
    	Message.message(this, result);
    }
    
    public void getDetails2(View view){
    	String s1=namePassword.getText().toString();
    	String sub1=s1.substring(0, s1.indexOf(" "));
    	String sub2=s1.substring(s1.indexOf(" ")+1);
    	String result=vivzHelper.getIdWhereNamePassword(sub1, sub2);
    	Message.message(this, result);
    }
    
    public void update(View view){
    	String s1=namePassword.getText().toString();
    	String sub1=s1.substring(0, s1.indexOf(" "));
    	String sub2=s1.substring(s1.indexOf(" ")+1);
    	int count=vivzHelper.updateName(sub1,sub2);
    	Message.message(this, ""+count);
    }
    
    public void delete(View view){
    	String s1=namePassword.getText().toString();
    	String sub1=s1.substring(0, s1.indexOf(" "));
    	String sub2=s1.substring(s1.indexOf(" ")+1);
    	int count=vivzHelper.deleteName(sub1,sub2);
    	Message.message(this, ""+count);
    }
}
