package com.example.medicare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FindData extends AsyncTask<String, String, String> {
	private Activity context;
	private String resp;
	ProgressDialog pr;
	private String ConnectionUrl = "http://localhost:8080";

	public FindData(Activity a) {
		context = a;
		pr = new ProgressDialog(context);
		pr.setMessage("Fetching data...");
	}

	@Override
	  protected String doInBackground(String... params) {
	   publishProgress("Downloading..."); // Calls onProgressUpdate()
	   try {
	    // Do your long operations here and return the result
	    int time = Integer.parseInt(params[0]);
	    boolean isCode=false;//assuming internet is not connected
	    // Sleeping for given time period
	    Thread.sleep(time);
	    resp = "HttpUrlConnection Status: ";
	    
	    if(isCode){
	    	 try {
	             URL url = new URL(ConnectionUrl);           
	             HttpURLConnection connection = 
	                     (HttpURLConnection)url.openConnection();
	              
	             
	             BufferedReader reader = new BufferedReader(
	                     new InputStreamReader(connection.getInputStream()));
	              
	             StringBuffer json = new StringBuffer(1024);
	             String tmp="";
	             while((tmp=reader.readLine())!=null)
	                 json.append(tmp).append("\n");
	             reader.close();
	              
	             JSONObject data = new JSONObject(json.toString());
	              
	             // This value will be 404 if the request was not
	             // successful
	             if(data.getInt("cod") != 200){
	                 isCode=true;
	                 return null;
	             }
	              
	         }catch(Exception e){
	             
	         }
	     }   	
	    
	   
	    else{
	    	resp+=" not connected cache value is used";
	    }
	   }catch(Exception e){
		   resp+=" not connected cache value is used";
	   }
	   
	   	   return resp;
	   }

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// execution of result of Long time consuming operation
		// finalResult.setText(result);
		super.onPostExecute(result);
		pr.dismiss();
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// Things to be done before execution of long running operation. For
		// example showing ProgessDialog
		pr.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(String... text) {
		// finalResult.setText(text[0]);
		// Things to be done while execution of long running operation is in
		// progress. For example updating ProgessDialog

	}
}
