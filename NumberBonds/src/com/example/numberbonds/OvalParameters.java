package com.example.numberbonds;

import android.graphics.Paint;
import android.graphics.RectF;

public class OvalParameters {
	 
	 RectF oval;
	 float startAngle;
	 float sweepAngle; boolean useCenter;
	 Paint paint;
	 

	public OvalParameters(OvalParameters ops) {
		// TODO Auto-generated constructor stub
		oval = ops.oval;
		 startAngle = ops.startAngle;
		 sweepAngle = ops.sweepAngle;
		 paint = ops.paint;
	}


	public OvalParameters() {
		// TODO Auto-generated constructor stub
	}
}