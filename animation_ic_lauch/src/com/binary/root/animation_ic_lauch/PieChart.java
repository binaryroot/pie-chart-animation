package com.binary.root.animation_ic_lauch;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.*;

import java.lang.Math;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class PieChart 
	extends ViewGroup 
	{
	
    private List<PieChartItem > data;
    private RectF pieBounds = new RectF();
    private Paint piePaint;
    private float highlightStrength = 1.15f;
    private float pointerRadius = 2.0f;
    private PieView pieView;

    public interface OnCurrentItemChangedListener 
    {
        void OnCurrentItemChanged(PieChart source, int currentItem);
    }

    public PieChart(Context context) 
    {
        super(context);
        init();
    }

    
    public PieChart(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
        init();
    }

  
    public float getHighlightStrength() 
    {
        return highlightStrength;
    }

   
    public void setHighlightStrength(float highlightStrength) 
    {
        if (highlightStrength < 0.0f) {
            throw new IllegalArgumentException(
                    "highlight strength cannot be negative");
        }
        invalidate();
    }

    
    public float getPointerRadius() 
    {
        return pointerRadius;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) 
    {
        super.onSizeChanged(w, h, oldw, oldh);

        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());


        float ww = (float) w - xpad;
        float hh = (float) h - ypad;

        float diameter = Math.min(ww, hh);
        pieBounds = new RectF(
                0.0f,
                0.0f,
                diameter,
                diameter);
        pieBounds.offsetTo(getPaddingLeft(), getPaddingTop());


        pieView.layout((int) pieBounds.left,
                (int) pieBounds.top,
                (int) pieBounds.right,
                (int) pieBounds.bottom);

        pieView.onDataChanged();
    }


    private void init() 
    {
    	setBackgroundColor(Color.WHITE);
        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);
        
        data = new ArrayList<PieChartItem>();
        pieView = new PieView(getContext(),data);
        addView(pieView);
    }

    
    public void addPieChart(String label, float value, int color)
    {
    	pieView.addPieChart(label, value, color);
    }
    
    
    public List<PieChartItem> getListItem()
    {
    	return data;
    }
}

