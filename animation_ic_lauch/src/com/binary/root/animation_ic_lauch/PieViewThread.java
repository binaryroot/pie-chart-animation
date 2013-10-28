package com.binary.root.animation_ic_lauch;

import android.graphics.Canvas;

 class PieViewThread extends Thread {
       static final long FPS = 60;
       private PieView view;
       private boolean running = false;
      
       public PieViewThread(PieView view) {
             this.view = view;
       }
 
       public void setRunning(boolean run) {
             running = run;
       }
 
       @Override
       public void run() {
             long ticksPS = 1000 / FPS;
             long startTime;
             long sleepTime;
             while (running) {
                    Canvas c = null;
                    startTime = System.currentTimeMillis();
                    try {
                           c = view.getHolder().lockCanvas();
                           synchronized (view.getHolder()) {
                                  view.onDraw(c);
                           }
                    } finally {
                           if (c != null) {
                        	   try{
                                  view.getHolder().unlockCanvasAndPost(c);
                        	   }catch (IllegalArgumentException e){
                        		   // https://code.google.com/p/android/issues/detail?id=58385
                        	   }
                           }
                    }
                    sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
                    try {
                           if (sleepTime > 0)
                                  sleep(sleepTime);
                           else
                                  sleep(10);
                    } catch (Exception e) {}
             }
       }
}
