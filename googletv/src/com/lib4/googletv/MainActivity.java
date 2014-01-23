package com.lib4.googletv;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.lib4.googletv.entity.AppInfo;
import com.lib4.googletv.util.PInfo;
import com.lib4.googletv.util.PinterestUI;

public class MainActivity extends Activity {

	ImageHolderView mHolderView;
	private String TAG = MainActivity.class.getCanonicalName();

	private ImageView hotelCityInfo;
	LinearLayout mAppsLinearLayout;
	PinterestUI mPinterestUI;
	Context mContext;
	ScrollView mScrollView	;
	ArrayList<AppInfo> mApps ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_frame);
		mHolderView = (ImageHolderView) findViewById(R.id.parentholder);
		mHolderView.setImages(new int[] { R.drawable.first, R.drawable.second,
				R.drawable.third, R.drawable.fourth, R.drawable.fifth });
		PInfo mInfo = new PInfo(this);
		mAppsLinearLayout = (LinearLayout) findViewById(R.id.app_holder);
		mApps = mInfo.getInstalledAppInfo();
		mContext	=	this;
	
		final int max = mApps.size();
		for (int i = 0; i < max; i++) {
			AppInfo mAppInfo = mApps.get(i);
			ImageView mImageView = new ImageView(this);
			mImageView
					.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT,
							LinearLayout.LayoutParams.WRAP_CONTENT));
			mImageView.setBackgroundDrawable(mAppInfo.icon);
			//mAppsLinearLayout.addView(mImageView);

		}

		
		hotelCityInfo	=	(ImageView) findViewById(R.id.htl_city_info);
		hotelCityInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				mPinterestUI	=	new PinterestUI(mContext,mApps);
				mPinterestUI.createLayout();
				mScrollView	=	new ScrollView(mContext);
				mScrollView.addView(mPinterestUI);
				mAppsLinearLayout.addView(mScrollView);
				mAppsLinearLayout.setVisibility(View.VISIBLE);
				
				
			}
		});
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
