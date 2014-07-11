package com.example.numberbonds;

import java.util.ArrayList;
import 	android.graphics.Color;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
@SuppressLint("NewApi")
public class DrawingView extends View implements OnTouchListener {
	NL1 nl1;
	Context mContext;
	private Canvas m_Canvas;
	private Path m_Path;
	int width, height, flag=0, flag2=0;
	int start=0;
	private Paint m_Paint;
	//public int checkAnswer=0;
	ArrayList<OvalParameters> arcs = new ArrayList<OvalParameters>();
	Pair<Path,Paint> pair1 = new Pair<Path,Paint>(m_Path,m_Paint);  
	OvalParameters ops = new OvalParameters();
	ArrayList<Float> temp = new ArrayList<Float>();
	ArrayList<Pair<Path, Paint>> paths = new ArrayList<Pair<Path, Paint>>();
	RectF ovalRectOUT;
	
	ArrayList<Pair<Path, Paint>> undonePaths = new ArrayList<Pair<Path, Paint>>();
	ArrayList<Integer> number1 = new ArrayList<Integer>();
	ArrayList<Integer> number2 = new ArrayList<Integer>();
	float startAngle;
	private float mX, mY;
	public float x1,y1,x2,y2;
	private static final float TOUCH_TOLERANCE = 4;
	ArrayList<Integer> ans_values = new ArrayList<Integer>();
	public static boolean isEraserActive = false; 

	public DrawingView(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);

		setBackgroundColor(0xfff0f7);

		this.setOnTouchListener(this);

		onCanvasInitialization();
		//
		mContext=context;
		nl1=(NL1) mContext;
		//Log.e("Question!!!!!",((Integer)question).toString());
		//nl1.hello();
		ArrayInitialise();
		
	}

	public void onCanvasInitialization() {
		m_Paint = new Paint();
		m_Paint.setAntiAlias(true);
		m_Paint.setDither(true);
		m_Paint.setColor(Color.RED); 
		m_Paint.setStyle(Paint.Style.STROKE);
		m_Paint.setStrokeJoin(Paint.Join.ROUND);
		m_Paint.setStrokeCap(Paint.Cap.ROUND);
		m_Paint.setStrokeWidth(2);

		m_Canvas = new Canvas();

		screenSize(getContext());
		m_Path = new Path();
		//m_Canvas.drawLine(50, height-50 ,width-50 , height-50, m_Paint);
		//Paint newPaint = new Paint(m_Paint);
		//paths.add(new Pair<Path, Paint>(m_Path, newPaint));
 
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	public boolean onTouch(View arg0, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
			
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("Action Down", ((Float)x).toString()+","+((Float)y).toString());
			m_Paint.setColor(Color.RED);
			m_Paint.setStrokeWidth(2);
			x1 = event.getX();
			y1 = event.getY();
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			Log.e("Action Up", ((Float)x).toString()+","+((Float)y).toString());
			x2 = event.getX();
			y2 = event.getY();
			touch_up();
			invalidate();
			break;
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		screenSize(getContext());
		//Log.e("Values:",((Integer)(height-50)).toString()+","+((Integer)(width-50)).toString());
		//System.out.println("Question On draw: "+nl1.question);
		m_Paint.setColor(Color.parseColor("#104E8B"));
		canvas.drawLine(50,2*height/3-30 ,width-50 ,2*height/3-30, m_Paint);
		int partition = (width -100 )/5;
		
		int i;
		if(nl1.question<6)
		{
			partition = (width -100 )/5;
			for (i=0;i<=5;i++)
			{
						
				canvas.drawLine(50+i*partition,2*height/3-40, 50+i*partition, 2*height/3-20, m_Paint);
				m_Paint.setTextSize(18); 
				canvas.drawText(((Integer)i).toString(),(float)50+i*partition, 2*height/3, m_Paint); 
			}
		}
		else
		{
			partition = (width -100 )/10;
			for (i=0;i<=10;i++)
			{
						
				canvas.drawLine(50+i*partition,2*height/3-30, 50+i*partition, 2*height/3-10, m_Paint);
				m_Paint.setTextSize(18); 
				canvas.drawText(((Integer)i).toString(),(float)50+i*partition, 2*height/3+10, m_Paint); 
			}
		}
		m_Paint.setColor(Color.RED);
		for (OvalParameters p : arcs) 
		{
			//canvas.drawPath(p.first, p.second);			
			canvas.drawArc(p.oval, p.startAngle, 180,false, p.paint);
		//	canvas.drawLine(50, 200 ,400 ,200, m_Paint);
		}
		for (Pair<Path, Paint> x : paths )
		{
			canvas.drawPath(x.first, x.second);
		}
		//Log.e("Ondraw","Executed!");
	}

	private void touch_start(float x, float y) {

		if (isEraserActive) {
			m_Paint.setColor(Color.WHITE);
			m_Paint.setStrokeWidth(6);
			//Paint newPaint = new Paint(m_Paint); // Clones the mPaint object
			////paths.add(new Pair<Path, Paint>(m_Path, newPaint));

		} else { 
			m_Paint.setColor(Color.RED);
			m_Paint.setStrokeWidth(2);
			Paint newPaint = new Paint(m_Paint); // Clones the mPaint object
			paths.add(new Pair<Path, Paint>(m_Path, newPaint));

		}


		m_Path.reset();
		m_Path.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			m_Path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	private void touch_up() {
		m_Path.lineTo(mX, mY);

		// commit the path to our offscreen
		m_Canvas.drawPath(m_Path, m_Paint);

		// kill this so we don't double draw
		m_Path = new Path();
		Paint newPaint = new Paint(m_Paint); // Clones the mPaint object
		paths.add(new Pair<Path, Paint>(m_Path, newPaint));
		
		check0();
		float centerX = x1 + ((x2 - x1) / 2);
        float centerY = y1 + ((y2 - y1) / 2);
 
        double xLen = (x2 - x1);
        double yLen = (y2 - y1);
        float radius = (float) (Math.sqrt(xLen * xLen + yLen * yLen) / 2);
        ovalRectOUT = new RectF((float) (centerX - radius),(float) (centerY - radius), (float) (centerX + radius),(float) (centerY + radius));
        double radStartAngle = 0;
        radStartAngle = Math.atan2(y1 - centerY, x1 - centerX);
        startAngle = (float) Math.toDegrees(radStartAngle);
        ops.oval = new RectF(ovalRectOUT);
        ops.paint = new Paint(newPaint);
        ops.useCenter=false;
        ops.sweepAngle=180;
        ops.startAngle= startAngle;
        if(flag==1){
        	//log.e("Arc Added","drawn");
        	arcs.add(new OvalParameters(ops));
        }
                     
        screenSize(getContext());
       	//	m_Path.addArc(ovalRectOUT, startAngle, 180);
        paths.clear();
		paths.add(new Pair<Path, Paint>(m_Path, newPaint));
		m_Canvas.drawArc(ovalRectOUT, startAngle, 180,false, m_Paint);
		//Log.e("Object ops",arcs.toString());
	}
	public void screenSize(Context context)
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x; //800
		height = size.y; //432
		
		//Log.e("Dimensions",((Integer)width).toString()+","+((Integer)height).toString());
	}
	
	public void check0()
	{
			Log.e("Check0","Executed");
			System.out.println("Question: "+nl1.question);			
			int stop=ans_values.get(nl1.question-1);
			int partition;
			if(nl1.question<6)
			{
				partition = (width - 100)/5;
			}
			else
				partition = (width - 100)/10;
			int error = (partition/2) -10;
			flag=0;
			//Log.e("Start",((Integer)start).toString());
			//Log.e("Stop",((Integer)stop).toString());
			if(start<stop)
			{
				if((x1 >=(50+start*partition)-error && x1 <= (50+start*partition)+error) && (y1>=(2*height/3-30)-50 && y1<=(2*height/3-30)+50))
				{
					if((x2 >=(50+((start+1)*partition))-error && x2 <= (50+((start+1)*partition))+error) && (y2>=(2*height/3-30)-50 && y2<=(2*height/3-30)+50))
					{
						x1 = (50+start*partition);
						x2 = (50+(start+1)*partition);
						y1 = (2*height/3-30);
						y2 = (2*height/3-30);
						flag=1;			
						//checkAnswer++; //not used
						//Log.e("Drawn!","Correct");
						start++;
						if(start==number1.get(nl1.question-1))
						{
							nl1.blink1();
						}
						
						//start++;
					}
					
				}
				
				//Log.e("Start-end",((Integer)start).toString());
				//Log.e("Stop-end",((Integer)stop).toString());
			}
			/*if(start==number1.get(nl1.question-1))
			{
				nl1.blink1();
			}*/
			if(start==stop && flag2==0)
			{
				flag2=1;
				nl1.blink2();
				nl1.hello();
			}
		}
		public void ArrayInitialise()
		{
				ans_values.add(3);			ans_values.add(4);			
				ans_values.add(2); 			ans_values.add(5);
				ans_values.add(5);			ans_values.add(7);			
				ans_values.add(9); 			ans_values.add(7);
				
				number1.add(1); 			number1.add(1);
				number1.add(1); 			number1.add(4);
				number1.add(2); 			number1.add(5);
				number1.add(5); 			number1.add(3);
				
				number2.add(2); 			number2.add(3);
				number2.add(1); 			number2.add(1);
				number1.add(3); 			number1.add(2);
				number1.add(4); 			number1.add(4);
			
		}
} 