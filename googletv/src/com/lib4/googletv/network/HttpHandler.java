package com.lib4.googletv.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UTFDataFormatException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lib4.googletv.util.Constants;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class HttpHandler extends Thread {

	private static final int UNABLE_TO_REACH_SERVER = 0;

	public static String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather"; //"http://10.76.72.16:8080/NextMove-war";//"http://192.168.1.100:8080/NextMove-war";
	String url;
	String requestBody;
	Context context;
	String requestType;
	HTTPResponseListener mHttpResponseListener;
	String UANBLETOREEACHSERVER	=	"Server is temperorily not available. Please try after some time.";
	String NONETWORK				=	"Your are not connected to internet. Please check your network settings and try again!";
	

	/**
	 * 
	 * 
	 * Function calls the ServerConnection gateway once the response is received
	 * Response will sent to appropriate response handled method. which in turn
	 * stores the data in to RecordStore.
	 */
	public void getWeather(Context context,
			HTTPResponseListener mHttpResponseListener,String lat,String lng) {

		url = WEATHER_URL+"?lat="+lat+"&lon="+lng;
		this.context = context;
		this.mHttpResponseListener = mHttpResponseListener;

		
	
		start();
	}

	
	/**
	 * Method Responsible for the following:- 1 Initiating Server Connection 2
	 * Initiating the Response Handling ie passing the response to appropriate
	 * parser class. 3.Trigger the UI events
	 */
	public void run() {
		
		try {

			Connection mConnection = new Connection();
			try {
				String response 	=	mConnection.connect(url, requestBody, requestType);
				
				JSONObject mJsonObject	=	new JSONObject(response);
				JSONObject weather 	=	mJsonObject.getJSONObject("main");
				if(weather!=null){
					
					Double kelvin  	=	weather.getDouble("temp");
					Double temp 	=	 (-272.15+(kelvin));
					Constants.CUR_TEMP	=	temp.intValue();
					mHttpResponseListener.onSuccess();
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// Exception will be handled based on response code obtained.
				mHttpResponseListener.onFailure(UNABLE_TO_REACH_SERVER, UANBLETOREEACHSERVER);
				return;
			}
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// responseResolver(mServerConnection);
	}



	public boolean isNetworkOnline() {
		boolean status = true;
		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getNetworkInfo(0);
			if (netInfo != null
					&& netInfo.getState() == NetworkInfo.State.CONNECTED) {
				status = true;
			} else {
				netInfo = cm.getNetworkInfo(1);
				if (netInfo != null
						&& netInfo.getState() == NetworkInfo.State.CONNECTED)
					status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return status;

	}
}
