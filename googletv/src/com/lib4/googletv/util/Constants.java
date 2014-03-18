package com.lib4.googletv.util;

import java.util.HashMap;

public class Constants {

	public static String USERNAME = "admin";
	public static String PASSWORD = "password";

	public static String HOTEL_SERVCIES_INFO = "Hotel Services";
	public static String NEWS_SPORTS_APPS = "News & Sports";
	public static String MEDIA_RADIO = "Media & Radio";
	public static String ONLINE_SOCIAL = "Online & Social";
	public static String GAMES = "Games";
	public static String APPS_WORLD = "Apps World";
	
	public static final int APP_LIMIT		=	20;
	
	public static final int HOTEL_SERVICES_FLAG = 1;
	public static final int NEWS_SPORTS_FLAG = 2;
	public static final int MEDIA_RADIO_FLAG = 3;
	public static final int ONLINE_SOCIAL_FLAG = 4;
	public static final int GAMES_FLAG = 5;
	public static final int APPS_WORLD_FLAG = 6;

	public static int CUR_TEMP		=	22;
	public static HashMap<Integer, String> CATEGORY_MAP	=	new HashMap<Integer, String>(){
		
		{
				put(HOTEL_SERVICES_FLAG, HOTEL_SERVCIES_INFO);
	            put(NEWS_SPORTS_FLAG, NEWS_SPORTS_APPS);
	            put(MEDIA_RADIO_FLAG, MEDIA_RADIO);
	            put(ONLINE_SOCIAL_FLAG, ONLINE_SOCIAL);
	            put(GAMES_FLAG, GAMES);
	            put(APPS_WORLD_FLAG, APPS_WORLD);

		}
		
	};

}
