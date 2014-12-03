package com.example.medicare;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Medical_List extends Activity {
	ArrayList<String> brandTable, compoTable;
	TextView brand;
	LinearLayout ll;

	DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medical_list);
		ll=(LinearLayout)findViewById(R.id.Layout);
		db=new DBHelper(getApplicationContext());
		
		showAll();
	//	showTable2();
		
		

//		Cursor c2 = db.getData("Select * from COMPOSITION");
	//	brandTable = new ArrayList<String>();
		//compoTable = new ArrayList<String>();

	}

	@SuppressLint("NewApi")
	public void showAll() {
		TextView brandName,category,categoryData, brandComposition, brandMrp, brandId, brandCompany, brandNameData,brandIdData, /*brandCompositionData,*/ brandMrpData, brandCompanyData;
		TableLayout table1 = new TableLayout(getApplicationContext());
		table1.setScrollContainer(true);
		TableRow row = new TableRow(getApplicationContext());
		brandName = new TextView(getApplicationContext());
		brandComposition = new TextView(getApplicationContext());
		brandCompany = new TextView(getApplicationContext());
		brandMrp = new TextView(getApplicationContext());
		brandId = new TextView(getApplicationContext());
		category= new TextView(getApplicationContext());
		
		
		brandId.setText("SNO.");
		brandId.setTextColor(Color.BLACK);
		brandId.setTextSize(16.0f);
		brandId.setHovered(true);
		brandId.setPadding(5, 5, 5, 5);
		
		category.setText("Category");
		category.setTextColor(Color.BLACK);
		category.setTextSize(16.0f);
		category.setHovered(true);
		category.setPadding(5, 5, 5, 5);

		brandName.setText("Name");
		brandName.setTextColor(Color.BLACK);
		brandName.setTextSize(16.0f);
		brandName.setHovered(true);
		brandName.setPadding(5, 5, 5, 5);

		brandCompany.setText("Company");
		brandCompany.setTextColor(Color.BLACK);
		brandCompany.setTextSize(16.0f);
		brandCompany.setHovered(true);
		brandCompany.setPadding(5, 5, 5, 5);

		brandMrp.setText("M.R.P");
		brandMrp.setTextColor(Color.BLACK);
		brandMrp.setTextSize(16.0f);
		brandMrp.setHovered(true);
		brandMrp.setPadding(5, 5, 5, 5);

		brandComposition.setText("Composition");
		brandComposition.setTextColor(Color.BLACK);
		brandComposition.setTextSize(16.0f);
		brandComposition.setHovered(true);
		brandComposition.setPadding(5, 5, 5, 5);

		row.addView(brandId);
		row.addView(category);
		row.addView(brandName);
		row.addView(brandCompany);
		row.addView(brandMrp);
		
		//row.addView(brandComposition);

		table1.addView(row);
		
		Cursor c1 = db.getData("select * from BRAND");
		//Cursor c2 = db.getData("select * from COMPOSITION");
		
		while (c1.moveToNext() ) {
			row= new TableRow(getApplicationContext());
			
			brandNameData =new TextView(getApplicationContext());
			brandCompanyData =new TextView(getApplicationContext());
			brandMrpData =new TextView(getApplicationContext());
			brandIdData =new TextView(getApplicationContext());
			categoryData=new TextView(getApplicationContext());
			//brandCompositionData =new TextView(getApplicationContext());
			
			brandIdData.setText(c1.getString(0));
			brandIdData.setTextSize(14.0f);
			brandIdData.setHovered(true);
			brandIdData.setTextColor(Color.BLACK);
			brandIdData.setPadding(5, 5, 5, 5);
			
			categoryData.setText(c1.getString(4));
			categoryData.setTextColor(Color.BLACK);
			categoryData.setTextSize(16.0f);
			categoryData.setHovered(true);
			categoryData.setPadding(5, 5, 5, 5);
	
			brandNameData.setText(c1.getString(1));
			brandNameData.setTextSize(14.0f);
			brandNameData.setHovered(true);
			brandNameData.setTextColor(Color.BLACK);
			brandNameData.setPadding(5, 5, 5, 5);
			
			brandCompanyData.setText(c1.getString(2));
			brandCompanyData.setTextSize(14.0f);
			brandCompanyData.setHovered(true);
			brandCompanyData.setTextColor(Color.BLACK);
			brandCompanyData.setPadding(5, 5, 5, 5);
			
			brandMrpData.setText(c1.getString(3));
			brandMrpData.setTextSize(14.0f);
			brandMrpData.setHovered(true);
			brandMrpData.setTextColor(Color.BLACK);
			brandMrpData.setPadding(5, 5, 5, 5);
			/*
			brandCompositionData.setText(c1.getString(1));
			brandCompositionData.setTextSize(14.0f);
			brandCompositionData.setHovered(true);
			brandCompositionData.setTextColor(Color.BLACK);
			brandCompositionData.setPadding(5, 5, 5, 5);
			*/
			row.addView(brandIdData);
			row.addView(categoryData);
			row.addView(brandNameData);
			row.addView(brandCompanyData);
			row.addView(brandMrpData);
			//row.addView(brandCompositionData);
			
			
			table1.addView(row);
			table1.setScrollContainer(true);
		
		}
		
		
		setContentView(table1);
		
	}
	/*@SuppressLint("NewApi")
	public void showTable2(){
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
*/
}
