package com.example.numberbonds;

import java.util.ArrayList;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
public class One extends Activity {
	int height;
	int width;
	int counter=0;
	int level;
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	ArrayList<Integer> answers = new ArrayList<Integer>();
	ArrayList<Integer> sound1 = new ArrayList<Integer>();
	ArrayList<Integer> sound2 = new ArrayList<Integer>();
	ArrayList<Integer> singleImage = new ArrayList<Integer>();
	ArrayList<Integer> groupImage = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_one);
		Bundle bundle = getIntent().getExtras();
		level = bundle.getInt("level");
		System.out.println("Level in One:"+level);
		getArrays();
				
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels; //432
		width = displaymetrics.widthPixels;	  //800
		Log.e("Dim:",((Integer)height).toString()+","+((Integer)width).toString());
		screenset();
		View vw = new View(getBaseContext());
		next(vw);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void previous(View view) {
		counter--;
		counter--;
		View vw = new View(getBaseContext());
		next(vw);
      }
	/*
	 * Displays the next numberbond.
	 */
	public void next(View view) {
		if(counter>=10 || counter < 0 )
		{
			counter=0;
			Intent intent = new Intent(this, MainActivity.class);    
			finish();
			startActivity(intent);
		}
		else 
		{
			screenset();
			Log.e("Counter was:",((Integer)counter).toString());
			ImageView n1 = (ImageView)findViewById(R.id.imageView1);
			ImageView eq = (ImageView)findViewById(R.id.imageView6);
			ImageView plus = (ImageView)findViewById(R.id.imageView2);
			ImageView n2 = (ImageView)findViewById(R.id.imageView3);
			ImageView i2 = (ImageView)findViewById(R.id.imageView4);
			ImageView igroup = (ImageView)findViewById(R.id.imageView8);
			ImageView ans = (ImageView)findViewById(R.id.imageView5);
			MediaPlayer n1_mp = MediaPlayer.create(this, sound1.get(counter));
			
			n1.setImageResource((int)numbers.get(counter));
			n1_mp.start();
			igroup.setImageResource((int)groupImage.get(counter));
			
			n1.setVisibility(View.VISIBLE);
			igroup.setVisibility(View.VISIBLE);
			
			n2.setVisibility(View.INVISIBLE);
			eq.setVisibility(View.INVISIBLE);
			ans.setVisibility(View.INVISIBLE);
			plus.setVisibility(View.INVISIBLE);
			i2.setVisibility(View.INVISIBLE);
			
			i2.setImageResource((int)singleImage.get(counter));
			ans.setImageResource((int)answers.get(counter));
			
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
			    @Override
			    public void run() 
			    {
			        // Do something after 5s = 5000ms
			    	
					ImageView n2 = (ImageView)findViewById(R.id.imageView3);
					if(level==1)
					{
						n2.setImageResource(R.drawable.number_1);
					}
					if(level==2)
					{
						n2.setImageResource(R.drawable.number_2);
					}
					if(level==3)
					{
						n2.setImageResource(R.drawable.number_3);
					}
					if(level==4)
					{
						n2.setImageResource(R.drawable.number_4);
					}
					if(level==5)
					{
						n2.setImageResource(R.drawable.number_5);
					}
					ImageView i2 = (ImageView)findViewById(R.id.imageView4);
					
					ImageView plus = (ImageView)findViewById(R.id.imageView2);
					getSound1();
					n2.setVisibility(View.VISIBLE);
			    	plus.setVisibility(View.VISIBLE);				
					i2.setVisibility(View.VISIBLE);
			       
			    }
			}, 1000);
			//final Handler handler2 = new Handler();
			handler.postDelayed(new Runnable() {
			    @Override
				public void run() 
			    {
			        // Do something after 5s = 5000ms
			    	ImageView ans = (ImageView)findViewById(R.id.imageView5);
			    	ImageView eq = (ImageView)findViewById(R.id.imageView6);
			    	getSound2();
			    	eq.setVisibility(View.VISIBLE);
			    	ans.setVisibility(View.VISIBLE);
					eq.setVisibility(View.VISIBLE);
					counter++;
			       
			    }
			}, 2500);
			
		}
        
      }
	public void screenset()
	{
		ImageView n1 = (ImageView)findViewById(R.id.imageView1); //do not change
		n1.requestLayout();
		n1.getLayoutParams().height = height/4;
		n1.getLayoutParams().width = width/5;
		
		ImageView eq = (ImageView)findViewById(R.id.imageView6); //DNC
		eq.requestLayout();
		eq.getLayoutParams().height = height/8;
		eq.getLayoutParams().width = width/16 + width/32;
		
		ImageView plus = (ImageView)findViewById(R.id.imageView2); // DNC
		plus.requestLayout();
		plus.getLayoutParams().height = height/8;
		plus.getLayoutParams().width = width/8;
		
		ImageView n2 = (ImageView)findViewById(R.id.imageView3); //DNC
		n2.requestLayout();
		n2.getLayoutParams().height = height/4;
		n2.getLayoutParams().width = width/5;
		
		
		ImageView i1 = (ImageView)findViewById(R.id.imageView8); //group image
		i1.requestLayout();
		i1.getLayoutParams().height = height/3;
		i1.getLayoutParams().width = width/3 ;
		
		
		if(counter+1 < 3)
		{
			Log.e("First","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/3;
			i2.getLayoutParams().width = width/3;
		}
		if(counter+1==3)
		{
			Log.e("Second","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/3;
			i2.getLayoutParams().width = width/6;
		}
		if(counter+1 >3 && counter != 7)
		{
			Log.e("Third","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/5;
			i2.getLayoutParams().width = width/6;
		}
		if(counter==7)
		{
			Log.e("Fourth","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/6;
			i2.getLayoutParams().width = width/7;
			
		}
		if(level==2)
			getImageSize2();
		if(level==3)
			getImageSize3();
		if(level==4)
			getImageSize4();
		if(level==5)
			getImageSize5();

		
		ImageView ans = (ImageView)findViewById(R.id.imageView5); //DNC
		ans.requestLayout();
		ans.getLayoutParams().height = height/4;
		ans.getLayoutParams().width = width/6;
		
		ImageView b1 = (ImageView)findViewById(R.id.imageButton1);
		b1.requestLayout();
		b1.getLayoutParams().height = height/8;
		b1.getLayoutParams().width = width/8;
		
		ImageView b2 = (ImageView)findViewById(R.id.imageButton2);
		b2.requestLayout();
		b2.getLayoutParams().height = height/8;
		b2.getLayoutParams().width = width/8;
	}
	private void getImageSize5() {
		
		if(counter>=2 || counter==0)
		{
			Log.e("Third level 4","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/3;
			i2.getLayoutParams().width = width/3;
		}
		else
		{
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/3;
			i2.getLayoutParams().width = width/3;
			
			ImageView i1 = (ImageView)findViewById(R.id.imageView8); //group image
			i1.requestLayout();
			i1.getLayoutParams().height = height/5;
			i1.getLayoutParams().width = width/3 ;
		}
	}
	private void getImageSize4() {
				
		if(counter!=5 && counter!=9)
		{
			Log.e("Third level 4","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/3;
			i2.getLayoutParams().width = width/3;
		}
		if(counter==5 || counter ==9)
		{
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/3;
			i2.getLayoutParams().width = width/5;

			
		}
		
		
	}

	private void getImageSize3() {
		if(counter!=7 && counter!=9)
		{
			Log.e("First","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/3;
			i2.getLayoutParams().width = width/3;
		}
		
		
		if(counter==7 || counter ==9)
		{
			Log.e("Third","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/5;
			i2.getLayoutParams().width = width/3;
		}
		
	}

	private void getImageSize2() {
		if(counter+1 < 4)
		{
			Log.e("First","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/3;
			i2.getLayoutParams().width = width/3;
		}
		
		if(counter+1 >3 && counter!=7)
		{
			Log.e("Third","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/5;
			i2.getLayoutParams().width = width/6;
		}
		if(counter==7)
		{
			Log.e("Third","Used");
			ImageView i2 = (ImageView)findViewById(R.id.imageView4); //single Image
			i2.requestLayout();
			i2.getLayoutParams().height = height/4;
			i2.getLayoutParams().width = width/4;
		}
		
	}

	public void onBackPressed()
	{
	   //logic here, for example an intent
	   Intent intent = new Intent(this, LevelsDisplay.class);    
	   finish();
	   startActivity(intent);   
	}
	public void getSound1()
	{
		
			MediaPlayer n1_mp;
			if(level==1)
			{
				n1_mp = MediaPlayer.create(this, R.raw.plus_one);
				n1_mp.start();
			}
			else if(level==2)
			{
				n1_mp = MediaPlayer.create(this, R.raw.plus_two_en);
				n1_mp.start();
			}
			else if(level==3)
			{
				n1_mp = MediaPlayer.create(this, R.raw.plus_three_en);
				n1_mp.start();
			}
			else if(level==4)
			{
				n1_mp = MediaPlayer.create(this, R.raw.plus_four_en);
				n1_mp.start();
			}
			else if(level==5)
			{
				n1_mp = MediaPlayer.create(this, R.raw.plus_five_en);
				n1_mp.start();
			}
	}
	public void getSound2()
	{
		MediaPlayer mp = MediaPlayer.create(this, sound2.get(counter));
		mp.start();
	}
	public void getArrays()
	{
		if(level==1)
		{
			numbers.add(R.drawable.number_0);		numbers.add(R.drawable.number_1); 		numbers.add(R.drawable.number_2);
			numbers.add(R.drawable.number_3);		numbers.add(R.drawable.number_4);		numbers.add(R.drawable.number_5);
			numbers.add(R.drawable.number_6);		numbers.add(R.drawable.number_7);		numbers.add(R.drawable.number_8);
			numbers.add(R.drawable.number_9); 		
			
			answers.add(R.drawable.number_1); 		answers.add(R.drawable.number_2);
			answers.add(R.drawable.number_3); 		answers.add(R.drawable.number_4); 		answers.add(R.drawable.number_5);
			answers.add(R.drawable.number_6); 		answers.add(R.drawable.number_7); 		answers.add(R.drawable.number_8);
			answers.add(R.drawable.number_9); 		answers.add(R.drawable.ten2);
			
			singleImage.add(R.drawable.elephant_3_gray); 		singleImage.add(R.drawable.bumble_bee); 		singleImage.add(R.drawable.fairy);
			singleImage.add(R.drawable.bird_brown_cute); 		singleImage.add(R.drawable.mouse_gray); 		singleImage.add(R.drawable.red);
			singleImage.add(R.drawable.tree_pine); 				singleImage.add(R.drawable.snail_2); 			singleImage.add(R.drawable.pepper);
			singleImage.add(R.drawable.carrot);
			
			groupImage.add(R.drawable.transparent); 				groupImage.add(R.drawable.bumble_bee); 				groupImage.add(R.drawable.fairy_group2);
			groupImage.add(R.drawable.bird_brown_cute_group3); 		groupImage.add(R.drawable.mouse_gray_group4); 		groupImage.add(R.drawable.red_group);
			groupImage.add(R.drawable.tree_pine_group6); 			groupImage.add(R.drawable.snail_2_group7);			groupImage.add(R.drawable.pepper_group9);
			groupImage.add(R.drawable.carrot_group9);
			
			sound1.add(R.raw.zero_en); 		sound1.add(R.raw.one_en); 		sound1.add(R.raw.two_en); 		sound1.add(R.raw.three_en);
			sound1.add(R.raw.four_en); 		sound1.add(R.raw.five_en); 		sound1.add(R.raw.six_en); 		sound1.add(R.raw.seven_en);
			sound1.add(R.raw.eight_en); 	sound1.add(R.raw.nine_en); 
			
			sound2.add(R.raw.eq_one_en); 		sound2.add(R.raw.eq_two_en); 		sound2.add(R.raw.eq_three_en); 		sound2.add(R.raw.eq_four_en);
			sound2.add(R.raw.eq_five_en); 		sound2.add(R.raw.eq_six_en); 		sound2.add(R.raw.eq_seven_en); 		sound2.add(R.raw.eq_eight_en);
			sound2.add(R.raw.eq_nine_en);		sound2.add(R.raw.eq_ten_en);
		}
		else if(level==2)
		{
				numbers.add(R.drawable.number_0); 				numbers.add(R.drawable.number_1); 				numbers.add(R.drawable.number_2);
				numbers.add(R.drawable.number_3); 				numbers.add(R.drawable.number_4); 				numbers.add(R.drawable.number_5);
				numbers.add(R.drawable.number_6); 				numbers.add(R.drawable.number_7); 				numbers.add(R.drawable.number_8);
				numbers.add(R.drawable.number_9); 
				
				answers.add(R.drawable.number_2); 				answers.add(R.drawable.number_3); 				answers.add(R.drawable.number_4);
				answers.add(R.drawable.number_5); 				answers.add(R.drawable.number_6); 				answers.add(R.drawable.number_7);
				answers.add(R.drawable.number_8); 				answers.add(R.drawable.number_9); 				answers.add(R.drawable.ten2);
				answers.add(R.drawable.eleven);
				
				singleImage.add(R.drawable.octopus2); 				singleImage.add(R.drawable.chick2); 				singleImage.add(R.drawable.bunny2);
				singleImage.add(R.drawable.apple2); 				singleImage.add(R.drawable.cake2); 				singleImage.add(R.drawable.koala2);
				singleImage.add(R.drawable.mango_tree2); 				singleImage.add(R.drawable.cater2); 				singleImage.add(R.drawable.daisy2);
				singleImage.add(R.drawable.green_chili2);
				
				groupImage.add(R.drawable.transparent); 				groupImage.add(R.drawable.chick); 				groupImage.add(R.drawable.bunny2);
				groupImage.add(R.drawable.apple3); 				groupImage.add(R.drawable.cake4); 				groupImage.add(R.drawable.koala5);
				groupImage.add(R.drawable.mango_tree6); 				groupImage.add(R.drawable.cater7); 				groupImage.add(R.drawable.daisy8);
				groupImage.add(R.drawable.green_chili9); 				
				
				sound1.add(R.raw.zero_en); 				sound1.add(R.raw.one_en); 				sound1.add(R.raw.two_en);
				sound1.add(R.raw.three_en); 				sound1.add(R.raw.four_en); 				sound1.add(R.raw.five_en);
				sound1.add(R.raw.six_en); 				sound1.add(R.raw.seven_en); 				sound1.add(R.raw.eight_en);
				sound1.add(R.raw.nine_en);
				 				
				sound2.add(R.raw.eq_two_en); 				sound2.add(R.raw.eq_three_en); 				sound2.add(R.raw.eq_four_en);
				sound2.add(R.raw.eq_five_en); 				sound2.add(R.raw.eq_six_en); 				sound2.add(R.raw.eq_seven_en);
				sound2.add(R.raw.eq_eight_en); 				sound2.add(R.raw.eq_nine_en); 				sound2.add(R.raw.eq_ten_en);
				sound2.add(R.raw.eq_eleven_en);
			
		}
		else if(level==3)
		{
				numbers.add(R.drawable.number_0);  				numbers.add(R.drawable.number_1); 				numbers.add(R.drawable.number_2);
				numbers.add(R.drawable.number_3); 				numbers.add(R.drawable.number_4);  				numbers.add(R.drawable.number_5);
				numbers.add(R.drawable.number_6); 				numbers.add(R.drawable.number_7); 				numbers.add(R.drawable.number_8);
				numbers.add(R.drawable.number_9);
				
				
				answers.add(R.drawable.number_3); 				answers.add(R.drawable.number_4); 				answers.add(R.drawable.number_5);
				answers.add(R.drawable.number_6);  				answers.add(R.drawable.number_7); 				answers.add(R.drawable.number_8);
				answers.add(R.drawable.number_9); 				answers.add(R.drawable.ten2); 				answers.add(R.drawable.eleven);
				answers.add(R.drawable.twelve);
				
				singleImage.add(R.drawable.teddy3); 				singleImage.add(R.drawable.donut3); 				singleImage.add(R.drawable.birdie3); 
				singleImage.add(R.drawable.tomato3); 				singleImage.add(R.drawable.pear3); 				singleImage.add(R.drawable.dolphin3);
				singleImage.add(R.drawable.flow3); 				singleImage.add(R.drawable.tulip3); 				singleImage.add(R.drawable.mushroom3);
				singleImage.add(R.drawable.palm3); 
				
				groupImage.add(R.drawable.transparent); 				groupImage.add(R.drawable.donut_strawberry_sprinkles);			groupImage.add(R.drawable.birdie2);
				groupImage.add(R.drawable.tomato3); 				groupImage.add(R.drawable.pear4); 				groupImage.add(R.drawable.dolphin5);
				groupImage.add(R.drawable.flow6); 				groupImage.add(R.drawable.tulip7); 				groupImage.add(R.drawable.mushroom8);
				groupImage.add(R.drawable.palm9);
				
				sound1.add(R.raw.zero_en); 				sound1.add(R.raw.one_en); 				sound1.add(R.raw.two_en); 				sound1.add(R.raw.three_en);
				sound1.add(R.raw.four_en); 				sound1.add(R.raw.five_en); 				sound1.add(R.raw.six_en);				sound1.add(R.raw.seven_en);
				sound1.add(R.raw.eight_en);  			sound1.add(R.raw.nine_en); 				
				
				sound2.add(R.raw.eq_three_en); 				sound2.add(R.raw.eq_four_en); 				sound2.add(R.raw.eq_five_en); 				sound2.add(R.raw.eq_six_en);
				sound2.add(R.raw.eq_seven_en); 				sound2.add(R.raw.eq_eight_en); 				sound2.add(R.raw.eq_nine_en);
				sound2.add(R.raw.eq_ten_en); 				sound2.add(R.raw.eq_eleven_en); 				sound2.add(R.raw.eq_twelve_en);
		}
		else if(level==4)
		{
			
				numbers.add(R.drawable.number_0);
				numbers.add(R.drawable.number_1);
				numbers.add(R.drawable.number_2);
				numbers.add(R.drawable.number_3);
				numbers.add(R.drawable.number_4);
				numbers.add(R.drawable.number_5);
				numbers.add(R.drawable.number_6);
				numbers.add(R.drawable.number_7);
				numbers.add(R.drawable.number_8);
				numbers.add(R.drawable.number_9);
				
				answers.add(R.drawable.number_4);
				answers.add(R.drawable.number_5);
				answers.add(R.drawable.number_6);
				answers.add(R.drawable.number_7);
				answers.add(R.drawable.number_8);
				answers.add(R.drawable.number_9);
				answers.add(R.drawable.ten2);
				answers.add(R.drawable.eleven);
				answers.add(R.drawable.twelve);
				answers.add(R.drawable.thirteen);
				
				singleImage.add(R.drawable.scooter4); //0+3
				singleImage.add(R.drawable.crow4); //1+3
				singleImage.add(R.drawable.frog4); //2+3
				singleImage.add(R.drawable.cat4);
				singleImage.add(R.drawable.shark4);	//4+3
				singleImage.add(R.drawable.fish4); //
				singleImage.add(R.drawable.dog4);
				singleImage.add(R.drawable.snake4);
				singleImage.add(R.drawable.bottle4);
				singleImage.add(R.drawable.ant4);
				
				groupImage.add(R.drawable.transparent);
				groupImage.add(R.drawable.crow);
				groupImage.add(R.drawable.frog2);
				groupImage.add(R.drawable.cat3);
				groupImage.add(R.drawable.shark4);
				groupImage.add(R.drawable.fish5);
				groupImage.add(R.drawable.dog6);
				groupImage.add(R.drawable.snake7);
				groupImage.add(R.drawable.bottle8);
				groupImage.add(R.drawable.ant9);
				
				sound1.add(R.raw.zero_en);
				sound1.add(R.raw.one_en);
				sound1.add(R.raw.two_en);
				sound1.add(R.raw.three_en);
				sound1.add(R.raw.four_en);
				sound1.add(R.raw.five_en);
				sound1.add(R.raw.six_en);
				sound1.add(R.raw.seven_en);
				sound1.add(R.raw.eight_en);
				sound1.add(R.raw.nine_en);
				
				sound2.add(R.raw.eq_four_en);
				sound2.add(R.raw.eq_five_en);
				sound2.add(R.raw.eq_six_en);
				sound2.add(R.raw.eq_seven_en);
				sound2.add(R.raw.eq_eight_en);
				sound2.add(R.raw.eq_nine_en);
				sound2.add(R.raw.eq_ten_en);
				sound2.add(R.raw.eq_eleven_en);
				sound2.add(R.raw.eq_twelve_en);
				sound2.add(R.raw.eq_thirteen_en);
				
			}
			else if(level==5)
			{
				
					numbers.add(R.drawable.number_0);
					numbers.add(R.drawable.number_1);
					numbers.add(R.drawable.number_2);
					numbers.add(R.drawable.number_3);
					numbers.add(R.drawable.number_4);
					numbers.add(R.drawable.number_5);
					numbers.add(R.drawable.number_6);
					numbers.add(R.drawable.number_7);
					numbers.add(R.drawable.number_8);
					numbers.add(R.drawable.number_9);
					
					answers.add(R.drawable.number_5);
					answers.add(R.drawable.number_6);
					answers.add(R.drawable.number_7);
					answers.add(R.drawable.number_8);
					answers.add(R.drawable.number_9);
					answers.add(R.drawable.ten2);
					answers.add(R.drawable.eleven);
					answers.add(R.drawable.twelve);
					answers.add(R.drawable.thirteen);
					answers.add(R.drawable.fourteen);
					
					singleImage.add(R.drawable.owl5); //0+3
					singleImage.add(R.drawable.sun5); //1+3
					singleImage.add(R.drawable.whale5); //2+3
					singleImage.add(R.drawable.camel6);
					singleImage.add(R.drawable.teapot5);	//4+3
					singleImage.add(R.drawable.turtle5); //
					singleImage.add(R.drawable.balloon5);
					singleImage.add(R.drawable.ballerina5);
					singleImage.add(R.drawable.eggplant5);
					singleImage.add(R.drawable.corn5);
					
					groupImage.add(R.drawable.transparent);
					groupImage.add(R.drawable.sun2);
					groupImage.add(R.drawable.whale2);
					groupImage.add(R.drawable.camel3);
					groupImage.add(R.drawable.teapot4);
					groupImage.add(R.drawable.turtle5);
					groupImage.add(R.drawable.balloon6);
					groupImage.add(R.drawable.ballerina7);
					groupImage.add(R.drawable.eggplant7);
					groupImage.add(R.drawable.corn9);
					
					sound1.add(R.raw.zero_en);
					sound1.add(R.raw.one_en);
					sound1.add(R.raw.two_en);
					sound1.add(R.raw.three_en);
					sound1.add(R.raw.four_en);
					sound1.add(R.raw.five_en);
					sound1.add(R.raw.six_en);
					sound1.add(R.raw.seven_en);
					sound1.add(R.raw.eight_en);
					sound1.add(R.raw.nine_en);
					
					sound2.add(R.raw.eq_five_en);
					sound2.add(R.raw.eq_six_en);
					sound2.add(R.raw.eq_seven_en);
					sound2.add(R.raw.eq_eight_en);
					sound2.add(R.raw.eq_nine_en);
					sound2.add(R.raw.eq_ten_en);
					sound2.add(R.raw.eq_eleven_en);
					sound2.add(R.raw.eq_twelve_en);
					sound2.add(R.raw.eq_thirteen_en);
					sound2.add(R.raw.eq_fourteen_en);
					
				
		}
	}

}
