package com.lib4.googletv.util;

import java.util.HashMap;

public class Constants {

	public static String USERNAME = "admin";
	public static String PASSWORD = "password";

	public static String HOTEL_CITY_INFO = "hotel_city_info";
	public static String INTERNET_APPS = "internet_apps";
	public static String TV_ONLINE_RADIO = "tv_online_radio";
	public static String NEWS_SPORTS = "news_sports";
	public static String GAMES = "games";
	public static String HOTEL_INFO = "hotel_info";
	
	public static final int APP_LIMIT		=	20;
	
	public static final int HOTEL_CITY_INFO_FLAG = 1;
	public static final int INTERNET_APPS_FLAG = 2;
	public static final int TV_ONLINE_RADIO_FLAG = 3;
	public static final int NEWS_SPORTS_FLAG = 4;
	public static final int GAMES_FLAG = 5;
	public static final int HOTEL_INFO_FLAG = 6;
	
	public static HashMap<Integer, String> CATEGORY_MAP	=	new HashMap<Integer, String>(){
		
		{
				put(HOTEL_CITY_INFO_FLAG, HOTEL_CITY_INFO);
	            put(INTERNET_APPS_FLAG, INTERNET_APPS);
	            put(TV_ONLINE_RADIO_FLAG, TV_ONLINE_RADIO);
	            put(NEWS_SPORTS_FLAG, NEWS_SPORTS);
	            put(GAMES_FLAG, GAMES);
	            put(HOTEL_INFO_FLAG, HOTEL_INFO);

		}
		
	};

}
