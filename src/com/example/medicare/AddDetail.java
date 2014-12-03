package com.example.medicare;

//import android.annotation.SuppressLint;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

//@SuppressLint("NewApi")
//@SuppressLint("NewApi")
@SuppressLint("NewApi")
public class AddDetail extends Activity {
	EditText ETBrandDetail, ETBrandComposition, ETCompanyDetail, ETBrandMRP,
			Category, Description;
	Button AddComposition, RemoveComposition, SaveData;
	LinearLayout LLComposition;
	DBHelper db;
	String BrandDetailString, BrandCompositionString, CompanyDetailString,
			BrandMRPString, CategoryString, DesString;
	int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_detail);
		id = 0;
		db = new DBHelper(getApplicationContext());

		ETBrandDetail = (EditText) findViewById(R.id.EtBrandDetail);
		ETBrandComposition = (EditText) findViewById(R.id.EtBrandComposition);
		ETCompanyDetail = (EditText) findViewById(R.id.EtCompanyDetail);
		ETBrandMRP = (EditText) findViewById(R.id.EtBrandMRP);
		Category = (EditText) findViewById(R.id.EtCategory);
		Description = (EditText) findViewById(R.id.EtDescription);

		AddComposition = (Button) findViewById(R.id.BtnAddComposition);
		RemoveComposition = (Button) findViewById(R.id.BtnRemoveComposition);
		SaveData = (Button) findViewById(R.id.BtnSaveData);
		LLComposition = (LinearLayout) findViewById(R.id.LLComposition);

		AddComposition.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText edText = new EditText(getApplicationContext());
				edText.setBackgroundColor(Color.WHITE);
				edText.setHint("Enter Brand Composition");
				edText.setTextSize(18);
				edText.setPadding(10, 5, 0, 5);
				edText.setTextColor(Color.BLACK);
				setId(edText);

				LLComposition.addView(edText);

			}
		});
		RemoveComposition.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (id != 0) {
					EditText edText = (EditText) findViewById(id--);
					LLComposition.removeView(edText);

				}
			}
		});

		SaveData.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				BrandDetailString = ETBrandDetail.getText().toString();
				BrandCompositionString = ETBrandComposition.getText()
						.toString();
				CompanyDetailString = ETCompanyDetail.getText().toString();
				BrandMRPString = ETBrandMRP.getText().toString();
				CategoryString = Category.getText().toString();
				DesString = Description.getText().toString();
				

				if (notEmptyfield()) {
					try {
						
						int parentId = setTable1();
						setTable2(parentId);
						setTable3();
						Toast.makeText(getApplicationContext(), "Yes!!",
								Toast.LENGTH_LONG).show();
						reset();
					} catch (NumberFormatException e) {
						Toast.makeText(getApplicationContext(),
								"MRP not resolved", Toast.LENGTH_LONG).show();
					}

				} else {
					Toast.makeText(getApplicationContext(),
							"One or more field empty", Toast.LENGTH_LONG)
							.show();

				}
			}
		});
	}

	private void setId(EditText edText) {
		edText.setId(++id);

	}

	private int setTable1() throws NumberFormatException {
		ContentValues values = new ContentValues();
		double mrp = Double.parseDouble(BrandMRPString);
		values.put("BRAND_NAME", BrandDetailString.toUpperCase());
		values.put("COMPANY", CompanyDetailString.toUpperCase());
		values.put("MRP", mrp);
		values.put("CATEGORY", CategoryString);
		values.put("DESCRIPTION", DesString);

		long ll = db.insertData(values, true);
		Toast.makeText(getApplicationContext(), "Inserted..." + ll,
				Toast.LENGTH_LONG).show();
		Cursor c = db.getData("Select MAX(DRUG_ID) from BRAND");
		c.moveToFirst();
		int column_no = c.getInt(0);

		return column_no;
	}

	private void setTable2(int parentId) {
		Toast.makeText(getApplicationContext(), "" + parentId,
				Toast.LENGTH_LONG).show();
		ContentValues values = new ContentValues();
		values.put("PARENT_ID", parentId);
		values.put("COMPOSITION", BrandCompositionString.toUpperCase());
		
		db.insertData(values, false);
		for (int i = 1; i <= id; i++) {
			EditText ed = (EditText) findViewById(i);
			String Comp = ed.getText().toString();
			values.put("COMPOSITION", Comp.toUpperCase());
			db.insertData(values, false);
		}
	}

	private void setTable3() {
		ContentValues values = new ContentValues();

		values.put("CATEGORY", CategoryString.toUpperCase());
		db.insertTable3(values);

	}

	private boolean notEmptyfield() {
		boolean emptyVar0 = (BrandCompositionString == null)
				|| (BrandMRPString == null) || (BrandDetailString == null)
				|| (CompanyDetailString == null) || (DesString == null)
				|| (CategoryString == null);
		boolean emptyVar1 = BrandCompositionString.isEmpty()
				|| BrandMRPString.isEmpty() || BrandDetailString.isEmpty()
				|| CompanyDetailString.isEmpty() || CategoryString.isEmpty()
				|| DesString.isEmpty();
		if (emptyVar0 || emptyVar1) {
			return false;
		}
		for (int i = 1; i <= id; i++) {
			EditText ed = (EditText) findViewById(i);
			String s = ed.getText().toString();
			if (s == null || s.isEmpty()) {
				return false;
			}

		}
		return true;
	}

	private void reset() {
		LLComposition.removeAllViews();
		LLComposition.addView(ETBrandComposition);
		ETBrandDetail.setText("");
		ETBrandComposition.setText("");
		ETBrandMRP.setText("");
		ETCompanyDetail.setText("");
		Category.setText("");
		Description.setText("");
	}

}