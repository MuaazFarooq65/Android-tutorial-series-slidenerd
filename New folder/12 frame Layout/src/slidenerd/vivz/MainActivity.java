package slidenerd.vivz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	ImageView p2, p3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		p2 = (ImageView) findViewById(R.id.p2);
		p3 = (ImageView) findViewById(R.id.p3);

		p2.setOnClickListener(this);
		p3.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == p2.getId()) {
			p2.setVisibility(View.GONE);
			p3.setVisibility(View.VISIBLE);
		} else {
			p3.setVisibility(View.GONE);
			p2.setVisibility(View.VISIBLE);
		}
	}

}