package com.lib4.googletv;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.lib4.googletv.fragments.InternetAppsFragment;

public class InternetAppsActivity extends BaseActivity {

	/**
	 * Called when the activity is starting.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragmet_holder);
		loadInternetAppsFragment_Fragment();

	}
	
	
	/**
	 * Load the LiveOrder fragment
	 * 
	 */

	private void loadInternetAppsFragment_Fragment() {

		InternetAppsFragment InternetAppsFragments = new InternetAppsFragment();
		
		InternetAppsFragments.setTitle(getIntent().getStringExtra("title"));

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		// fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit,
		// R.anim.pop_enter, R.anim.pop_exit);
		// Replace whatever is in the fragment_container view with this
		// fragment,
		// and add the transaction to the back stack
		fragmentTransaction.replace(R.id.fragment_holder, InternetAppsFragments);

		// Commit the transaction
		fragmentTransaction.commit();

	}

}
