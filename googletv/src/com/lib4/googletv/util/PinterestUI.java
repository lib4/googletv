package com.lib4.googletv.util;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.lib4.googletv.R;
import com.lib4.googletv.entity.AppInfo;


public class PinterestUI extends LinearLayout {
	private int NUM_COLUMN = 5;
	Context context;
	int SCREEN_WIDTH = 0;
	int SCREEN_HEIGHT = 0;

	LinearLayout NextLayout;
	int TOTAL_NUM_ITEMS = 10;
	int ITEM_DRAWN_INDEX = 0;
	ArrayList<AppInfo> mApps;
	public PinterestUI(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.context = context;
	}

	public PinterestUI(Context context,ArrayList<AppInfo> mApps) {
		super(context);
		this.context = context;
		ITEM_DRAWN_INDEX	=	0;
		TOTAL_NUM_ITEMS	=	mApps.size();
		this.mApps		=	mApps;
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);

		SCREEN_WIDTH = metrics.widthPixels;
	
		
//		ViewTreeObserver vto = getViewTreeObserver();
//		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//
//			@SuppressLint("NewApi")
//			@Override
//			public void onGlobalLayout() {
//				ITEM_DRAWN_INDEX++;
//				NextLayout = (LinearLayout) getChildAt(getLayoutIndexToAdd());
//				Log.e("NEXT LAOUT ",""+NEXTLAYOUT);
//				if (ITEM_DRAWN_INDEX >= TOTAL_NUM_ITEMS) {
//
//					ViewTreeObserver obs = getViewTreeObserver();
//
//					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//						obs.removeOnGlobalLayoutListener(this);
//					} else {
//						obs.removeGlobalOnLayoutListener(this);
//					}
//				} else {
//					draw(ITEM_DRAWN_INDEX);
//				}
//			}
//
//		});
	}

	public void createLayout() {

		System.out.println("IM HERE>>>>>>>>>>>" + SCREEN_WIDTH);
		for (int i = 0; i < NUM_COLUMN; i++) {

			LinearLayout mLinearLayout = new LinearLayout(context);
			mLinearLayout.setLayoutParams(new LayoutParams(SCREEN_WIDTH
					/ NUM_COLUMN, LayoutParams.WRAP_CONTENT));

			mLinearLayout.setOrientation(LinearLayout.VERTICAL);
			setOrientation(LinearLayout.HORIZONTAL);
			addView(mLinearLayout);

			System.out.println("IM HERE>>>>>>>>>>> Layout Creation" + i);
		}

		for (int i = 0; i < TOTAL_NUM_ITEMS; i++) {

			draw(i);
		}

	}

	private void draw(int index) {
		if (NextLayout == null) {
			NextLayout = (LinearLayout) getChildAt(0);
		}else{
			NextLayout = (LinearLayout) getChildAt(getLayoutIndexToAdd());
		}
		
		LinearLayout mLinearLayout	=	new LinearLayout(context);
		mLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT
				, LayoutParams.WRAP_CONTENT));
		mLinearLayout.setPadding(20, 20, 20, 20);
		//mLinearLayout.setBackgroundColor(Color.argb(100, 176, 176, 176));
		
		ImageView imageView = new ImageView(context);
		
		
		imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		imageView.setScaleType(ScaleType.FIT_XY);
		AppInfo mAppInfo = mApps.get(index);
		
		imageView.setImageDrawable(mAppInfo.icon);
		mLinearLayout.addView(imageView);
		mLinearLayout.startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_alpha));
		
		NextLayout.addView(mLinearLayout);
	}

	private static int NEXTLAYOUT	=	0;
	private int getLayoutIndexToAdd() {
		
		
		
		
		if(NEXTLAYOUT>=NUM_COLUMN-1){
			NEXTLAYOUT	=	-1;
		}
		NEXTLAYOUT++;
		

		
		return NEXTLAYOUT;
	}

}
