package com.lib4.googletv;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	ImageHolderView mHolderView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_frame);
		mHolderView	=	(ImageHolderView) findViewById(R.id.parentholder);
		mHolderView.setImages(new int[]{R.drawable.first,R.drawable.second,
				R.drawable.third,R.drawable.fourth,R.drawable.fifth});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
