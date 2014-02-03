package com.lib4.googletv.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lib4.googletv.R;

public class Hotel_City_Info_Fragment extends BaseFragment {

	LinearLayout mLinearLayout;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mLinearLayout = (LinearLayout) inflater.inflate(R.layout.hotel_city_info_fragment,
				container, false);

		return mLinearLayout;
	}

}