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
import android.view.View.OnClickListener;
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
			hotel_info,home,back;
	ArrayList<AppInfo> mApps ;
	PinterestUI mPinterestUI;
	int CATEGORY	=	1;

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
		
		htl_city_info.setTag(Constants.HOTEL_CITY_INFO_FLAG);
		internet_apps.setTag(Constants.INTERNET_APPS_FLAG);
		tv_radio.setTag(Constants.TV_ONLINE_RADIO_FLAG);
		news_sports.setTag(Constants.NEWS_SPORTS_FLAG);
		games.setTag(Constants.GAMES_FLAG);
		hotel_info.setTag(Constants.HOTEL_INFO_FLAG);
		htl_city_info.setOnClickListener(showAppsListener);
		internet_apps.setOnClickListener(showAppsListener);
		tv_radio.setOnClickListener(showAppsListener);
		news_sports.setOnClickListener(showAppsListener);
		games.setOnClickListener(showAppsListener);
		hotel_info.setOnClickListener(showAppsListener);
		CATEGORY	=	1;
		loadAllInstalledApps();
		
		home	=	(ImageView) mSettingsLayout.findViewById(R.id.home);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});
		
		back	=	(ImageView) mSettingsLayout.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
				
			}
		});

	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		highLightSelected();
		showApps(CATEGORY);
		
		
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
	
	private void showApps(int CATEGORY){
		
		if(mPinterestUI!=null){
			mAppsLinearLayout.removeView(mPinterestUI);
		}
		mPinterestUI	=	new PinterestUI(getActivity(),mApps,2,CATEGORY);
		mPinterestUI.createLayout();
		//mScrollView	=	new ScrollView(mContext);
		//mScrollView.addView(mPinterestUI);
		mAppsLinearLayout.addView(mPinterestUI);
		mAppsLinearLayout.setVisibility(View.VISIBLE);
	}

	

	
	
	private View.OnClickListener  showAppsListener	= new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			CATEGORY	=	(Integer) v.getTag();
			highLightSelected();
			showApps(CATEGORY);
			
		}
	}; 
	
	private void highLightSelected(){
		
		htl_city_info.setImageResource(R.drawable.hotel_info_button);
		internet_apps.setImageResource(R.drawable.internet_apps_button);
		tv_radio.setImageResource(R.drawable.tv_radio_button);
		news_sports.setImageResource(R.drawable.news_sports_button);
		games.setImageResource(R.drawable.games_button);
		hotel_info.setImageResource(R.drawable.hotel_info_button);
		
		switch(CATEGORY){
		
				case Constants.HOTEL_CITY_INFO_FLAG:
					htl_city_info.setImageResource(R.drawable.htl_city_info_selection);
					break;
					
				case Constants.INTERNET_APPS_FLAG:
					internet_apps.setImageResource(R.drawable.internet_apps_selection);
					break;
					
				case Constants.TV_ONLINE_RADIO_FLAG:
					tv_radio.setImageResource(R.drawable.tv_radtion_selection);
					break;
					
				case Constants.NEWS_SPORTS_FLAG:
					news_sports.setImageResource(R.drawable.news_sports_selection);
					break;
					
				case Constants.GAMES_FLAG:
					games.setImageResource(R.drawable.games_selection);
					break;
					
				case Constants.HOTEL_INFO_FLAG:
					hotel_info.setImageResource(R.drawable.hotel_info_selection);
					break;
			
			
		
		}
	
		
	}

}
