package com.andremlsantos;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SlideCalActivity extends Activity implements OnClickListener {

	String stringasNumber = "";
	Double result=0.0;
	Button sin, ln, cos, log, tan, e, pi, expo, fact, correct;
	Button seven, eight, nine, four, five, six, one, two, three, zero, dot;
	Button div, braq1, multi, braq2, minus, equal, plus;
	EditText resultasTEXT;

	List<Button> NUMBERS = new ArrayList<Button>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_cal);
		assign();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("string", stringasNumber);
		Log.d("VIVZ", stringasNumber);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		stringasNumber = savedInstanceState.getString("string");
		Log.d("VIVZ", stringasNumber);
	}

	@Override
	protected void onResume() {
		super.onResume();
		resultasTEXT.setText(stringasNumber);
		Log.d("VIVZ", stringasNumber);
	}

	@Override
	public void onClick(View v) {
		if (isNumber(v)) {
			fillNumber(v);
			Log.d("VIVZ", stringasNumber);
		} else if (v.getId() == equal.getId()) {
			evaluateSum(stringasNumber);
			stringasNumber=""+result;
			resultasTEXT.setText("" + result);
			result=0.0;
		}
	}

	public double evaluateSum(String expression) {
		String[] numbers = expression.split("\\+");
		for (String string : numbers) {
			double actual = Double.parseDouble(string);
			result += actual;
		}
		return result;
	}

	public void assign() {
		seven = (Button) findViewById(R.id.seven);
		seven.setOnClickListener(this);
		eight = (Button) findViewById(R.id.eight);
		eight.setOnClickListener(this);
		nine = (Button) findViewById(R.id.nine);
		nine.setOnClickListener(this);
		four = (Button) findViewById(R.id.four);
		four.setOnClickListener(this);
		five = (Button) findViewById(R.id.five);
		five.setOnClickListener(this);
		six = (Button) findViewById(R.id.six);
		six.setOnClickListener(this);
		one = (Button) findViewById(R.id.one);
		one.setOnClickListener(this);
		two = (Button) findViewById(R.id.two);
		two.setOnClickListener(this);
		three = (Button) findViewById(R.id.three);
		three.setOnClickListener(this);
		zero = (Button) findViewById(R.id.zero);
		zero.setOnClickListener(this);
		resultasTEXT = (EditText) findViewById(R.id.result);
		resultasTEXT.setOnClickListener(this);
		plus = (Button) findViewById(R.id.plus);
		plus.setOnClickListener(this);
		
		equal = (Button) findViewById(R.id.equal);
		equal.setOnClickListener(this);

		dot = (Button) findViewById(R.id.dot);
		dot.setOnClickListener(this);

		NUMBERS.add(seven);
		NUMBERS.add(eight);
		NUMBERS.add(nine);
		NUMBERS.add(four);
		NUMBERS.add(five);
		NUMBERS.add(six);
		NUMBERS.add(one);
		NUMBERS.add(two);
		NUMBERS.add(three);
		NUMBERS.add(zero);
		NUMBERS.add(plus);

	}

	public boolean isNumber(View v) {
		boolean result = false;
		for (Button button : NUMBERS) {
			if (v.getId() == button.getId()) {
				result = true;
			}
		}
		return result;
	}

	public void fillNumber(View v) {
		if (v.getId() == seven.getId()) {
			stringasNumber += 7;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == eight.getId()) {
			stringasNumber += 8;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == nine.getId()) {
			stringasNumber += 9;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == four.getId()) {
			stringasNumber += 4;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == five.getId()) {
			stringasNumber += 5;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == six.getId()) {
			stringasNumber += 6;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == one.getId()) {
			stringasNumber += 1;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == two.getId()) {
			stringasNumber += 2;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == three.getId()) {
			stringasNumber += 3;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == zero.getId()) {
			stringasNumber += 0;
			resultasTEXT.setText(stringasNumber);
		} else if (v.getId() == plus.getId()) {
			stringasNumber += '+';
			resultasTEXT.setText(stringasNumber);
		}
	}
}
