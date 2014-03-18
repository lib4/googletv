package com.lib4.googletv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.lib4.googletv.entity.AppInfo;
import com.lib4.googletv.fragments.BaseFragment;
import com.lib4.googletv.util.Constants;
import com.lib4.googletv.util.PInfo;
import com.lib4.googletv.util.PinterestUI;

public class MainActivity extends Activity {

	ImageHolderView mHolderView;
	private String TAG = MainActivity.class.getCanonicalName();

	private ImageView hotelCityInfo, internetApps, tvOnlineRadio, newsSports,
			games, hotelInfo, home;
	LinearLayout mAppsLinearLayout;
	PinterestUI mPinterestUI;
	Context mContext;
	ScrollView mScrollView;
	ArrayList<AppInfo> installedApps;
	ImageView Settings;
	int CATEGORY = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_frame);
		mHolderView = (ImageHolderView) findViewById(R.id.parentholder);
//		mHolderView.setImages(new int[] { R.drawable.hotel_images_00,
//				R.drawable.hotel_images_01, R.drawable.hotel_images_02,
//				R.drawable.hotel_images_03, R.drawable.hotel_images_04,
//				R.drawable.hotel_images_05, R.drawable.hotel_images_06,
//				R.drawable.hotel_images_07, R.drawable.hotel_images_08 });
		
		
		
		mHolderView.setImages(new int[] { R.drawable.slideimage_1,
				R.drawable.slideimage_2, R.drawable.slideimage_3,
				R.drawable.slideimage_4});
		//PInfo mInfo = new PInfo(this);
		mAppsLinearLayout = (LinearLayout) findViewById(R.id.app_holder);
		//installedApps = mInfo.getInstalledAppInfo();
		mContext = this;
		/*
		final int max = installedApps.size();
		for (int i = 0; i < max; i++) {
			AppInfo mAppInfo = installedApps.get(i);
			ImageView mImageView = new ImageView(this);
			mImageView
					.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT,
							LinearLayout.LayoutParams.WRAP_CONTENT));
			mImageView.setBackgroundDrawable(mAppInfo.icon);
			// mAppsLinearLayout.addView(mImageView);

		}
		*/
		CATEGORY = 1;

		hotelCityInfo = (ImageView) findViewById(R.id.htl_city_info);
		internetApps = (ImageView) findViewById(R.id.internet_apps);
		tvOnlineRadio = (ImageView) findViewById(R.id.tv_radio);
		newsSports = (ImageView) findViewById(R.id.news_sports);
		games = (ImageView) findViewById(R.id.games);
		hotelInfo = (ImageView) findViewById(R.id.hotel_info);
		hotelCityInfo.setTag(Constants.HOTEL_CITY_INFO_FLAG);
		internetApps.setTag(Constants.INTERNET_APPS_FLAG);
		tvOnlineRadio.setTag(Constants.TV_ONLINE_RADIO_FLAG);
		newsSports.setTag(Constants.NEWS_SPORTS_FLAG);
		games.setTag(Constants.GAMES_FLAG);
		hotelInfo.setTag(Constants.HOTEL_INFO_FLAG);

		hotelCityInfo.setOnClickListener(showAppsListener);
		internetApps.setOnClickListener(showAppsListener);
		tvOnlineRadio.setOnClickListener(showAppsListener);
		newsSports.setOnClickListener(showAppsListener);
		games.setOnClickListener(showAppsListener);
		hotelInfo.setOnClickListener(showAppsListener);

		Settings = (ImageView) findViewById(R.id.settings);
		Settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Calling the next Activity.
				Intent intent = new Intent(MainActivity.this,
						SignInActivity.class);
				startActivity(intent);
			}
		});

		home = (ImageView) findViewById(R.id.home);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	@Override
	public void onResume() {
		super.onResume();
		//populateScreen();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void populateScreen() {

		Log.e("CATEGORY ", " " + CATEGORY);

		//highLightSelected();

		switch (CATEGORY) {

		case Constants.HOTEL_CITY_INFO_FLAG:

			//showApps(Constants.HOTEL_CITY_INFO_FLAG);
			switchToActivity(HotelCityInfoActivity.class,null,null);
			break;
		case Constants.INTERNET_APPS_FLAG:
			//showApps(Constants.INTERNET_APPS_FLAG);

			switchToActivity(InternetAppsActivity.class,"Internet Apps",null);
			break;
		case Constants.TV_ONLINE_RADIO_FLAG:
			String url	=	"http://www.siriusxm.com/android";
			//showApps(Constants.TV_ONLINE_RADIO_FLAG);
			switchToActivity(WebViewActivity.class,"Online Radio",url );
			break;

		case Constants.NEWS_SPORTS_FLAG:

			//showApps(Constants.NEWS_SPORTS_FLAG);
			switchToActivity(WebViewActivity.class,"Sports News","http://www.foxsports.com.au/mobile");
			break;
		case Constants.GAMES_FLAG:

			//showApps(Constants.GAMES_FLAG);
			switchToActivity(InternetAppsActivity.class,"Games",null);
			break;
		case Constants.HOTEL_INFO_FLAG:

			switchToActivity(HotelCityInfoActivity.class,null,null);
			break;
		}

	}

	private void showApps(int CATEGORY) {

		if (mPinterestUI != null) {
			mAppsLinearLayout.removeView(mPinterestUI);
		}
		mPinterestUI = new PinterestUI(mContext,
				getAppsForThisCategory(Constants.CATEGORY_MAP.get(CATEGORY)),
				1, CATEGORY);
		mPinterestUI.createLayout();
		mAppsLinearLayout.addView(mPinterestUI);
		mAppsLinearLayout.setVisibility(View.VISIBLE);		

	}
	
	private void switchToActivity(Class CLASSNAME,String Title,String url){
		

		// Calling the next Activity.
		Intent intent = new Intent(MainActivity.this,
				CLASSNAME);
		intent.putExtra("title", Title);
		intent.putExtra("url", url);
		startActivity(intent);

	}

	private ArrayList<AppInfo> getAppsForThisCategory(String Key) {

		Log.e("KEY ", " " + Key);
		final SharedPreferences prefs = getSharedPreferences(Key,

		Context.MODE_PRIVATE);
		Set<String> set = new HashSet<String>();
		Set<String> stored = prefs.getStringSet(Key, set);
		Iterator mIterator = stored.iterator();
		while (mIterator.hasNext()) {

			String str = (String) mIterator.next();

			Log.e("STR ", "" + str);

		}

		ArrayList<AppInfo> cateogryApps = new ArrayList<AppInfo>();
		final int max = installedApps.size();
		for (int i = 0; i < max; i++) {
			AppInfo mAppInfo = installedApps.get(i);
			if (stored.contains(mAppInfo.appname)) {
				cateogryApps.add(mAppInfo);
				Log.e("FOUND  ", "" + mAppInfo.appname);
			} else {
				Log.e("mAppInfo ", "" + mAppInfo.appname);
			}

		}

		return cateogryApps;

	}

	private View.OnClickListener showAppsListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// v.requestFocus();
			// v.setPressed(true);
			CATEGORY = (Integer) v.getTag();
			populateScreen();

		}
	};

	private void highLightSelected() {

		hotelCityInfo.setImageResource(R.drawable.htl_city_info);
		internetApps.setImageResource(R.drawable.internet_apps_button);
		tvOnlineRadio.setImageResource(R.drawable.tv_radio_button);
		newsSports.setImageResource(R.drawable.news_sports_button);
		games.setImageResource(R.drawable.games_button);
		hotelInfo.setImageResource(R.drawable.hotel_info_button);

		switch (CATEGORY) {

		case Constants.HOTEL_CITY_INFO_FLAG:
			hotelCityInfo.setImageResource(R.drawable.htl_city_info_selection);
			break;

		case Constants.INTERNET_APPS_FLAG:
			internetApps.setImageResource(R.drawable.internet_apps_selection);
			break;

		case Constants.TV_ONLINE_RADIO_FLAG:
			tvOnlineRadio.setImageResource(R.drawable.tv_radtion_selection);
			break;

		case Constants.NEWS_SPORTS_FLAG:
			newsSports.setImageResource(R.drawable.news_sports_selection);
			break;

		case Constants.GAMES_FLAG:
			games.setImageResource(R.drawable.games_selection);
			break;

		case Constants.HOTEL_INFO_FLAG:
			hotelInfo.setImageResource(R.drawable.hotel_info_selection);
			break;

		}

	}

}
