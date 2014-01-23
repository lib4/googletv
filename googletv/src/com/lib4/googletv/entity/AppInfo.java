package com.lib4.googletv.entity;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class AppInfo {

	
	public String appname = "";
	public String pname = "";
	public String versionName = "";
	public int versionCode = 0;
	public Drawable icon;

	public void prettyPrint() {
		Log.e("AppInfo", appname + "\t" + pname + "\t" + versionName + "\t"
				+ versionCode);
	}
}
