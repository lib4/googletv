package com.lib4.googletv.fragments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib4.googletv.R;
import com.lib4.googletv.network.HTTPResponseListener;
import com.lib4.googletv.network.HttpHandler;
import com.lib4.googletv.util.Constants;

public class HomeFragment extends Fragment implements LocationListener,
		HTTPResponseListener {

	LinearLayout mHomeLayout;

	TextView timeText;
	TextView weatherText;

	Handler mHandler = new Handler();
	LocationManager Locationm;
	public static Location lastbestlocation_trck = null;
	public static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	public static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	Handler handler;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mHomeLayout = (LinearLayout) inflater.inflate(R.layout.home_fragment,
				container, false);

		timeText = (TextView) mHomeLayout.findViewById(R.id.timetext);
		weatherText = (TextView) mHomeLayout.findViewById(R.id.weathertext);

		handler = new Handler();

		init();
		return mHomeLayout;
	}

	private void init() {

		updateTimer();
		startTimer(getActivity());

		Locationm = (LocationManager) getActivity().getSystemService(
				getActivity().LOCATION_SERVICE);

		Locationm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

	}

	private void updateTimer() {
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		Log.e("TIME", "test " + dateFormat.format(date));
		Log.e("HOUR ", "test " + Calendar.getInstance().get(Calendar.HOUR));
		Log.e("MINUTE ", "test " + Calendar.getInstance().get(Calendar.MINUTE));

		if (Calendar.getInstance().get(Calendar.AM_PM) == 1)
			timeText.setText(dateFormat.format(date) + " PM");
		else
			timeText.setText(dateFormat.format(date) + " AM");

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		try {
			double latitude = 0, longitude = 0;

			latitude = location.getLatitude();
			longitude = location.getLongitude();

			Log.e("Lattitude & Longitude ", "" + latitude + " " + longitude);

			new HttpHandler().getWeather(getActivity().getApplicationContext(),
					this, "" + latitude, "" + longitude);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSuccess() {
		try {
			handler.post(new Runnable() {

				@Override
				public void run() {

					weatherText.setText(Constants.CUR_TEMP + ""
							+ getActivity().getString(R.string.celsius));

				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onFailure(int failureCode, String message) {
		// TODO Auto-generated method stub

	}

	private void startTimer(Context context) {

		new Timer().schedule(new UpdateTimeTask(), 1000, 1000);
	}

	class UpdateTimeTask extends TimerTask {
		public void run() {
			mHandler.post(new Runnable() {

				@Override
				public void run() {

					updateTimer();

				}
			});
		}
	}

}