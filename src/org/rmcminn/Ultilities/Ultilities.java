package org.rmcminn.Ultilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class Ultilities {
	//Static world variables
	public static String Path = "BitCoinData.csv";
	/*
	 * Retrieves Ticker data from URL
	 */
	public static String getTickerData(URL bitCoinTicker) {
		String tickerInfo = null;
		
		try{
			URLConnection bctConnection = bitCoinTicker.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(bctConnection.getInputStream()));
			String tickerInfoRead;
			while((tickerInfoRead = in.readLine()) != null){
				tickerInfo = tickerInfoRead;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return tickerInfo;
		
	}
	/*
	 * @param timestamp "unix epoch timestamp"
	 * @return dayTime[] "a array of day and time"
	 * converts unix epoch timestamp to an array of day then time
	 */
	public static String[] convertTimestamp(String timestamp){
		Date ts= new Date(Long.parseLong(timestamp)*1000);
		String day = ts.toString().substring(4, 10);
		String time = ts.toString().substring(11, 19);
		String dayTime[] = {day, time};
		return dayTime;
	}

}
