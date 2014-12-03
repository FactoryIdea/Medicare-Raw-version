package com.example.medicare;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class composee extends Activity {
	DBHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medical_list);
		db=new DBHelper(getApplicationContext());
		showAll();
		
	}
	@SuppressLint("NewApi")
	public void showAll(){
		TextView compId,composition,parentId,compIdData,compositionData,parentIdData;
		TableLayout table2 = new TableLayout(getApplicationContext());
		table2.setScrollContainer(true);
		TableRow row = new TableRow(getApplicationContext());
		compId = new TextView(getApplicationContext());
		composition = new TextView(getApplicationContext());
		parentId = new TextView(getApplicationContext());
		
		compId.setText("SNO.");
		compId.setTextColor(Color.BLACK);
		compId.setTextSize(16.0f);
		compId.setHovered(true);
		compId.setPadding(5, 5, 5, 5);
		
		composition.setText("Composition");
		composition.setTextColor(Color.BLACK);
		composition.setTextSize(16.0f);
		composition.setHovered(true);
		composition.setPadding(5, 5, 5, 5);

		parentId.setText("Last Id");
		parentId.setTextColor(Color.BLACK);
		parentId.setTextSize(16.0f);
		parentId.setHovered(true);
		parentId.setPadding(5, 5, 5, 5);
		
		row.addView(compId);
		row.addView(composition);
		row.addView(parentId);
		
		table2.addView(row);
		
		Cursor c2 = db.getData("select * from COMPOSITION");
		
		while(c2.moveToNext()){
			row =new TableRow(getApplicationContext());
		
			compIdData = new TextView(getApplicationContext());
			compositionData = new TextView(getApplicationContext());
			parentIdData = new TextView(getApplicationContext());
			
			compIdData.setText(c2.getString(0));
			compIdData.setTextColor(Color.BLACK);
			compIdData.setTextSize(16.0f);
			compIdData.setHovered(true);
			compIdData.setPadding(5, 5, 5, 5);

			compositionData.setText(c2.getString(1));
			compositionData.setTextColor(Color.BLACK);
			compositionData.setTextSize(16.0f);
			compositionData.setHovered(true);
			compositionData.setPadding(5, 5, 5, 5);

			parentIdData.setText(c2.getString(2));
			parentIdData.setTextColor(Color.BLACK);
			parentIdData.setTextSize(16.0f);
			parentIdData.setHovered(true);
			parentIdData.setPadding(5, 5, 5, 5);
			

			row.addView(compIdData);
			row.addView(compositionData);
			row.addView(parentIdData);
			
			table2.addView(row);
		}
		setContentView(table2);


	}

}
