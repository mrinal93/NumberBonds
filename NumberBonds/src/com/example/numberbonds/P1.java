package com.example.numberbonds;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

public class P1 extends Activity {
	ArrayList<Integer> number1 = new ArrayList<Integer>();
	ArrayList<Integer> number2 = new ArrayList<Integer>();
	ArrayList<Integer> o1_n = new ArrayList<Integer>();
	ArrayList<Integer> o2_n = new ArrayList<Integer>();
	ArrayList<Integer> o3_n = new ArrayList<Integer>();
	ArrayList<Integer> answer = new ArrayList<Integer>();
	ArrayList<Integer> value1 = new ArrayList<Integer>();
	ArrayList<Integer> value2 = new ArrayList<Integer>();
	ArrayList<Integer> value3 = new ArrayList<Integer>();
	ArrayList<Integer> o1 = new ArrayList<Integer>();
	ArrayList<Integer> o2 = new ArrayList<Integer>();
	ArrayList<Integer> o3 = new ArrayList<Integer>();
	ArrayList<String> ques = new ArrayList<String>();
	ArrayList<ArrayList<Integer>> choices = new ArrayList<ArrayList<Integer>>();
	public int attempts = 0, qno, right = 0, wrong = 0;
	public String name;
	int height;
	int width;
	TestAdapter db;
	private String id;
	private int flag=1;
	int question=0;
	EditText input;
	int level;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_p1);
		db=new TestAdapter(this);
		db.createDatabase();
		Bundle bundle = getIntent().getExtras();
		level = bundle.getInt("level");	
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels; //432
		width = displaymetrics.widthPixels-40;	  //800
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Enter your name");
		alert.setCancelable(false);
		
		input = new EditText(this);
		input.setGravity(Gravity.CENTER);
		alert.setView(input);
		alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                // Write your code here to execute after dialog)
            }
		});
		
		alert.show();
		MediaPlayer mp = MediaPlayer.create(this, R.raw.practice_en);
		mp.start();
		loadActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.p1, menu);
		return true;
	}
	public void loadActivity()
	{
		ArrayIntialise();
		//name = new String("Mrinal");
		attempts=0;
		right=0;
		//wrong=0;
		qno=question+1;
		screenset();
		ImageView option2_ans = (ImageView) findViewById(R.id.tick);
		ImageView ques = (ImageView) findViewById(R.id.question);
		ImageView option1_ans = (ImageView) findViewById(R.id.wrong1);
		ImageView option3_ans = (ImageView) findViewById(R.id.wrong2);
		ImageView n1 = (ImageView) findViewById(R.id.imageView1);
		ImageView plus = (ImageView) findViewById(R.id.imageView2);
		ImageView n2 = (ImageView) findViewById(R.id.imageView3);
		ImageView equals = (ImageView) findViewById(R.id.imageView6);
		ImageView option1 = (ImageView) findViewById(R.id.option1);
		ImageView option2 = (ImageView) findViewById(R.id.option2);
		ImageView option3 = (ImageView) findViewById(R.id.option3);

		
		option1_ans.setVisibility(View.INVISIBLE);
		option2_ans.setVisibility(View.INVISIBLE);
		option3_ans.setVisibility(View.INVISIBLE);		
		
		n1.setImageResource(number1.get(question));
		if(level==1)
			n2.setImageResource(R.drawable.number_1);
		else if(level==2)
			n2.setImageResource(R.drawable.number_2);
		else if(level==3)
			n2.setImageResource(R.drawable.number_3);
		else if(level==4)
			n2.setImageResource(R.drawable.number_4);
		else if(level==5)
			n2.setImageResource(R.drawable.number_5);
		option1.setImageResource(o1.get(question));
		option2.setImageResource(o2.get(question));
		option3.setImageResource(o3.get(question));
		ques.setImageResource(R.drawable.question);
		
		ques.setVisibility(View.VISIBLE);
		n1.setVisibility(View.VISIBLE);
		plus.setVisibility(View.VISIBLE);
		n2.setVisibility(View.VISIBLE);
		equals.setVisibility(View.VISIBLE);
		qno=question+1;
		Log.e("LoadActivity","Question:"+((Integer)question).toString());
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
	
		
		ImageView ans = (ImageView)findViewById(R.id.question); //DNC question or answer
		ans.requestLayout();
		ans.getLayoutParams().height = height/4;
		ans.getLayoutParams().width = width/6;
		
		ImageView op1 = (ImageView)findViewById(R.id.option1); //option1
		op1.requestLayout();
		op1.getLayoutParams().height = height/4+height/8;
		op1.getLayoutParams().width = width/8+width/32;
		//op1.set(0, 0, 15, 0);
		
		ImageView op2 = (ImageView)findViewById(R.id.option2); //option2
		op2.requestLayout();
		op2.getLayoutParams().height = height/4+height/8;
		op2.getLayoutParams().width = width/8+width/32;
		
		
		ImageView op3 = (ImageView)findViewById(R.id.option3); //option3
		op3.requestLayout();
		op3.getLayoutParams().height = height/4+height/8;
		op3.getLayoutParams().width = width/8+width/32;
		
		ImageView sym1 = (ImageView)findViewById(R.id.wrong1); //symbol1
		sym1.requestLayout();
		sym1.getLayoutParams().height = height/16+height/32;
		sym1.getLayoutParams().width = width/8;
		
		ImageView sym2 = (ImageView)findViewById(R.id.tick); //symbol2
		sym2.requestLayout();
		sym2.getLayoutParams().height = height/16+height/32;
		sym2.getLayoutParams().width = width/8;
		
		ImageView sym3 = (ImageView)findViewById(R.id.wrong2); //symbol3
		sym3.requestLayout();
		sym3.getLayoutParams().height = height/16+height/32;
		sym3.getLayoutParams().width = width/8;
		
		
		ImageView b1 = (ImageView)findViewById(R.id.imageButton1);
		b1.requestLayout();
		b1.getLayoutParams().height = height/8;
		b1.getLayoutParams().width = width/10;
		
		ImageView b2 = (ImageView)findViewById(R.id.imageButton2);
		b2.requestLayout();
		b2.getLayoutParams().height = height/8;
		b2.getLayoutParams().width = width/10;
	}

	public void button1(View view)
	{
		ImageView option1_ans = (ImageView) findViewById(R.id.wrong1);
		ImageView option2_ans = (ImageView) findViewById(R.id.tick);
		ImageView option3_ans = (ImageView) findViewById(R.id.wrong2);
		option2_ans.setVisibility(View.INVISIBLE);
		option3_ans.setVisibility(View.INVISIBLE);
		ImageView ans = (ImageView) findViewById(R.id.question);
		if((choices.get(question)).get(0)==1)
		{
			MediaPlayer mp = MediaPlayer.create(this,R.raw.correct_en);
			mp.start();
			attempts++;
			right++;
			
			option1_ans.setImageResource(R.drawable.tick);
			
			option1_ans.setVisibility(View.VISIBLE);
			ans.setImageResource(answer.get(question));
			ans.setVisibility(View.VISIBLE);
		}
		else
		{
			MediaPlayer mp = MediaPlayer.create(this,R.raw.wrong_en);
			mp.start();
			attempts++;
			wrong++;
			option1_ans.setImageResource(R.drawable.x);
			option1_ans.setVisibility(View.VISIBLE);
			ans.setImageResource(R.drawable.question);
			ans.setVisibility(View.VISIBLE);
		}
		
		if(flag==1)
		{
			db.open();
			name = new String(input.getText().toString());
			id =new String (db.insertStudent(name,ques));
			flag=0;
		}
		db.open();
		System.out.println("Before Update");
		int q = question + 1;
		int ID = Integer.parseInt(id);
		String l = ((Integer)level).toString();
		String m = ((Integer)wrong).toString();
		db.update(l,ID, o1_n.get(question).toString()+";", "question"+((Integer)q).toString(), m);
		//db.update(level,ID, o1_n.get(question).toString()+";", "question"+((Integer)q).toString(), wrong);
		Log.e("AddData-id,response,qno,mistakes",id+","+ o1_n.get(q-1).toString()+","+
		"question"+((Integer)q).toString()+","+	((Integer)wrong).toString());
		db.close();
	}
	public void button2(View view)
	{
		ImageView option2_ans = (ImageView) findViewById(R.id.tick);
		ImageView option1_ans = (ImageView) findViewById(R.id.wrong1);
		ImageView option3_ans = (ImageView) findViewById(R.id.wrong2);
		option1_ans.setVisibility(View.INVISIBLE);
		option3_ans.setVisibility(View.INVISIBLE);
		ImageView ans = (ImageView) findViewById(R.id.question);
		if((choices.get(question)).get(1)==1)
		{
			MediaPlayer mp = MediaPlayer.create(this,R.raw.correct_en);
			mp.start();
			attempts++;
			right++;
			option2_ans.setImageResource(R.drawable.tick);
			option2_ans.setVisibility(View.VISIBLE);
			ans.setImageResource(answer.get(question));
			ans.setVisibility(View.VISIBLE);
		}
		else
		{
			MediaPlayer mp = MediaPlayer.create(this,R.raw.wrong_en);
			mp.start();
			attempts++;
			wrong++;
			option2_ans.setImageResource(R.drawable.x);
			option2_ans.setVisibility(View.VISIBLE);
			ans.setImageResource(R.drawable.question);
			ans.setVisibility(View.VISIBLE);
		}
		db.open();
		if(flag==1)
		{
			name = new String(input.getText().toString());
			id =new String (db.insertStudent(name,ques));
			flag=0;
		}
		
		int q = question + 1;
		int ID = Integer.parseInt(id);
		//db.update(level,ID, o2_n.get(question).toString()+";", "question"+((Integer)q).toString(), wrong);
		String l = ((Integer)level).toString();
		String m = ((Integer)wrong).toString();
		db.update(l,ID, o2_n.get(question).toString()+";", "question"+((Integer)q).toString(), m);
		Log.e("AddData-id,response,qno,mistakes",id+","+ o2_n.get(q-1).toString()+","+
		"question"+((Integer)q).toString()+","+	((Integer)wrong).toString());
		db.close();
	}
	public void button3(View view)
	{
		Log.e("Button3","Executed!");
		ImageView option3_ans = (ImageView) findViewById(R.id.wrong2);
		ImageView option1_ans = (ImageView) findViewById(R.id.wrong1);
		ImageView option2_ans = (ImageView) findViewById(R.id.tick);
		
		option2_ans.setVisibility(View.INVISIBLE);
		option1_ans.setVisibility(View.INVISIBLE);
		ImageView ans = (ImageView) findViewById(R.id.question);
		if((choices.get(question)).get(2)==1)
		{
			MediaPlayer mp = MediaPlayer.create(this,R.raw.correct_en);
			mp.start();
			attempts++;
			right++;
			option3_ans.setImageResource(R.drawable.tick);
			option3_ans.setVisibility(View.VISIBLE);
			ans.setImageResource(answer.get(question));
			ans.setVisibility(View.VISIBLE);
		}
		else
		{
			MediaPlayer mp = MediaPlayer.create(this,R.raw.wrong_en);
			mp.start();
			attempts++;
			wrong++;
			option3_ans.setImageResource(R.drawable.x);
			option3_ans.setVisibility(View.VISIBLE);
			ans.setImageResource(R.drawable.question);
			ans.setVisibility(View.VISIBLE);
		}
		db.open();
		if(flag==1)
		{
			name = new String(input.getText().toString());
			id =new String (db.insertStudent(name,ques));
			flag=0;
		}
		int q = question + 1;
		int ID = Integer.parseInt(id);
		String l = ((Integer)level).toString();
		String m = ((Integer)wrong).toString();
		db.update(l,ID, o3_n.get(question).toString()+";", "question"+((Integer)q).toString(), m);
		//db.update(level,ID, o3_n.get(question).toString()+";", "question"+((Integer)q).toString(), wrong);
		Log.e("AddData-id,response,qno,mistakes",id+","+ o3_n.get(q-1).toString()+","+
		"question"+((Integer)q).toString()+","+	((Integer)wrong).toString());
		db.close();
	}
	public void wrong2(View view){
		ImageView tick = (ImageView) findViewById(R.id.tick);
		ImageView ques = (ImageView) findViewById(R.id.question);
		ImageView w1 = (ImageView) findViewById(R.id.wrong1);
		ImageView w2 = (ImageView) findViewById(R.id.wrong2);
		w2.setVisibility(View.VISIBLE);
		w1.setVisibility(View.INVISIBLE);
		tick.setVisibility(View.INVISIBLE);
		ques.setVisibility(View.VISIBLE);
		
	}
	
	public void previous(View view) {
		//Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_SHORT).show();
		Log.e("Previous","executed!");
		if(question==0)
		{
			generateCsvFile();
			Intent intent = new Intent(this,PracticeLevels.class);
			finish();
			startActivity(intent);
		}
		else
		{
			question--;
			loadActivity();
			
		}
      }
	public void next(View view) {
		//Toast.makeText(getApplicationContext(), "Incorrect",Toast.LENGTH_SHORT).show();
		  if(question==9 || (question==3 && (level==4 || level==5)))
			{
			    generateCsvFile();
				Intent intent = new Intent(this,PracticeLevels.class);
				finish();
				startActivity(intent);
			}
		  else{
		  	question++;
		  	Log.e("Next","Question:"+((Integer)question).toString());      
		  	loadActivity();
		  }
      }
	public void onBackPressed()
	{
	   //logic here, for example an intent
	   generateCsvFile();
	   Intent intent = new Intent(this, PracticeLevels.class);    
	   finish();
	   startActivity(intent);   
	}
	public void ArrayIntialise()
	{
		if(level==1){		
			number1.add(R.drawable.number_2); number1.add(R.drawable.number_6); number1.add(R.drawable.number_4);
			number1.add(R.drawable.number_8); number1.add(R.drawable.number_1); number1.add(R.drawable.number_3);
			number1.add(R.drawable.number_7); number1.add(R.drawable.number_5); number1.add(R.drawable.number_0);
			number1.add(R.drawable.number_9);
			
			answer.add(R.drawable.number_3); answer.add(R.drawable.number_7); answer.add(R.drawable.number_5);
			answer.add(R.drawable.number_9); answer.add(R.drawable.number_2); answer.add(R.drawable.number_4);
			answer.add(R.drawable.number_8); answer.add(R.drawable.number_6); answer.add(R.drawable.number_1);
			answer.add(R.drawable.ten2);
			
			value1.add(1); value1.add(0); value1.add(0);
			value2.add(0); value2.add(1); value2.add(0); 
			value3.add(0); value3.add(0); value3.add(1);
			
			choices.add(value2); 		choices.add(value1); 		choices.add(value3);
			choices.add(value3); 		choices.add(value1); 		choices.add(value3);
			choices.add(value1); 		choices.add(value2); 		choices.add(value1);
			choices.add(value3);
			
			o1.clear();		o1_n.clear();
			
			o1.add(R.drawable.number_4);	o1_n.add(4); 		o1.add(R.drawable.number_7);	o1_n.add(7);
			o1.add(R.drawable.number_7);	o1_n.add(7); 		o1.add(R.drawable.number_6);	o1_n.add(6);
			o1.add(R.drawable.number_2);	o1_n.add(2); 		o1.add(R.drawable.number_2);	o1_n.add(2);
			o1.add(R.drawable.number_8);	o1_n.add(8); 		o1.add(R.drawable.number_4);	o1_n.add(4);
			o1.add(R.drawable.number_1);	o1_n.add(1);  		o1.add(R.drawable.number_8);	o1_n.add(8);
			
			o2.add(R.drawable.number_3);	o2_n.add(3); 		o2.add(R.drawable.number_6);	o2_n.add(6);
			o2.add(R.drawable.number_6);	o2_n.add(6); 		o2.add(R.drawable.number_7);	o2_n.add(7);
			o2.add(R.drawable.number_4);	o2_n.add(4); 		o2.add(R.drawable.number_5);	o2_n.add(5);
			o2.add(R.drawable.number_6);	o2_n.add(6); 		o2.add(R.drawable.number_6);	o2_n.add(6);
			o2.add(R.drawable.number_2);	o2_n.add(2); 		o2.add(R.drawable.number_9);	o2_n.add(9);
			
			o3.add(R.drawable.number_5);	o3_n.add(5); 		o3.add(R.drawable.number_5);	o3_n.add(5);
			o3.add(R.drawable.number_5);	o3_n.add(5); 		o3.add(R.drawable.number_9);	o3_n.add(9);
			o3.add(R.drawable.number_1);	o3_n.add(1); 		o3.add(R.drawable.number_4);	o3_n.add(4);
			o3.add(R.drawable.number_5);	o3_n.add(5); 		o3.add(R.drawable.number_5);	o3_n.add(5);
			o3.add(R.drawable.number_0);	o3_n.add(0); 		o3.add(R.drawable.ten2);		o3_n.add(10);
			
			ques.add("2+1=3 -"); ques.add("6+1=7 -");ques.add("4+1=5 -");ques.add("8+1=9 -"); ques.add("1+1=2 -");
			ques.add("3+1=4 -"); ques.add("7+1=8 -");ques.add("5+1=6 -");ques.add("0+1=1 -"); ques.add("9+1=10 -");
			
		}
		if(level==2)
		{
			number1.add(R.drawable.number_3); number1.add(R.drawable.number_7); number1.add(R.drawable.number_4);
			number1.add(R.drawable.number_8); number1.add(R.drawable.number_0); number1.add(R.drawable.number_9);
			number1.add(R.drawable.number_1); number1.add(R.drawable.number_2); number1.add(R.drawable.number_5);
			number1.add(R.drawable.number_6);
			
			answer.add(R.drawable.number_5); answer.add(R.drawable.number_9); answer.add(R.drawable.number_6);
			answer.add(R.drawable.ten2); answer.add(R.drawable.number_2); answer.add(R.drawable.eleven);
			answer.add(R.drawable.number_3); answer.add(R.drawable.number_4); answer.add(R.drawable.number_7);
			answer.add(R.drawable.number_8);
			
			value1.add(1); value1.add(0); value1.add(0);
			value2.add(0); value2.add(1); value2.add(0); 
			value3.add(0); value3.add(0); value3.add(1);
			
			choices.add(value1); 		choices.add(value3); 		choices.add(value2);
			choices.add(value2); 		choices.add(value1); 		choices.add(value2);
			choices.add(value1); 		choices.add(value3); 		choices.add(value2);
			choices.add(value3);
			
			o1.clear();		o1_n.clear();
			
			o1.add(R.drawable.number_5);	o1_n.add(5); 		o1.add(R.drawable.number_6);	o1_n.add(6);
			o1.add(R.drawable.number_5);	o1_n.add(5); 		o1.add(R.drawable.number_9);	o1_n.add(9);
			o1.add(R.drawable.number_2);	o1_n.add(2); 		o1.add(R.drawable.ten2);		o1_n.add(10);
			o1.add(R.drawable.number_3);	o1_n.add(3); 		o1.add(R.drawable.number_3);	o1_n.add(3);
			o1.add(R.drawable.number_5);	o1_n.add(5);  		o1.add(R.drawable.number_9);	o1_n.add(9);
			
			o2.add(R.drawable.number_4);	o2_n.add(4); 		o2.add(R.drawable.number_8);	o2_n.add(8);
			o2.add(R.drawable.number_6);	o2_n.add(6); 		o2.add(R.drawable.ten2);		o2_n.add(10);
			o2.add(R.drawable.number_4);	o2_n.add(4); 		o2.add(R.drawable.eleven);		o2_n.add(11);
			o2.add(R.drawable.number_4);	o2_n.add(4); 		o2.add(R.drawable.number_5);	o2_n.add(5);
			o2.add(R.drawable.number_7);	o2_n.add(7); 		o2.add(R.drawable.number_7);	o2_n.add(7);
			
			o3.add(R.drawable.number_6);	o3_n.add(6); 		o3.add(R.drawable.number_9);	o3_n.add(9);
			o3.add(R.drawable.number_4);	o3_n.add(4); 		o3.add(R.drawable.number_8);	o3_n.add(8);
			o3.add(R.drawable.number_3);	o3_n.add(3); 		o3.add(R.drawable.number_9);	o3_n.add(9);
			o3.add(R.drawable.number_2);	o3_n.add(2); 		o3.add(R.drawable.number_4);	o3_n.add(4);
			o3.add(R.drawable.number_6);	o3_n.add(6); 		o3.add(R.drawable.number_8);	o3_n.add(8);
			
			ques.add("3+2=5 -"); ques.add("7+2=9 -");ques.add("4+2=6 -");ques.add("8+2=10 -"); ques.add("0+1=2 -");
			ques.add("9+2=11 -"); ques.add("1+2=3 -");ques.add("2+2=4 -");ques.add("5+2=7 -"); ques.add("6+2=8 -");
		}
		if(level==3)
		{
			number1.add(R.drawable.number_4); number1.add(R.drawable.number_6); number1.add(R.drawable.number_0);
			number1.add(R.drawable.number_8); number1.add(R.drawable.number_1); number1.add(R.drawable.number_5);
			number1.add(R.drawable.number_2); number1.add(R.drawable.number_7); number1.add(R.drawable.number_3);
			number1.add(R.drawable.number_9);
			
			answer.add(R.drawable.number_7); answer.add(R.drawable.number_9); answer.add(R.drawable.number_3);
			answer.add(R.drawable.eleven);   answer.add(R.drawable.number_4); answer.add(R.drawable.number_8);
			answer.add(R.drawable.number_5); answer.add(R.drawable.ten2); answer.add(R.drawable.number_6);
			answer.add(R.drawable.twelve);
			
			value1.add(1); value1.add(0); value1.add(0);
			value2.add(0); value2.add(1); value2.add(0); 
			value3.add(0); value3.add(0); value3.add(1);
			
			choices.add(value2); 		choices.add(value3); 		choices.add(value1);
			choices.add(value3); 		choices.add(value3); 		choices.add(value1);
			choices.add(value3); 		choices.add(value1); 		choices.add(value2);
			choices.add(value2);
			
			o1.clear();		o1_n.clear();
			
			o1.add(R.drawable.number_6);	o1_n.add(6); 		o1.add(R.drawable.number_8);	o1_n.add(8);
			o1.add(R.drawable.number_3);	o1_n.add(3); 		o1.add(R.drawable.ten2);		o1_n.add(10);
			o1.add(R.drawable.number_6);	o1_n.add(6); 		o1.add(R.drawable.number_8);	o1_n.add(8);
			o1.add(R.drawable.number_4);	o1_n.add(4); 		o1.add(R.drawable.ten2);		o1_n.add(10);
			o1.add(R.drawable.number_7);	o1_n.add(7);  		o1.add(R.drawable.ten2);		o1_n.add(10);
			
			o2.add(R.drawable.number_7);	o2_n.add(7); 		o2.add(R.drawable.ten2);		o2_n.add(10);
			o2.add(R.drawable.number_1);	o2_n.add(1); 		o2.add(R.drawable.number_9);	o2_n.add(9);
			o2.add(R.drawable.number_2);	o2_n.add(6); 		o2.add(R.drawable.number_6);	o2_n.add(6);
			o2.add(R.drawable.number_6);	o2_n.add(6); 		o2.add(R.drawable.eleven);		o2_n.add(11);
			o2.add(R.drawable.number_6);	o2_n.add(6); 		o2.add(R.drawable.twelve);		o2_n.add(12);
			
			o3.add(R.drawable.number_8);	o3_n.add(8); 		o3.add(R.drawable.number_9);	o3_n.add(9);
			o3.add(R.drawable.number_2);	o3_n.add(2); 		o3.add(R.drawable.eleven);		o3_n.add(11);
			o3.add(R.drawable.number_4);	o3_n.add(4); 		o3.add(R.drawable.number_9);	o3_n.add(9);
			o3.add(R.drawable.number_5);	o3_n.add(5); 		o3.add(R.drawable.number_9);	o3_n.add(9);
			o3.add(R.drawable.number_8);	o3_n.add(8); 		o3.add(R.drawable.eleven);		o3_n.add(11);
			
			ques.add("4+3=7 -"); ques.add("6+3=9 -");ques.add("0+3=3 -");ques.add("8+3=11 -"); ques.add("1+3=4 -");
			ques.add("5+3=8 -"); ques.add("2+3=5 -");ques.add("7+3=10 -");ques.add("3+3=6 -"); ques.add("9+3=12 -");
		}
		if(level==4)
		{
			number1.add(R.drawable.number_4); 	number1.add(R.drawable.number_7); number1.add(R.drawable.number_6); 
			number1.add(R.drawable.number_5);
			
			answer.add(R.drawable.number_8); answer.add(R.drawable.eleven); answer.add(R.drawable.ten2);
			answer.add(R.drawable.number_9);   
			
			value1.add(1); value1.add(0); value1.add(0);
			value2.add(0); value2.add(1); value2.add(0); 
			value3.add(0); value3.add(0); value3.add(1);
			
			choices.add(value3); 		choices.add(value3); 		choices.add(value2);
			choices.add(value1); 	
			
			o1.clear();		o1_n.clear();
			
			o1.add(R.drawable.number_6);	o1_n.add(6); 		o1.add(R.drawable.twelve);	o1_n.add(12);
			o1.add(R.drawable.eleven);	o1_n.add(11); 		o1.add(R.drawable.number_9);		o1_n.add(9);				
			
			o2.add(R.drawable.number_7);	o2_n.add(7); 		o2.add(R.drawable.ten2);		o2_n.add(10);
			o2.add(R.drawable.ten2);	o2_n.add(10); 		o2.add(R.drawable.number_8);	o2_n.add(8);			 		
			
			o3.add(R.drawable.number_8);	o3_n.add(8); 		o3.add(R.drawable.eleven);		o3_n.add(11);
			o3.add(R.drawable.number_9);	o3_n.add(9);		o3.add(R.drawable.number_6);	o3_n.add(6); 		
		
			ques.add("4+4=8 -"); ques.add("7+4=11 -");ques.add("6+4=10 -");ques.add("5+4=9 -");
			ques.add("-");ques.add("-");ques.add("-");ques.add("-");ques.add("-");ques.add("-");
		}
		if(level==5)
		{
			number1.add(R.drawable.number_6); 	number1.add(R.drawable.number_9); number1.add(R.drawable.number_5); 
			number1.add(R.drawable.number_8);
			
			answer.add(R.drawable.eleven); answer.add(R.drawable.fourteen); answer.add(R.drawable.ten2);
			answer.add(R.drawable.thirteen);   
			
			value1.add(1); value1.add(0); value1.add(0);
			value2.add(0); value2.add(1); value2.add(0); 
			value3.add(0); value3.add(0); value3.add(1);
			
			choices.add(value2); 		choices.add(value3); 		choices.add(value3);
			choices.add(value1); 	 
			
			o1.clear();		o1_n.clear();
			
			o1.add(R.drawable.ten2);	o1_n.add(10); 		o1.add(R.drawable.twelve);	o1_n.add(12);
			o1.add(R.drawable.number_9);	o1_n.add(9); 		o1.add(R.drawable.thirteen);		o1_n.add(13);
			
			o2.add(R.drawable.eleven);	o2_n.add(11); 		o2.add(R.drawable.eleven);		o2_n.add(11);
			o2.add(R.drawable.number_8);	o2_n.add(8);	o2.add(R.drawable.number_5);	o2_n.add(5); 		
			
			o3.add(R.drawable.twelve);	o3_n.add(12); 		o3.add(R.drawable.fourteen);		o3_n.add(14);
			o3.add(R.drawable.ten2);	o3_n.add(10);		o3.add(R.drawable.ten2);	o3_n.add(10);	
			
			ques.add("6+5=11 -"); ques.add("9+5=14 -");ques.add("5+5=10 -");ques.add("8+5=13 -");
			ques.add("-");ques.add("-");ques.add("-");ques.add("-");ques.add("-");ques.add("-");
		}
	}
	private void generateCsvFile()
	   {
		FileWriter writer;
		try 
        {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AppsReport/NumberBonds";
        File dir = new File(path);
        if(!dir.exists())
        dir.mkdirs();
        File file = new File(dir,"feedback.csv");
        if(file.exists()) 
          file.delete();
        writer = new FileWriter(file);
	    String header="Name,Date,Level,Question1,Question2,Question3,Question4,Question5,Question6,Question7,Question8,Question9,Question10,TotalMistakes\n";
		writer.write(header);
	 
		
	        ArrayList<Student> records = new ArrayList<Student>();
	        db.open();
	        records = db.extract();
	        db.close();
	        System.out.println("Total Records::="+records.size());
	             
	        int i=0;
	        for(i=0;i< records.size();i++)
	        {
	        	Student s1 = new Student(records.get(i));
	        	System.out.print(s1.level+"--"+s1.id+"--"+s1.date+"--"+s1.question1+"--"+s1.question2+"--end");
	        	System.out.println();
	        	writer.append(s1.name);
	        	writer.append(',');
	        	writer.append(s1.date);
	        	writer.append(',');
			    writer.append(s1.level);
	        	writer.append(',');
	        	writer.append(s1.question1);
			    writer.append(',');
			    writer.append(s1.question2);
			    writer.append(',');
			    writer.append(s1.question3);
			    writer.append(',');
			    writer.append(s1.question4);
			    writer.append(',');
			    writer.append(s1.question5);
			    writer.append(',');
			    writer.append(s1.question6);
			    writer.append(',');
			    writer.append(s1.question7);
			    writer.append(',');
			    writer.append(s1.question8);
			    writer.append(',');
			    writer.append(s1.question9);
			    writer.append(',');
			    writer.append(s1.question10);
			    writer.append(',');
			    System.out.println("Mistakes and Level in s1:"+s1.mistakes+","+s1.level);
			   // writer.append(((Integer)s1.mistakes).toString());
			    writer.append(s1.mistakes);
			    
			    writer.append('\n');
	        }
		
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	    }
	

}
