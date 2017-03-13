package com.example.looperexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	MyThread myThread;
	Button sendMessageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendMessageButton=(Button) findViewById(R.id.sendMessage);
        myThread= new MyThread();
        myThread.start();
    }
    
    public void sendMessage(View view){
    	myThread.handler.post(new Runnable() {
			
			@Override
			public void run() {
				L.m(Thread.currentThread().getName());
			}
		});
    }
}

class MyThread extends Thread {
	Handler handler;
	public MyThread(){
	}
	
	@Override
	public void run() {
		Looper.prepare();//faz com que a tread tenha uma fila message
		handler= new Handler();
		Looper.loop();//vai buscar message de cada vez da fila e processala com handler
	}
}