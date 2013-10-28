package com.binary.root.animation_ic_lauch;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_main);
		
		final PieChart pc = (PieChart) findViewById(R.id.pieChart1);
		pc.addPieChart("1", 40, Color.BLACK);
		pc.addPieChart("2", 80, Color.RED);
		pc.addPieChart("3", 60, Color.GREEN);
		
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				List<PieChartItem> listOfValue = pc.getListItem();
				for (PieChartItem itemVO : listOfValue) {
					itemVO.reset();
				}
			}
		});
	}

	

}
