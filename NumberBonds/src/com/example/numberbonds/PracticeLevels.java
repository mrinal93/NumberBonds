package com.example.numberbonds;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class PracticeLevels extends Activity {

	private int height;
	private int width;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_levels);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels; //432
		width = displaymetrics.widthPixels-40;	  //800
		screenset();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practice_levels, menu);
		return true;
	}
	public void level1(View view) {
		Intent intent = new Intent(this,P1.class);
		intent.putExtra("level",1);
        finish();
        startActivity(intent);
      }
	public void level2(View view) {
		Intent intent = new Intent(this,P1.class);
		intent.putExtra("level",2);
        finish();
        startActivity(intent);
      }
	public void level3(View view) {
		Intent intent = new Intent(this,P1.class);
		intent.putExtra("level",3);
        finish();
        startActivity(intent);
      }
	public void level4(View view) {
		Intent intent = new Intent(this,P1.class);
		intent.putExtra("level",4);
        finish();
        startActivity(intent);
      }
	public void level5(View view) {
		Intent intent = new Intent(this,P1.class);
		intent.putExtra("level",5);
        finish();
        startActivity(intent);
      }
	public void onBackPressed()
	{
	   Intent intent = new Intent(this, MainActivity.class);    
	   finish();
	   startActivity(intent);   
	}
	public void screenset()
	{
		ImageView b1 = (ImageView) findViewById(R.id.imagebutton1);
		ImageView b2 = (ImageView) findViewById(R.id.imagebutton2);
		ImageView b3 = (ImageView) findViewById(R.id.imagebutton3);
		ImageView b4 = (ImageView) findViewById(R.id.imagebutton4);
		ImageView b5 = (ImageView) findViewById(R.id.imagebutton5);
		
		b1.requestLayout();
		b1.getLayoutParams().height = height/11;
		b1.getLayoutParams().width = width/4;
		
		b2.requestLayout();
		b2.getLayoutParams().height = height/11;
		b2.getLayoutParams().width = width/4;
		
		b3.requestLayout();
		b3.getLayoutParams().height = height/11;
		b3.getLayoutParams().width = width/4;
		
		b4.requestLayout();
		b4.getLayoutParams().height = height/11;
		b4.getLayoutParams().width = width/4;
		
		b5.requestLayout();
		b5.getLayoutParams().height = height/11;
		b5.getLayoutParams().width = width/4;
	}
	
}

