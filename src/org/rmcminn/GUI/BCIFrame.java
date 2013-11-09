package org.rmcminn.GUI;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BCIFrame extends JFrame {
	
	private static int FRAME_HEIGHT = 400;
	private static int FRAME_WIDTH = 1000;

	private JLabel infoTimeStamp;
	private JLabel infoHigh;
	private JLabel infoLow;
	private JLabel infoBid;
	private JLabel infoAsk;
	private JLabel infoVolume;
	private JLabel infoLast;
	private JPanel infoPanel;
	private String tickerInfo;
	
	//ticket info
	String timestamp;
	double last;
	double volume;
	double high;
	double low;
	double bid;
	double ask;
	
	private URL bitCoinTicker;
	
	
	public BCIFrame(){
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		try{
			//uses BitStamp.com
			bitCoinTicker = new URL("https://www.bitstamp.net/api/ticker/");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		createInfoPanel();
	}
	/*
	 * creates main panel for now
	 */
	private void createInfoPanel() {
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(7,1));

		infoTimeStamp = new JLabel("");
		infoLast = new JLabel("");
		infoHigh = new JLabel("");
		infoLow = new JLabel("");
		infoVolume = new JLabel("");
		infoBid = new JLabel("");
		infoAsk = new JLabel("");
		
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
		
		Object obj=JSONValue.parse("[" + tickerInfo + "]");
		JSONArray array=(JSONArray)obj; 
		JSONObject ticker=(JSONObject)array.get(0);
		
		timestamp = (String) ticker.get("timestamp");
		last = Double.parseDouble((String)ticker.get("last"));
		volume = Double.parseDouble((String)ticker.get("volume"));
		high = Double.parseDouble((String)ticker.get("high"));
		low = Double.parseDouble((String)ticker.get("low"));
		bid = Double.parseDouble((String)ticker.get("bid"));
		ask = Double.parseDouble((String)ticker.get("ask"));
		
		
		infoTimeStamp.setText("Timestamp: " + timestamp);
		infoLast.setText("Last Trade: $" + last);
		infoHigh.setText("High(over last 24 hours): $" + high);
		infoLow.setText("Low(over last 24 hours): $" + low);
		infoVolume.setText("Volume(over last 24 hours): " + volume);
		infoBid.setText("Bid(highest buy order): $" + bid);
		infoAsk.setText("Ask(lowest sell order): $" + ask);
		
		infoPanel.add(infoTimeStamp);
		infoPanel.add(infoLast);
		infoPanel.add(infoHigh);
		infoPanel.add(infoLow);
		infoPanel.add(infoVolume);
		infoPanel.add(infoBid);
		infoPanel.add(infoAsk);
		
		add(infoPanel);
	}
	
}
