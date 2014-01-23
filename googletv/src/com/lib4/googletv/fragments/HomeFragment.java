package com.lib4.googletv.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lib4.googletv.R;





public class HomeFragment extends Fragment {
	
	LinearLayout mHomeLayout;
	
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// Inflate the layout for this fragment
	mHomeLayout = (LinearLayout) inflater.inflate(
			R.layout.home_fragment, container, false);
	
	return mHomeLayout;
}

}