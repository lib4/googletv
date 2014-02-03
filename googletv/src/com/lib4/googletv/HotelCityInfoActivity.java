package com.lib4.googletv;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.lib4.googletv.fragments.SettingsFragment;
import com.lib4.googletv.fragments.Hotel_City_Info_Fragment;

public class HotelCityInfoActivity extends BaseActivity {

	/**
	 * Called when the activity is starting.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragmet_holder);
		loadHotel_City_Info_Fragment();

	}
	
	
	/**
	 * Load the LiveOrder fragment
	 * 
	 */

	private void loadHotel_City_Info_Fragment() {

		Hotel_City_Info_Fragment mHotel_City_Info_Fragment = new Hotel_City_Info_Fragment();

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		// fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit,
		// R.anim.pop_enter, R.anim.pop_exit);
		// Replace whatever is in the fragment_container view with this
		// fragment,
		// and add the transaction to the back stack
		fragmentTransaction.replace(R.id.fragment_holder, mHotel_City_Info_Fragment);

		// Commit the transaction
		fragmentTransaction.commit();

	}

}
