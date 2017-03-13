package com.example.handler;

import android.app.Activity;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	Thread thread;
	Handler handler;
	ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=(ProgressBar) findViewById(R.id.progressBar1);
        /*
         * MAIN THREAD
         * inicializar thread
         */
        thread=new Thread(new MyThread());
        //correr em background
        thread.start();
        handler= new Handler(){
        	//receiver
        	@Override
        	public void handleMessage(Message msg) {
        		//actualizar progress bar
        		bar.setProgress(msg.arg1);
        	}
        };
    }
    
    /*
     * separate thread
     */
    class MyThread implements Runnable{

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				//sender
				Message message = Message.obtain();
				message.arg1=i;
				handler.sendMessage(message);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
    }
}
