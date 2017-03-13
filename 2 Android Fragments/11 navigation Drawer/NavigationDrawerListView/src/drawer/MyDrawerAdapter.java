package drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationdrawer.R;

public class MyDrawerAdapter extends BaseAdapter {
	public Context context;
	String[] socialSites;
	int[] images = {R.drawable.ic_facebook, R.drawable.ic_twitter, R.drawable.ic_youtube, R.drawable.ic_googleplus, R.drawable.ic_pinterest};
	public MyDrawerAdapter(Context context){
		socialSites=context.getResources().getStringArray(R.array.social);
		this.context=context;
	}
	
	@Override
	public int getCount() {
		return socialSites.length;
	}

	@Override
	public Object getItem(int position) {
		return socialSites[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row;
		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.custom_row, parent, false);
		} else {
			row = convertView;
		}
		TextView title=(TextView) row.findViewById(R.id.textView1);
		ImageView image = (ImageView) row.findViewById(R.id.imageView1);
		
		title.setText(socialSites[position]);
		image.setImageResource(images[position]);
		return row;
	}

}
