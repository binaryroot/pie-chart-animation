package com.binary.root.animation_ic_lauch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class PieChartItem {
	private PieView gameView;

	public String label;
    public float value;
    public int color;

    public int startAngle;
    public int endAngle;

    public float currentStartAngle;
    public float currentEndAngle;
    
    public float currentAngle;
    public int middleAngle;

    private Paint piePaint;
    private RectF rectF; 
    
    private float startAngleInc = 1;
    private float endAndleInc = 2;
    
	public PieChartItem(PieView gameView) 
	{
		this.gameView = gameView;
		piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    piePaint.setStyle(Paint.Style.FILL);
	    piePaint.setColor(Color.WHITE);
	    rectF = new RectF(0, 0, this.gameView.getWidth(), this.gameView.getHeight());
	    currentEndAngle = 1;
	}

	
	private void update() 
	{
		if(currentStartAngle >=startAngle){
			currentStartAngle-=startAngleInc;
		}

		if((currentEndAngle+startAngle) <= endAngle){
			currentEndAngle+=endAndleInc;
		}
		
		startAngleInc = startAngleInc+0.1f;
		endAndleInc = endAndleInc+0.2f;
		
	}

	public void onDraw(Canvas canvas) 
	{
		update();
		piePaint.setColor(color);
		rectF = new RectF(0, 0, this.gameView.getWidth(), this.gameView.getHeight());
 	    canvas.drawArc (rectF, currentStartAngle, currentEndAngle, true, piePaint);
	}
	
	
	// reset to default value
	public void reset()
	{
		currentStartAngle = (float)Math.ceil((middleAngle-2.5));
		currentEndAngle = 1;
		startAngleInc = 1;
		endAndleInc = 2;
	}
}