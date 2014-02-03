package com.lib4.googletv;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageHolderView extends FrameLayout {

	Animation animationSlideInLeft, animationSlideOutLeft;
	int[] imageIds;
	Context context;
	ImageHolderView mHolderView;
	Handler mHandler = new Handler();
	int CURRENT_IMGAE_INDEX = -1;
	ImageView imageView1, imageView2;

	public ImageHolderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		mHolderView = this;
		InitializeAnimation(context);

	}

	public ImageHolderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setImages(int[] images) {
		imageIds = images;

		if (imageView1 == null) {
			imageView1 = new ImageView(context);

			imageView1.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			imageView1.setScaleType(ScaleType.FIT_XY);
			addView(imageView1);
		}

		if (imageView2 == null) {
			imageView2 = new ImageView(context);

			imageView2.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			imageView2.setScaleType(ScaleType.FIT_XY);
			addView(imageView2);
		}

		int nextIndex = getNextIndex();
		imageView1.setImageResource(imageIds[nextIndex]);
		imageView2.setImageResource(imageIds[nextIndex]);
		startAnimation(this.context);
	}

	private void InitializeAnimation(Context context) {
		animationSlideInLeft = AnimationUtils.loadAnimation(context,
				R.anim.right_left);
		animationSlideOutLeft = AnimationUtils.loadAnimation(context,
				R.anim.left_hide);
		animationSlideInLeft.setDuration(1000);
		animationSlideInLeft.setFillAfter(true);
		animationSlideInLeft.setFillEnabled(true);
		animationSlideOutLeft.setDuration(1000);
		animationSlideOutLeft.setFillAfter(true);
		animationSlideOutLeft.setFillEnabled(true);
		animationSlideInLeft.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {

			}
		});

		animationSlideOutLeft.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub

			}
		});

	}

	private long count = 0;

	private void switchImages() {

		count++;
		try{
		if (count % 2 != 0) {
			imageView1.startAnimation(animationSlideOutLeft);
			imageView2.setImageResource(imageIds[getNextIndex()]);
			imageView2.startAnimation(animationSlideInLeft);
		} else {
			imageView1.setImageResource(imageIds[getNextIndex()]);
			imageView1.startAnimation(animationSlideInLeft);
			imageView2.startAnimation(animationSlideOutLeft);
		}
		}catch(OutOfMemoryError e){
			e.printStackTrace();
		}

	}

	private void startAnimation(Context context) {

		new Timer().schedule(new UpdateTimeTask(), 2000, 3000);
	}

	class UpdateTimeTask extends TimerTask {
		public void run() {
			mHandler.post(new Runnable() {

				@Override
				public void run() {

					switchImages();
				}
			});
		}
	}

	private int getNextIndex() {
		CURRENT_IMGAE_INDEX++;
		if (CURRENT_IMGAE_INDEX >= imageIds.length) {
			CURRENT_IMGAE_INDEX = 0;
		}
		return CURRENT_IMGAE_INDEX;
	}
}
