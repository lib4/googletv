package com.lib4.googletv.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import android.view.LayoutInflater;
import android.view.View;

import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
	int CALLER_FLAG = 0;
	int CATEGORY = 0;
	String CATEGORY_NAME = "";

	public PinterestUI(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context = context;
	}

	public PinterestUI(Context context, ArrayList<AppInfo> mApps,
			int CALLER_FLAG, int CATEGORY) {
		super(context);
		NEXTLAYOUT = -1;
		this.context = context;
		ITEM_DRAWN_INDEX = 0;
		TOTAL_NUM_ITEMS = mApps.size();
		this.CALLER_FLAG = CALLER_FLAG;
		this.CATEGORY = CATEGORY;
		this.mApps = mApps;
		
		Log.e("APP SIZE "," "+mApps.size());
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);

		SCREEN_WIDTH = metrics.widthPixels;
		CATEGORY_NAME	=	Constants.CATEGORY_MAP.get(CATEGORY);
	

	}

	public void createLayout() {
		removeAllViews();
		System.out.println("IM HERE>>>>>>>>>>>" + SCREEN_WIDTH);
		
		if(mApps.size()==0){
			
			LinearLayout mLinearLayout = new LinearLayout(context);
			mLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT
					, LayoutParams.WRAP_CONTENT));
			
			TextView mTextView	=	new TextView(context);
			mTextView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT
					, LayoutParams.WRAP_CONTENT));
			
			mTextView.setTextSize(25);
			mTextView.setText("No apps found for this category.");
			mLinearLayout.addView(mTextView);
			setGravity(Gravity.LEFT);
			
			addView(mLinearLayout);
			return;
			
		}
		
		
		for (int i = 0; i < NUM_COLUMN; i++) {

			LinearLayout mLinearLayout = new LinearLayout(context);
			mLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);//Orientation()
			mLinearLayout.setLayoutParams(new LayoutParams(SCREEN_WIDTH
					/ NUM_COLUMN, LayoutParams.WRAP_CONTENT));

			mLinearLayout.setOrientation(LinearLayout.VERTICAL);
			setOrientation(LinearLayout.HORIZONTAL);
			addView(mLinearLayout);
			// setGravity(Gravity.CENTER);
			System.out.println("IM HERE>>>>>>>>>>> Layout Creation" + i);
			// mLinearLayout.setBackgroundColor(Color.GREEN);
		}

		for (int i = 0; i < TOTAL_NUM_ITEMS; i++) {

			draw(i);
		}

	}

	private void draw(int index) {
		// if (NextLayout == null) {
		// NextLayout = (LinearLayout) getChildAt(0);
		// } else {
		NextLayout = (LinearLayout) getChildAt(getLayoutIndexToAdd());
		// }

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		final LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(
				R.layout.tile, null);

		ImageView imageView = (ImageView) mLinearLayout
				.findViewById(R.id.appicon);
		final AppInfo mAppInfo = mApps.get(index);
		imageView.setImageDrawable(mAppInfo.icon);
		TextView title = (TextView) mLinearLayout.findViewById(R.id.appname);
		title.setText(mAppInfo.appname);
		title.setTextColor(Color.BLUE);
		mLinearLayout.startAnimation(AnimationUtils.loadAnimation(context,
				R.anim.scale_alpha));
		mLinearLayout.setTag(index);

		if (CALLER_FLAG == 1) {

			ImageView mImageView = (ImageView) mLinearLayout
					.findViewById(R.id.tickmark);
			mImageView.setVisibility(View.GONE);

			mLinearLayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					PackageManager pm = context.getPackageManager();

					Intent intent = pm
							.getLaunchIntentForPackage(mAppInfo.pname);
					context.startActivity(intent);
				}
			});
		} else {

			
			if(isThisAppAlreadyAdded(mAppInfo.appname,CATEGORY_NAME)){
				ImageView mImageView = (ImageView) mLinearLayout
						.findViewById(R.id.tickmark);
				mImageView.setVisibility(View.VISIBLE);

			}else{
				ImageView mImageView = (ImageView) mLinearLayout
						.findViewById(R.id.tickmark);
				mImageView.setVisibility(View.GONE);

			}
			mLinearLayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					if(isThisAppAlreadyAdded(mAppInfo.appname,CATEGORY_NAME)){
						ImageView mImageView = (ImageView) mLinearLayout
								.findViewById(R.id.tickmark);
						mImageView.setVisibility(View.GONE);
						removeFromSharedPreference(CATEGORY_NAME, mAppInfo.appname);
						
					}else{
						
						boolean isAdded 	=	addToSharedPreference(CATEGORY_NAME,mAppInfo.appname);
						if(isAdded){
							ImageView mImageView = (ImageView) mLinearLayout
									.findViewById(R.id.tickmark);
							mImageView.setVisibility(View.VISIBLE);	
						}
					}

				}
			});
		}
		NextLayout.addView(mLinearLayout);
	}

	private static int NEXTLAYOUT = -1;

	private int getLayoutIndexToAdd() {

		if (NEXTLAYOUT >= NUM_COLUMN - 1) {
			NEXTLAYOUT = -1;
		}
		NEXTLAYOUT++;
		Log.e("NEXT LAYOUT ", " " + NEXTLAYOUT);
		return NEXTLAYOUT;
	}

	private boolean addToSharedPreference(String Key, String appName) {

		final SharedPreferences prefs = context.getSharedPreferences(
				Key,

				Context.MODE_PRIVATE);
		Set<String> set = new HashSet<String>();
		set = prefs.getStringSet(Key, set);

		if (set.size() <= 20) {
			
			set.add(appName);
			SharedPreferences.Editor editor = prefs.edit().clear();
			editor.putStringSet(Key, set);
			editor.commit();
			Set<String> stored = prefs.getStringSet(Key,
					set);
			Iterator mIterator = stored.iterator();
			while (mIterator.hasNext()) {

				String str = (String) mIterator.next();

				Log.e("STR ", "" + str);

			}
			return true;
		} else {

			showAlertDialog("You cannot add more than 20 apps in to each category.");
			return false;
		}

	}
	
	
	private void removeFromSharedPreference(String Key, String appName) {

		final SharedPreferences prefs = context.getSharedPreferences(
				Key,
				Context.MODE_PRIVATE);
		Set<String> set = new HashSet<String>();
		set = prefs.getStringSet(Key, set);
		
		if(set!=null){
			set.remove(appName);
		}
		
		Log.e("Removed ","Removed");

	}

	private void showAlertDialog(String messgae) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		// alertDialogBuilder.setTitle("Your Title");

		// set dialog message
		alertDialogBuilder.setMessage(messgae).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, close
						// current activity

					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

	private boolean isThisAppAlreadyAdded(String appName, String category) {

		Log.e("	CATEGORY_NAME	=	Constants.CATEGORY_MAP.get(CATEGORY);",""+category);
		final SharedPreferences prefs = context.getSharedPreferences(
				category,

				Context.MODE_PRIVATE);
		Set<String> set = new HashSet<String>();
		Set<String> stored = prefs.getStringSet(category, set);

		if (stored != null && stored.contains(appName)) {
			return true;
		} else {
			return false;
		}

	}

}
