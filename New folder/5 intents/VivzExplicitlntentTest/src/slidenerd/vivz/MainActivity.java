package slidenerd.vivz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button1);
    }
    
    public void doSomething(View v){
    	//Intent i = new Intent(this, ActivityB.class);
    	Intent i = new Intent();
    	//1 - package
    	//2 - classe
    	i.setClassName("slidenerd.vivz", "slidenerd.vivz.ActivityB");
    	startActivity(i);
    }
}
