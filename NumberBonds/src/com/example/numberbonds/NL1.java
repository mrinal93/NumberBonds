package com.example.numberbonds;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class NL1 extends Activity {

	ImageView qm;
	ImageView tick,next;
	ArrayList<Integer> number1 = new ArrayList<Integer>();
	ArrayList<Integer> number1_red = new ArrayList<Integer>();
	ArrayList<Integer> number2 = new ArrayList<Integer>();
	ArrayList<Integer> number2_red = new ArrayList<Integer>();
	ArrayList<Integer> answer = new ArrayList<Integer>();
	int blink1_counter=0;
	int blink2_counter=0;
	int answer_counter=0;
	int question=1;
	int height;
	int width;
	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_nl1);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels; //432
		width = displaymetrics.widthPixels;	  //800
		screenset();
		mp = MediaPlayer.create(this, R.raw.numberline_en);
		mp.start();
		if(!mp.isPlaying())
			mp.release();
		loadActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nl1, menu);
		return true;
	}
	public void loadActivity()
	{
		setContentView(R.layout.activity_nl1);
		screenset();
		if(!mp.isPlaying())
			mp.release();
		qm = (ImageView)findViewById(R.id.imageView6);
		tick = (ImageView)findViewById(R.id.imageView1);
		next = (ImageView)findViewById(R.id.imageButton1);
		tick.setVisibility(View.INVISIBLE);
		next.setVisibility(View.INVISIBLE);
		//Log.e("LoadActivity","qm ready");
		ListInitialise();
		LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearlayout);
		//inflater.inflate(R.layout.DrawingViewXml, layout);
		DrawingView drawingView=new DrawingView(this);	
		linearLayout.addView(drawingView);
	}
	public void next(View view)
	{
		Log.e("Next","It is being executed!");
		if(question==8)
		{
			Intent intent = new Intent(this, MainActivity.class);    
			finish();
			startActivity(intent);
		}
		else
		{
			loadActivity();
			ImageView num1 = (ImageView)findViewById(R.id.imageView2);
	    	num1.setImageResource(number1.get(question));
			ImageView num2 = (ImageView)findViewById(R.id.imageView4);
			num2.setImageResource(number2.get(question));
			qm.setImageResource(R.drawable.qm1);
			tick.setVisibility(View.INVISIBLE);
			question++;
		}
	}
	public void onBackPressed()
	{
	   //logic here, for example an intent
	   Intent intent = new Intent(this, MainActivity.class);    
	   finish();
	   startActivity(intent);   
	}
	
	public void hello()
	{
		Log.e("Hello","Called");
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		    @Override
		    public void run() 
		    {
		    	getSound();
		    	qm.setImageResource(answer.get(answer_counter));
				tick.setVisibility(View.VISIBLE);
				next.setVisibility(View.VISIBLE);
				answer_counter++;
		       
		    }
		}, 1000);
		//answer_counter++;
		
	}
	public void getSound()
	{
		mp = MediaPlayer.create(this,R.raw.correct_en);
		mp.start();
	}
	public void blink1()
	{
		ImageView num1 = (ImageView)findViewById(R.id.imageView2);
		//Log.e("Blink1",((Integer)blink1_counter).toString());
		num1.setImageResource(number1_red.get(blink1_counter));
		//num1.setImageResource((R.drawable.one11));
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		    @Override
		    public void run() 
		    {
		        // Do something after 5s = 5000ms
		    	ImageView num1 = (ImageView)findViewById(R.id.imageView2);
		    	num1.setImageResource(number1.get(blink1_counter));
		    	//num1.setImageResource(R.drawable.one1);
		    	blink1_counter++;
		    }
		}, 500);
		//Log.e("Blink1End",((Integer)blink1_counter).toString());
		//blink1_counter++;
	}
	public void blink2()
	{
		ImageView num2 = (ImageView)findViewById(R.id.imageView4);
		num2.setImageResource(number2_red.get(blink2_counter));
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		    @Override
		    public void run() 
		    {
		        // Do something after 5s = 5000ms
		    	ImageView num2 = (ImageView)findViewById(R.id.imageView4);
		    	num2.setImageResource(number2.get(blink2_counter));
		    	blink2_counter++;
		    }
		}, 500);
		
		
	}
	public void screenset()
	{
		ImageView n1 = (ImageView)findViewById(R.id.imageView2); //do not change
		n1.requestLayout();
		n1.getLayoutParams().height = height/5;
		n1.getLayoutParams().width = width/6;
		//n1.setPadding(0, 0, 10, 0);
		
		ImageView eq = (ImageView)findViewById(R.id.imageView5); //DNC
		eq.requestLayout();
		eq.getLayoutParams().height = height/7;
		eq.getLayoutParams().width = width/8;
		//eq.setPadding(0, 0, 10, 0);
		
		ImageView plus = (ImageView)findViewById(R.id.imageView3); // DNC
		plus.requestLayout();
		plus.getLayoutParams().height = height/7;
		plus.getLayoutParams().width = width/7;
	//	plus.setPadding(0, 0, 10, 0);
		
		ImageView n2 = (ImageView)findViewById(R.id.imageView4); //DNC
		n2.requestLayout();
		n2.getLayoutParams().height = height/5;
		n2.getLayoutParams().width = width/6;
	//	n2.setPadding(0, 0, 10, 0);
	
		
		ImageView ans = (ImageView)findViewById(R.id.imageView6); //DNC question or answer
		ans.requestLayout();
		ans.getLayoutParams().height = height/5;
		ans.getLayoutParams().width = width/6;
		//ans.setPadding(0, 0, 10, 0);
		
		ImageView tick = (ImageView)findViewById(R.id.imageView1); //DNC
		tick.requestLayout();
		tick.getLayoutParams().height = height/8;
		tick.getLayoutParams().width = width/8;
		
		ImageView b1 = (ImageView)findViewById(R.id.imageButton1);
		b1.requestLayout();
		b1.getLayoutParams().height = height/8;
		b1.getLayoutParams().width = width/8;
		
		
	}
	
	public void ListInitialise()
	{
		number1.add(R.drawable.one1);
		number1.add(R.drawable.one1);
		number1.add(R.drawable.one1);
		number1.add(R.drawable.four4);
		
		number1.add(R.drawable.two2);
		number1.add(R.drawable.five5);
		number1.add(R.drawable.five5);
		number1.add(R.drawable.three3);
		
		number1_red.add(R.drawable.one11);
		number1_red.add(R.drawable.one11);
		number1_red.add(R.drawable.one11);
		number1_red.add(R.drawable.four44);		
		number1_red.add(R.drawable.two22);
		number1_red.add(R.drawable.five55);
		number1_red.add(R.drawable.five55);
		number1_red.add(R.drawable.three33);
		
		number2.add(R.drawable.two2);
		number2.add(R.drawable.three3);
		number2.add(R.drawable.one1);
		number2.add(R.drawable.one1);
		
		number2.add(R.drawable.three3);
		number2.add(R.drawable.two2);
		number2.add(R.drawable.four4);
		number2.add(R.drawable.four4);
		
		number2_red.add(R.drawable.two22);
		number2_red.add(R.drawable.three33);
		number2_red.add(R.drawable.one11);
		number2_red.add(R.drawable.one11);
		number2_red.add(R.drawable.three33);
		number2_red.add(R.drawable.two22);
		number2_red.add(R.drawable.four44);
		number2_red.add(R.drawable.four44);
		
		answer.add(R.drawable.three3);
		answer.add(R.drawable.four4);
		answer.add(R.drawable.two2);
		answer.add(R.drawable.five5);
		answer.add(R.drawable.five5);
		answer.add(R.drawable.seven7);
		answer.add(R.drawable.nine9);
		answer.add(R.drawable.seven7);
	}
}