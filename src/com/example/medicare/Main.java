package com.example.medicare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity {
	TextView tvBrandName, tvComposition, tvAdmin,tvComposee;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		tvBrandName = (TextView) findViewById(R.id.tvBrandName);
		tvComposition = (TextView) findViewById(R.id.tvComposition);
		tvAdmin = (TextView) findViewById(R.id.tvAdmin);
		tvComposee=(TextView)findViewById(R.id.tvcomposee);
		tvBrandName.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),brandActivity.class);
				startActivity(i);
			}
		});
		tvAdmin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						AddDetail.class);
				startActivity(i);
			}
		});
		tvComposee.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),composee.class);
				startActivity(i);
			}
		});
		tvComposition.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i =new Intent(getApplicationContext(),Medical_List.class);
				startActivity(i);
				
			}
		});

	}
}
