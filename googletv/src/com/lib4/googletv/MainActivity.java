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

	private ImageView hotelCityInfo;
	LinearLayout mAppsLinearLayout;
	PinterestUI mPinterestUI;
	Context mContext;
	ScrollView mScrollView;
	ArrayList<AppInfo> installedApps;
	ImageView Settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_frame);
		mHolderView = (ImageHolderView) findViewById(R.id.parentholder);
		mHolderView.setImages(new int[] { R.drawable.first, R.drawable.second,
				R.drawable.third, R.drawable.fourth, R.drawable.fifth });
		PInfo mInfo = new PInfo(this);
		mAppsLinearLayout = (LinearLayout) findViewById(R.id.app_holder);
		installedApps = mInfo.getInstalledAppInfo();
		mContext = this;

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

		hotelCityInfo = (ImageView) findViewById(R.id.htl_city_info);
		hotelCityInfo.setPressed(true);
		showApps();
		hotelCityInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showApps();

			}
		});

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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showApps() {

		mPinterestUI = new PinterestUI(mContext,  getAppsForThisCategory(Constants.HOTEL_CITY_INFO));
		mPinterestUI.createLayout();
		// mScrollView = new ScrollView(mContext);
		// mScrollView.addView(mPinterestUI);
		mAppsLinearLayout.addView(mPinterestUI);
		mAppsLinearLayout.setVisibility(View.VISIBLE);

	}

	private ArrayList<AppInfo> getAppsForThisCategory(String Key) {

		final SharedPreferences prefs = getSharedPreferences(
				com.lib4.googletv.BaseActivity.class.getSimpleName(),

				Context.MODE_PRIVATE);
		Set<String> set = new HashSet<String>();
		Set<String> stored = prefs.getStringSet(Constants.HOTEL_CITY_INFO, set);
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
			}else{
				Log.e("mAppInfo ", "" + mAppInfo.appname);
			}

		}
		
		return cateogryApps;

	}

}
