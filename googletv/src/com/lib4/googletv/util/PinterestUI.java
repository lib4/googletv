package com.lib4.googletv.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib4.googletv.R;
import com.lib4.googletv.entity.AppInfo;

public class PinterestUI extends LinearLayout {
	private int NUM_COLUMN = 10;
	Context context;
	int SCREEN_WIDTH = 0;
	int SCREEN_HEIGHT = 0;

	LinearLayout NextLayout;
	int TOTAL_NUM_ITEMS = 20;
	int ITEM_DRAWN_INDEX = 0;
	ArrayList<AppInfo> mApps;

	public PinterestUI(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context = context;
	}

	public PinterestUI(Context context, ArrayList<AppInfo> mApps) {
		super(context);
		this.context = context;
		ITEM_DRAWN_INDEX = 0;
		TOTAL_NUM_ITEMS = mApps.size();
		
		this.mApps = mApps;
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);

		SCREEN_WIDTH = metrics.widthPixels;

		// ViewTreeObserver vto = getViewTreeObserver();
		// vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		//
		// @SuppressLint("NewApi")
		// @Override
		// public void onGlobalLayout() {
		// ITEM_DRAWN_INDEX++;
		// NextLayout = (LinearLayout) getChildAt(getLayoutIndexToAdd());
		// Log.e("NEXT LAOUT ",""+NEXTLAYOUT);
		// if (ITEM_DRAWN_INDEX >= TOTAL_NUM_ITEMS) {
		//
		// ViewTreeObserver obs = getViewTreeObserver();
		//
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
		// obs.removeOnGlobalLayoutListener(this);
		// } else {
		// obs.removeGlobalOnLayoutListener(this);
		// }
		// } else {
		// draw(ITEM_DRAWN_INDEX);
		// }
		// }
		//
		// });
	}

	public void createLayout() {

		System.out.println("IM HERE>>>>>>>>>>>" + SCREEN_WIDTH);
		for (int i = 0; i < NUM_COLUMN; i++) {

			LinearLayout mLinearLayout = new LinearLayout(context);
			mLinearLayout.setLayoutParams(new LayoutParams(SCREEN_WIDTH
					/ NUM_COLUMN,LayoutParams.WRAP_CONTENT));

			mLinearLayout.setOrientation(LinearLayout.VERTICAL);
			setOrientation(LinearLayout.HORIZONTAL);
			addView(mLinearLayout);
			//setGravity(Gravity.CENTER);
			System.out.println("IM HERE>>>>>>>>>>> Layout Creation" + i);
			//mLinearLayout.setBackgroundColor(Color.GREEN);
		}

		for (int i = 0; i < TOTAL_NUM_ITEMS; i++) {

			draw(i);
		}

	}

	private void draw(int index) {
//		if (NextLayout == null) {
//			NextLayout = (LinearLayout) getChildAt(0);
//		} else {
			NextLayout = (LinearLayout) getChildAt(getLayoutIndexToAdd());
//		}

			LayoutInflater inflater = (LayoutInflater)context.getSystemService
				      (Context.LAYOUT_INFLATER_SERVICE);
			
		LinearLayout mLinearLayout	=	(LinearLayout) inflater.inflate(R.layout.tile, null);
		
		ImageView imageView =	(ImageView) mLinearLayout.findViewById(R.id.appicon);
		final AppInfo mAppInfo = mApps.get(index);
		imageView.setImageDrawable(mAppInfo.icon);
		TextView title = (TextView) mLinearLayout.findViewById(R.id.appname);
		title.setText(mAppInfo.appname);
		title.setTextColor(Color.BLUE);
		mLinearLayout.startAnimation(AnimationUtils.loadAnimation(context,
				R.anim.scale_alpha));
		mLinearLayout.setTag(index);
//		mLinearLayout.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				PackageManager pm = context.getPackageManager();
//			
//				Intent intent=pm.getLaunchIntentForPackage(mAppInfo.pname);
//				context.startActivity(intent);
//			}
//		});
		
		
		
		mLinearLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			addToSharedPreference(Constants.HOTEL_CITY_INFO,(Integer)v.getTag());
				
			}
		});
		NextLayout.addView(mLinearLayout);
	}

	private static int NEXTLAYOUT = -1;

	private int getLayoutIndexToAdd() {

		if (NEXTLAYOUT >= NUM_COLUMN - 1) {
			NEXTLAYOUT = -1;
		}
		NEXTLAYOUT++;
		Log.e("NEXT LAYOUT "," "+NEXTLAYOUT);
		return NEXTLAYOUT;
	}
	
	private void addToSharedPreference(String Key,int index){
	
		final SharedPreferences prefs = context.getSharedPreferences(com.lib4.googletv.BaseActivity.class.getSimpleName(),
					
					Context.MODE_PRIVATE);
			Set<String> set = new HashSet<String>();
			set	=	prefs.getStringSet(Constants.HOTEL_CITY_INFO, set);
			AppInfo mAppInfo	=	mApps.get(index);
			set.add(mAppInfo.appname);
			SharedPreferences.Editor editor	=	 prefs.edit().clear();
			editor.putStringSet(Constants.HOTEL_CITY_INFO, set);
			editor.commit();
			Log.e("LOG ", "MESSAGE ");
			
			Set<String> stored	=	 prefs.getStringSet(Constants.HOTEL_CITY_INFO, set);
			int length 	=	stored.size();
			int i =0;
			Iterator mIterator	=	stored.iterator();
			while(mIterator.hasNext()){
				
				String str =	(String) mIterator.next();
				
				Log.e("STR ",""+str);
				
			}
			
			
	}

}
