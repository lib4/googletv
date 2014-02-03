package com.lib4.googletv.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.lib4.googletv.R;

public class WebViewFragment extends BaseFragment {

	LinearLayout mLinearLayout;
	static ProgressDialog mDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mLinearLayout = (LinearLayout) inflater.inflate(R.layout.web_view_fragment,
				container, false);
		
	
		
		WebView mWebView	=	(WebView) mLinearLayout.findViewById(R.id.webview);
		
		mWebView.setWebViewClient(new WebViewClient(){
			
			
			@Override
			
			 public void onPageStarted(android.webkit.WebView view, java.lang.String url, android.graphics.Bitmap favicon){
				mDialog = new ProgressDialog(getActivity());
				mDialog.setMessage("Loading...");
				mDialog.setCancelable(true);
				mDialog.show();
			}
			   @Override
			    public void onPageFinished(WebView view, String url) {
				   if (mDialog != null && mDialog.isShowing())
						mDialog.dismiss();
			    }
			
		});
		//mWebView.getSettings().setJavaScriptEnabled(true);
		//mWebView.loadUrl("http://tablet.sportingnews.com/2014-02-03/sn-defense-rules-the-day.html");
		
		mWebView.loadUrl("http://www.foxsports.com.au/mobile");
		
		return mLinearLayout;
	}

}