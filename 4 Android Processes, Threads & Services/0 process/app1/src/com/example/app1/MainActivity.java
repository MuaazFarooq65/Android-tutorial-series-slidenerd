package com.example.app1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//TEMOS TER MESMO APP USER ID
public class MainActivity extends Activity {
	
	private EditText messageBox;
	private TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        messageBox=(EditText) findViewById(R.id.editText1);
        status=(TextView) findViewById(R.id.textView1);
    }
    
    public void saveFile(View view){
    	File file=null;
    	String text1=messageBox.getText().toString();
    	FileOutputStream fos=null;
    	try {
    		file=getFilesDir();
			fos=openFileOutput("vivz.txt", Context.MODE_PRIVATE);
			fos.write(text1.getBytes());
			status.setTextColor(Color.GREEN);
			status.setText(text1+" \nwriteen to\n "+file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			status.setTextColor(Color.GREEN);
			status.setText(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			status.setTextColor(Color.GREEN);
			status.setText(e.toString());
			e.printStackTrace();
		}
    }
}
