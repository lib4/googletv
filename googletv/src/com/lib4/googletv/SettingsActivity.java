package com.lib4.googletv;

import com.lib4.googletv.fragments.SettingsFragment;
import com.lib4.googletv.fragments.SignInFragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class SettingsActivity extends BaseActivity {

	/**
	 * Called when the activity is starting.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragmet_holder);
		loadSettingsFragment();

	}
	
	
	/**
	 * Load the LiveOrder fragment
	 * 
	 */

	private void loadSettingsFragment() {

		SettingsFragment mSettingsFragment = new SettingsFragment();

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		// fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit,
		// R.anim.pop_enter, R.anim.pop_exit);
		// Replace whatever is in the fragment_container view with this
		// fragment,
		// and add the transaction to the back stack
		fragmentTransaction.replace(R.id.fragment_holder, mSettingsFragment);

		// Commit the transaction
		fragmentTransaction.commit();

	}

}
