package com.lib4.googletv.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;




public class Connection {

	public InputStream responseStream;
	public int responseCode;

	public String connect(String url, String requestBody, String requestType)
			throws IOException {

		URL obj = new URL(url);
		// Trusting all the Https server and certificate. This is insecure
		// should be avoided.
		//trustAllHosts();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post body : " + requestBody);

		con.setRequestMethod("GET");
		// con.setRequestProperty("User-Agent", USER_AGENT);
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		//con.setRequestProperty("Content-Type", "application/json");


		responseCode = con.getResponseCode();

		System.out.println("Response Code : " + responseCode);
		
		  BufferedReader in = new BufferedReader( new
		  InputStreamReader(con.getInputStream())); String inputLine;
		  StringBuffer response = new StringBuffer();
		  
		  while ((inputLine = in.readLine()) != null) {
		  response.append(inputLine); } in.close();
		  
		  	System.out.println(response.toString());
		

		  return response.toString();
	}

	public static void trustAllHosts() {

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
