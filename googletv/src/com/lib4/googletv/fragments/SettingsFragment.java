package com.lib4.googletv.fragments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class SettingsFragment extends BaseFragment {

	LinearLayout mSettingsLayout,mAppsLinearLayout;
	ImageView htl_city_info, internet_apps, tv_radio, news_sports, games,
			hotel_info;
	ArrayList<AppInfo> mApps ;
	PinterestUI mPinterestUI;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mSettingsLayout = (LinearLayout) inflater.inflate(
				R.layout.settings_fragment, container, false);
		init();
		return mSettingsLayout;
	}

	private void init() {

		htl_city_info = (ImageView) mSettingsLayout.findViewById(R.id.htl_city_info);
		internet_apps = (ImageView) mSettingsLayout.findViewById(R.id.internet_apps);
		tv_radio = (ImageView) mSettingsLayout.findViewById(R.id.tv_radio);
		news_sports = (ImageView) mSettingsLayout.findViewById(R.id.news_sports);
		games = (ImageView) mSettingsLayout.findViewById(R.id.games);
		hotel_info = (ImageView) mSettingsLayout.findViewById(R.id.hotel_info);
		mAppsLinearLayout	=	(LinearLayout) mSettingsLayout.findViewById(R.id.apps_layout);
		loadAllInstalledApps();
		showApps();

	}
	
	private void loadAllInstalledApps(){
		
		
		PInfo mInfo = new PInfo(getActivity());
		mApps = mInfo.getInstalledAppInfo();
		
	
		final int max = mApps.size();
		for (int i = 0; i < max; i++) {
			AppInfo mAppInfo = mApps.get(i);
			ImageView mImageView = new ImageView(getActivity());
			mImageView
					.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT,
							LinearLayout.LayoutParams.WRAP_CONTENT));
			mImageView.setBackgroundDrawable(mAppInfo.icon);
			//mAppsLinearLayout.addView(mImageView);

		}

	}
	
	private void showApps(){
		
		mPinterestUI	=	new PinterestUI(getActivity(),mApps);
		mPinterestUI.createLayout();
		//mScrollView	=	new ScrollView(mContext);
		//mScrollView.addView(mPinterestUI);
		mAppsLinearLayout.addView(mPinterestUI);
		mAppsLinearLayout.setVisibility(View.VISIBLE);
	}

	
	private void setToPreference(String packageName){
		
		final SharedPreferences prefs = getActivity().getSharedPreferences(com.lib4.googletv.BaseActivity.class.getSimpleName(),
				
				Context.MODE_PRIVATE);
		Set<String> set = new HashSet<String>();
		set	=	prefs.getStringSet(Constants.HOTEL_CITY_INFO, set);
		set.add("test");
		//SharedPreferences.Editor editor	=	 prefs.edit().clear();
		//editor.commit();
	}

}
