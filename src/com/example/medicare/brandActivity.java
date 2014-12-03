package com.example.medicare;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class brandActivity extends Activity {
	ArrayAdapter<String> brand_adapter;
	Array brandName;
	DBHelper db;
	ImageView ivSearch;
	LinearLayout llList;
	AutoCompleteTextView textview;
	TextView nameComposition;
	private final int name_composition_id = 15;
	private final int TABLE_COMPOSITION_ID = 45;
	private final int TABLE_ALTERNATE_ID = 56;
	private final int TABLE_DESC_ID = 96;
	String HttpUrlCode = "5000";// check the port and status of code to
								// configure the connection

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brand_name_activity);

		db = new DBHelper(getApplicationContext());
		llList = (LinearLayout) findViewById(R.id.llList);
		ivSearch = (ImageView) findViewById(R.id.ivSearch);
		Cursor c = db.getData("select BRAND_NAME from BRAND");
		ArrayList<String> list = new ArrayList<String>();
		while (c.moveToNext()) {
			list.add(c.getString(0));

		}
		brand_adapter = new ArrayAdapter<>(getApplicationContext(),
				R.layout.brand_adapter, list);

		textview = (AutoCompleteTextView) findViewById(R.id.atvBrand);

		textview.setAdapter(brand_adapter);

		ivSearch.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (!textview.getText().toString().isEmpty()) {
					reset();
					tableComposition();

					// Cursor c2 = db.getData("select * from BRAND");
					// reset();

				}

			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		new FindData(this).execute(HttpUrlCode);
	}

	private void reset() {

		if (llList.getChildCount() > 1) {
			// Toast.makeText(getApplicationContext(),
			// "" + llList.getChildCount(), Toast.LENGTH_LONG).show();
			TableLayout tl = (TableLayout) findViewById(TABLE_COMPOSITION_ID);
			TableLayout t2 = (TableLayout) findViewById(TABLE_ALTERNATE_ID);
			TableLayout t3 = (TableLayout) findViewById(TABLE_DESC_ID);
			llList.removeView(tl);
			llList.removeView(t2);
			llList.removeView(t3);
		}
	}

	@SuppressLint("NewApi")
	private void tableComposition() {
		String textName = textview.getText().toString().toUpperCase();

		Cursor c1 = getComposition(textName);
		if (c1 != null) {
			TextView composition;
			composition = new TextView(getApplicationContext());
			nameComposition = new TextView(getApplicationContext());
			llList = (LinearLayout) findViewById(R.id.llList);
			TableLayout tableComposition = new TableLayout(
					getApplicationContext());
			tableComposition.setId(TABLE_COMPOSITION_ID);
			// reset();
			TableRow tr = new TableRow(getApplicationContext());
			TableRow tr1 = new TableRow(getApplicationContext());

			composition.setText("Composition");
			composition.setTextSize(18.0f);
			composition.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
			composition.setTypeface(null, Typeface.BOLD_ITALIC);
			composition.setPadding(4, 4, 4, 4);
			composition.setTextColor(Color.BLACK);

			tr.addView(composition);

			tableComposition.addView(tr);

			String compo = "";
			while (c1.moveToNext()) {
				compo += c1.getString(0) + ", ";

			}
			if (compo.length() > 0) {
				compo = compo.substring(0, compo.length() - 2);
			}
			nameComposition.setText(compo);
			nameComposition.setTextSize(16.0f);
			nameComposition.setTextColor(Color.BLACK);
			nameComposition.setTypeface(null, Typeface.ITALIC);
			nameComposition.setId(name_composition_id);
			nameComposition.setPadding(4, 4, 4, 4);
			tr1.addView(nameComposition);
			tableComposition.addView(tr1);

			llList.addView(tableComposition);
			tableAlternate();
			tableDescription();
		}
	}

	private void tableDescription() {
		TextView desc, descData;
		Cursor c = getDesc();
		desc = new TextView(getApplicationContext());
		descData = new TextView(getApplicationContext());
		TableLayout tableDesc;
		tableDesc = new TableLayout(getApplicationContext());
		tableDesc.setId(TABLE_DESC_ID);
		tableDesc.setScrollContainer(true);
		TableRow tr;
		tr = new TableRow(getApplicationContext());

		desc.setText("Use ");
		desc.setTextColor(Color.BLACK);
		desc.setPadding(5, 15, 5, 10);
		desc.setTextSize(18.0f);
		desc.setTypeface(null, Typeface.BOLD_ITALIC);
		if (c.moveToNext()) {
			descData.setText(c.getString(0));
		}
		descData.setTextSize(16.0f);
		descData.setTypeface(null, Typeface.ITALIC);
		descData.setTextColor(Color.BLACK);
		descData.setPadding(5, 15, 5, 10);
		descData.setSingleLine(false);
		descData.setLines(5);
		descData.setWidth(300);
		tr.addView(desc);
		tr.addView(descData);
		tableDesc.addView(tr);
		llList.addView(tableDesc);
	}

	private void tableAlternate() {
		TextView name, mrp, company, category, equivalent;
		Cursor c = getAlternateName();
		name = new TextView(getApplicationContext());
		mrp = new TextView(getApplicationContext());
		company = new TextView(getApplicationContext());
		category = new TextView(getApplicationContext());
		equivalent = new TextView(getApplicationContext());
		llList = (LinearLayout) findViewById(R.id.llList);
		TableLayout tableAlternate;
		tableAlternate = new TableLayout(getApplicationContext());
		tableAlternate.setId(TABLE_ALTERNATE_ID);
		tableAlternate.setScrollContainer(true);
		// reset();
		TableRow tr = new TableRow(getApplicationContext());
		TableRow tr2 = new TableRow(getApplicationContext());

		equivalent.setText("Similar Medicine");
		equivalent.setTypeface(null, Typeface.BOLD);
		equivalent.setTextColor(Color.BLACK);
		equivalent.setTextSize(20.0f);
		equivalent.setPadding(60, 5, 0, 5);

		category.setText("Form");
		category.setTypeface(null, Typeface.BOLD_ITALIC);
		category.setTextSize(18.0f);
		category.setPadding(4, 4, 4, 4);
		category.setTextColor(Color.BLACK);

		name.setText("Name");
		name.setTypeface(null, Typeface.BOLD_ITALIC);
		name.setTextSize(18.0f);
		name.setPadding(4, 4, 4, 4);
		name.setTextColor(Color.BLACK);

		mrp.setText("M.R.P");
		mrp.setTextColor(Color.BLACK);
		mrp.setTextSize(18.0f);
		mrp.setPadding(4, 4, 4, 4);
		mrp.setTypeface(null, Typeface.BOLD_ITALIC);

		company.setText("Company");
		company.setTextColor(Color.BLACK);
		company.setPadding(4, 4, 4, 4);
		company.setTextSize(18.0f);
		company.setTypeface(null, Typeface.BOLD_ITALIC);

		// tr2.addView(equivalent);

		tr.addView(category);
		tr.addView(name);
		tr.addView(company);
		tr.addView(mrp);

		tableAlternate.addView(equivalent);
		tableAlternate.addView(tr);
		while (c.moveToNext()) {
			TextView nameData, mrpData, companyData, categoryData;
			nameData = new TextView(getApplicationContext());
			mrpData = new TextView(getApplicationContext());
			companyData = new TextView(getApplicationContext());
			categoryData = new TextView(getApplicationContext());
			TableRow tr1 = new TableRow(getApplicationContext());

			categoryData.setText(c.getString(4));
			categoryData.setTextColor(Color.BLACK);
			categoryData.setTextSize(16.0f);
			categoryData.setPadding(4, 4, 4, 4);
			categoryData.setTypeface(null, Typeface.ITALIC);

			nameData.setText(c.getString(1));
			nameData.setTextColor(Color.BLACK);
			nameData.setTextSize(16.0f);
			nameData.setPadding(4, 4, 4, 4);
			nameData.setTypeface(null, Typeface.ITALIC);

			mrpData.setText(c.getString(3));
			mrpData.setTextColor(Color.BLACK);
			mrpData.setTextSize(16.0f);
			mrpData.setPadding(4, 4, 4, 4);
			mrpData.setTypeface(null, Typeface.ITALIC);

			companyData.setText(c.getString(2));
			companyData.setTextColor(Color.BLACK);
			companyData.setTextSize(16.0f);
			companyData.setPadding(4, 4, 4, 4);
			companyData.setTypeface(null, Typeface.ITALIC);

			tr1.addView(categoryData);
			tr1.addView(nameData);
			tr1.addView(companyData);
			tr1.addView(mrpData);

			tableAlternate.addView(tr1);
		}

		llList.addView(tableAlternate);

	}

	private Cursor getComposition(String BrandName) {
		Cursor id = db.getData("select DRUG_ID from BRAND where BRAND_NAME='"
				+ BrandName + "'");

		Cursor composition = null;
		if (id.moveToFirst()) {

			int parentId = id.getInt(0);
			composition = db
					.getData("Select COMPOSITION from COMPOSITION where PARENT_ID="
							+ parentId);

		} else {
			Toast.makeText(getApplicationContext(),
					"Name doesn't exist in database", Toast.LENGTH_LONG).show();
		}
		return composition;
	}

	private Cursor getAlternateName() {
		TextView tv = (TextView) findViewById(name_composition_id);
		String Composition = tv.getText().toString();
		StringTokenizer tokens = new StringTokenizer(Composition, ",");
		String queryFooter = "COMPOSITION =";
		String queryConnector = " or ";
		while (tokens.hasMoreTokens()) {
			queryFooter += "'" + tokens.nextToken() + "'" + queryConnector;
			// Toast.makeText(getApplicationContext(), queryFooter,
			// Toast.LENGTH_LONG).show();
		}
		queryFooter = queryFooter.substring(0,
				queryFooter.lastIndexOf(queryConnector));
		// Toast.makeText(getApplicationContext(), queryFooter,
		// Toast.LENGTH_LONG)
		// .show();
		Cursor id = db.getData("select PARENT_ID from COMPOSITION where "
				+ queryFooter);

		String queryFooterDrugId = "DRUG_ID =";

		int prevId = -1;
		while (id.moveToNext()) {
			int a = id.getInt(0);
			// if(a!=prevId){
			queryFooterDrugId += a + queryConnector;
			queryFooterDrugId += "DRUG_ID=";
			// }
			/*
			 * else{
			 * 
			 * } prevId=a;
			 */
		}
		queryFooterDrugId = queryFooterDrugId.substring(0,
				queryFooterDrugId.lastIndexOf(queryConnector));
		Cursor name = db.getData("select * from BRAND where "
				+ queryFooterDrugId);
		// Toast.makeText(getApplicationContext(),"select * from BRAND where "+queryFooterDrugId,Toast.LENGTH_LONG).show();
		return name;
	}

	private Cursor getDesc() {
		TextView tv = (TextView) findViewById(name_composition_id);
		String Composition = tv.getText().toString();
		StringTokenizer tokens = new StringTokenizer(Composition, ",");
		String queryFooter = "COMPOSITION =";
		String queryConnector = " or ";
		while (tokens.hasMoreTokens()) {
			queryFooter += "'" + tokens.nextToken() + "'" + queryConnector;
			// Toast.makeText(getApplicationContext(), queryFooter,
			// Toast.LENGTH_LONG).show();
		}
		queryFooter = queryFooter.substring(0,
				queryFooter.lastIndexOf(queryConnector));
		// Toast.makeText(getApplicationContext(), queryFooter,
		// Toast.LENGTH_LONG)
		// .show();
		Cursor id = db.getData("select PARENT_ID from COMPOSITION where "
				+ queryFooter);
		id.moveToFirst();
		int a = id.getInt(0);
		Cursor desc = db
				.getData("select DESCRIPTION from BRAND where DRUG_ID =" + a);
		return desc;

	}

}