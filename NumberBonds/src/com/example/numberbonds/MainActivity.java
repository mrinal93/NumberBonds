package com.example.numberbonds;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * Function executed when "Let's Learn" is clicked.
	 */
	public void one(View view) {
		//Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_SHORT).show();
		Log.e("it is executed","executed");
        Intent intent = new Intent(this,LevelsDisplay.class);
        finish();
        startActivity(intent);
      }
	/**
	 * Function executed when "Let's Practice" is clicked.
	 */
	public void p1(View view) {
		//Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_SHORT).show();
		Log.e("it is executed","executed");
        Intent intent = new Intent(this,PracticeLevels.class);
        finish();
        startActivity(intent);
      }
	/**
	 * Function executed when "Numberline" is clicked.
	 */
	public void nline(View view) {
		//Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_SHORT).show();
		Log.e("it is executed","executed");
        Intent intent = new Intent(this,NL1.class);
        finish();
        startActivity(intent);
      }
	
}
