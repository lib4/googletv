package com.lib4.googletv.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;

import com.lib4.googletv.entity.AppInfo;

public class PInfo {

	Context mContext;

	public PInfo(Context mContext) {

		this.mContext = mContext;
		
	}

	public ArrayList<AppInfo> getInstalledAppInfo() {
			return getPackages() ;
	}

	private ArrayList<AppInfo> getPackages() {
		ArrayList<AppInfo> apps = getInstalledApps(false); /*
															 * false = no system
															 * packages
															 */
		final int max = apps.size();
		for (int i = 0; i < max; i++) {
			AppInfo mAppInfo = apps.get(i);
			mAppInfo.prettyPrint();
		}
		return apps;
	}

	private ArrayList<AppInfo> getInstalledApps(boolean getSysPackages) {
		ArrayList<AppInfo> res = new ArrayList<AppInfo>();
		List<PackageInfo> packs = mContext.getPackageManager()
				.getInstalledPackages(0);
		for (int i = 0; i < packs.size(); i++) {
			PackageInfo p = packs.get(i);
			if ((!getSysPackages) && (p.versionName == null)) {
				continue;
			}
			AppInfo newInfo = new AppInfo();
			newInfo.appname = p.applicationInfo.loadLabel(
					mContext.getPackageManager()).toString();
			newInfo.pname = p.packageName;
			newInfo.versionName = p.versionName;
			newInfo.versionCode = p.versionCode;
			newInfo.icon = p.applicationInfo.loadIcon(mContext
					.getPackageManager());
			res.add(newInfo);
		}
		return res;
	}

	

}