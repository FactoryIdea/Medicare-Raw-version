package com.example.medicare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FrontPage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.front_page);
		Thread timer = new Thread(){
			@SuppressWarnings("deprecation")
			public void run(){
				try{
					sleep(4000);					
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent i = new Intent(getApplicationContext(),brandActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					
				}
			}
		};
		timer.start();
	}
	
}
