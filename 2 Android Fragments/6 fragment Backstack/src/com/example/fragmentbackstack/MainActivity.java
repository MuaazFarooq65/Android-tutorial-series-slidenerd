package com.example.fragmentbackstack;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener {

	FragmentManager manager;
	TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getFragmentManager();
		text = (TextView) findViewById(R.id.message);
		manager.addOnBackStackChangedListener(this);
	}

	public void addA(View v) {
		FragmentA f1 = new FragmentA();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.group, f1, "A");
		transaction.addToBackStack("addA");
		transaction.commit();
	}

	public void addB(View v) {
		FragmentB f2 = new FragmentB();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.group, f2, "B");
		transaction.addToBackStack("addB");
		transaction.commit();
	}

	public void removeA(View v) {
		FragmentA f1 = (FragmentA) manager.findFragmentByTag("A");
		FragmentTransaction transaction = manager.beginTransaction();
		if (f1 != null) {
			transaction.remove(f1);
			transaction.addToBackStack("removeA");
			transaction.commit();
		} else {
			Toast.makeText(this, "The Fragment A was not added before",	Toast.LENGTH_SHORT).show();
		}
	}

	public void removeB(View v) {
		FragmentB f1 = (FragmentB) manager.findFragmentByTag("B");
		FragmentTransaction transaction = manager.beginTransaction();
		if (f1 != null) {
			transaction.remove(f1);
			transaction.addToBackStack("removeB");
			transaction.commit();
		} else {
			Toast.makeText(this, "The Fragment B was not added before",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void replaceWithB(View v) {
		FragmentB f2 = new FragmentB();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.group, f2, "B");
		transaction.addToBackStack("replaceWithB");
		transaction.commit();
	}

	public void replaceWithA(View v) {
		FragmentA f1 = new FragmentA();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.group, f1, "A");
		transaction.addToBackStack("replaceWithA");
		transaction.commit();
	}

	public void attachA(View v) {
		FragmentA f1 = (FragmentA) manager.findFragmentByTag("A");
		FragmentTransaction transaction = manager.beginTransaction();
		if (f1 != null) {
			transaction.attach(f1);
			transaction.addToBackStack("attachA");
			transaction.commit();
		}
	}

	public void detachA(View v) {
		FragmentA f1 = (FragmentA) manager.findFragmentByTag("A");
		FragmentTransaction transaction = manager.beginTransaction();
		if (f1 != null) {
			transaction.detach(f1);
			transaction.addToBackStack("detachA");
			transaction.commit();
		}
	}

	public void back(View v) {
		manager.popBackStack();
	}

	public void popAddB(View v) {
		manager.popBackStack("addB", FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}

	@Override
	public void onBackStackChanged() {
		text.setText(text.getText() + "\n");
		text.setText(text.getText() + "The current status of Back Stack\n");

		int count = manager.getBackStackEntryCount();
		for (int i = count - 1; i >= 0; i--) {
			FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(i);
			text.setText(text.getText() + " " + entry.getName() + " \n");
		}
		text.setText(text.getText() + "\n");
	}
}