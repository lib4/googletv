package com.lib4.googletv.fragments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lib4.googletv.R;
import com.lib4.googletv.entity.AppInfo;
import com.lib4.googletv.util.Constants;
import com.lib4.googletv.util.PInfo;
import com.lib4.googletv.util.PinterestUI;

public class InternetAppsFragment extends BaseFragment {

	LinearLayout mLinearLayout;
	ArrayList<AppInfo> installedApps;
	LinearLayout mAppsLinearLayout;
	Context mContext;
	PinterestUI mPinterestUI;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mLinearLayout = (LinearLayout) inflater.inflate(R.layout.internet_apps_fragment,
				container, false);

		PInfo mInfo = new PInfo(getActivity());
		installedApps = mInfo.getInstalledAppInfo();
		mContext = getActivity();
		mAppsLinearLayout	=	(LinearLayout) mLinearLayout.findViewById(R.id.apps_layout);
		
		showApps(1);
		return mLinearLayout;
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
	
	private ArrayList<AppInfo> getAppsForThisCategory(String Key) {

		
		
		ArrayList<AppInfo> cateogryApps = new ArrayList<AppInfo>();
		
		final int max = 20 ; //installedApps.size();
		for (int i = 0; i < max; i++) {
			AppInfo mAppInfo = installedApps.get(i);
			cateogryApps.add(mAppInfo);
//			ImageView mImageView = new ImageView(getActivity());
//			mImageView
//					.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
//							LinearLayout.LayoutParams.WRAP_CONTENT,
//							LinearLayout.LayoutParams.WRAP_CONTENT));
//			mImageView.setBackgroundDrawable(mAppInfo.icon);
//			mAppsLinearLayout.addView(mImageView);

		}
		
		
//		Log.e("KEY ", " " + Key);
//		final SharedPreferences prefs = getSharedPreferences(Key,
//		Context.MODE_PRIVATE);
//		Set<String> set = new HashSet<String>();
//		Set<String> stored = prefs.getStringSet(Key, set);
//		Iterator mIterator = stored.iterator();
//		while (mIterator.hasNext()) {
//
//			String str = (String) mIterator.next();
//
//			Log.e("STR ", "" + str);
//
//		}
//
//		ArrayList<AppInfo> cateogryApps = new ArrayList<AppInfo>();
//		final int max = installedApps.size();
//		for (int i = 0; i < max; i++) {
//			AppInfo mAppInfo = installedApps.get(i);
//			if (stored.contains(mAppInfo.appname)) {
//				cateogryApps.add(mAppInfo);
//				Log.e("FOUND  ", "" + mAppInfo.appname);
//			} else {
//				Log.e("mAppInfo ", "" + mAppInfo.appname);
//			}
//
//		}

		return cateogryApps;

	}

	
	
	

}