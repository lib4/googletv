package com.lib4.googletv.network;



public interface HTTPResponseListener{

	public void onSuccess();
	public void onFailure(int failureCode,String message);
	
}
