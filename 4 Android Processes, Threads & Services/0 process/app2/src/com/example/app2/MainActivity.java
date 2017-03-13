package com.example.app2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//TEMOS TER MESMO APP USER ID
public class MainActivity extends Activity {
	
	private EditText messageFromApp1;
	private TextView status;
	
	String packageName="com.example.app1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        messageFromApp1=(EditText) findViewById(R.id.editText1);
        status=(TextView) findViewById(R.id.textView1);
    }
    
    public void loadFile(View view){
    	//obter todas as apps instaladas no device
    	PackageManager packagemanager = getPackageManager();
    	try {
			ApplicationInfo app1 = packagemanager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
			String fullPath=app1.dataDir+"/files/vivz.txt";
			readFile(fullPath);
		} catch (NameNotFoundException e) {
			status.setTextColor(Color.RED);
			status.setText(""+e);
		}
    }
    
    public void readFile(String fullPath){
    	FileInputStream fis = null;
    	try {
			fis=new FileInputStream(new File(fullPath));
			int read=-1;
			StringBuffer buffer = new StringBuffer();
			while ((read=fis.read())!=-1) {
				buffer.append((char)read);
			}
			messageFromApp1.setText(buffer);
			status.setTextColor(Color.GREEN);
			status.setText(buffer+"\n was read successufully from \n"+fullPath);
		} catch (FileNotFoundException e) {
			status.setTextColor(Color.RED);
			status.setText(""+e);
		} catch (IOException e) {
			status.setTextColor(Color.RED);
			status.setText(""+e);
		}
    }
}
