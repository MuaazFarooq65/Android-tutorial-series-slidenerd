package com.example.dialogfragmeng;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dialogfragmeng.MyDialog.Comunicator;

public class MainActivity extends Activity implements Comunicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    
    public void showDialog(View v){
    	FragmentManager manager =  getFragmentManager();
    	MyDialog myDialog =  new MyDialog();
    	//metodo que tem fragmentTransaction
    	//ultimo a ser invocado
    	myDialog.show(manager, "MyDialog");
    }

	@Override
	public void onDialogMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}