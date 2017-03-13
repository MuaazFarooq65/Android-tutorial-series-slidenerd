package slidenerd.vivz;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void showToast(View view){
    	Toast toast = Toast.makeText(this, "Hello from slidenerd and vivz, whats up folks", Toast.LENGTH_LONG);
    	toast.setGravity(Gravity.CENTER, 0, 0);
    	toast.show();
    }
    
    public void showToast2(View view){
    	Toast toast = new Toast(this);
    	toast.setDuration(Toast.LENGTH_LONG);
    	toast.setGravity(Gravity.BOTTOM, 0, 0);
    	
    	LayoutInflater inflater = getLayoutInflater();
    	//inflater converte xml em objecto
    	//segundo parametro precisa ver um viewgroup
    	View appearance = inflater.inflate(R.layout.toast_layouyt, (ViewGroup)findViewById(R.id.root));
    	toast.setView(appearance);
    	toast.show();
    	
    }
}
