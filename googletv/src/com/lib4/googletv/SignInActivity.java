package com.lib4.googletv;


import com.lib4.googletv.fragments.SignInFragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class SignInActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragmet_holder);
		loadSignInFragment();
	}

	/**
	 * Load the LiveOrder fragment
	 * 
	 */

	private void loadSignInFragment() {

		SignInFragment mSignInFragment = new SignInFragment();

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		// fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit,
		// R.anim.pop_enter, R.anim.pop_exit);
		// Replace whatever is in the fragment_container view with this
		// fragment,
		// and add the transaction to the back stack
		fragmentTransaction.replace(R.id.fragment_holder, mSignInFragment);

		// Commit the transaction
		fragmentTransaction.commit();

	}
}
