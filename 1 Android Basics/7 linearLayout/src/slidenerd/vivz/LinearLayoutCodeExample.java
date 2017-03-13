package slidenerd.vivz;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

public class LinearLayoutCodeExample extends Activity {
	
	LinearLayout ll;
	TextView t;
	Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * this = context
         * context é superclasse de Activity
         * Activity é superclasse de LinearLayoutCodeExample
         * context tbm é superclasse de LinearLayoutCodeExample
         * logo usamos this
         */
        
        //criar objectos
        ll = new LinearLayout(this);
        t = new TextView(this);
        b = new Button(this);
        
        //dimensoes
        LayoutParams dimensions = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(dimensions);
        
        LayoutParams viewDimensions = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        t.setLayoutParams(viewDimensions);
        b.setLayoutParams(viewDimensions);
        
        //propriedades
        ll.setOrientation(LinearLayout.VERTICAL);
        t.setText("Hello World!");
        b.setText("Button Here");
        
        //adicionar views
        ll.addView(t);
        ll.addView(b);
        
        setContentView(ll);       
    }
    
    
}
