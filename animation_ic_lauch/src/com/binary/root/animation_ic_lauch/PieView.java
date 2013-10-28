package com.binary.root.animation_ic_lauch;

import java.util.List;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
 class PieView 
	extends SurfaceView 
{
	
    private SurfaceHolder holder;
    private PieViewThread pieViewThread;

    private List<PieChartItem> sprites;
    private float total = 0.0f;
    private boolean isDestroyed;
    public PieView(Context context, List<PieChartItem> pieChartItem)
    {
          super(context);
          pieViewThread = new PieViewThread(this);
          sprites = pieChartItem;
          holder = getHolder();
        
          holder.addCallback(new SurfaceHolder.Callback() {

                 @Override
                 public void surfaceDestroyed(SurfaceHolder holder) {
                        boolean retry = true;
                        isDestroyed = true;
                        pieViewThread.setRunning(false);
                        while (retry) {
                               try {
                                     pieViewThread.join();
                                     retry = false;
                               } catch (InterruptedException e) {
                               }
                        }
                 }

                 @Override
                 public void surfaceCreated(SurfaceHolder holder) {
                	 if (pieViewThread.getState() == Thread.State.TERMINATED) {
                		 pieViewThread = new PieViewThread(PieView.this);
                		 pieViewThread.setRunning(true);
                		 pieViewThread.start();
                		 isDestroyed = false;
                     }
                     else {
                    	 pieViewThread.setRunning(true);
                    	 pieViewThread.start();
                     }
                	 
                 }

                 @Override
                 public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {
                 }
          });
    }
   
    
    public void addPieChart(String label, float value, int color)
    {
    	PieChartItem pieChart = new PieChartItem(this);
    	pieChart.label = label;
    	pieChart.value = value;
    	pieChart.color =color;
    	
    	total += value;
    	
    	sprites.add(pieChart);
    	onDataChanged();
    }
    
    
    protected void onDataChanged()
    {
    	int currentAngle = 0;
    	for (PieChartItem it : sprites) {
    		it.startAngle = currentAngle;
    		it.endAngle =(int) Math.ceil((currentAngle + it.value * 360.0f / total));
    		it.middleAngle = (it.startAngle + it.endAngle)/2;
    		it.currentStartAngle = (float)Math.ceil((it.middleAngle-2.5));
    		currentAngle = it.endAngle;
		}
    }

    
    @Override
    protected void onDraw(Canvas canvas) 
    {
    	if(!isDestroyed){
         canvas.drawColor(Color.WHITE);
          for (PieChartItem sprite : sprites) {
              sprite.onDraw(canvas);
          }
    	}
    }
    
}