package org.rmcminn.GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.rmcminn.Ultilities.*;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel{
	
	
	//labels for recent display
	private JLabel infoTimeStamp;
	private JLabel infoHigh;
	private JLabel infoLow;
	private JLabel infoBid;
	private JLabel infoAsk;
	private JLabel infoVolume;
	private JLabel infoLast;
	private JLabel infoTitle;
	private int dataCount;
	
	//Recorded data
	double[] tickerData = new double[6];
	String timestamp;
	String[] dayTime;
	
	private URL bitCoinTicker;
	
	static Timer mainTimer = new Timer();
	
	//writing to file
	PrintWriter writer;
	FileWriter write;
	
	public InfoPanel(URL bitCoinTicker){
		this.bitCoinTicker = bitCoinTicker;
		this.setLayout(new GridLayout(8,1));
		//get data entries here
		try{
			BufferedReader br = new BufferedReader(new FileReader(Ultilities.Path));
	        while(br.readLine() != null)
	        	dataCount++;
	        br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//set panel background color
		this.setBackground(Color.WHITE);
		this.setBorder(new EtchedBorder());
		
		//refresh class for infopane
		class RefreshInfoPanel extends TimerTask {
			public void run(){
				createTickerInfo();
			}
		}
		
		//task for refresh
		mainTimer.schedule(new RefreshInfoPanel(), 0, 10000); // every 1/6 of a minute. 10000
	}
	
	public void createTickerInfo(){
		this.removeAll();
		
		//infoPanel JLabels
		infoTimeStamp = new JLabel("");
		infoLast = new JLabel("");
		infoHigh = new JLabel("");
		infoLow = new JLabel("");
		infoVolume = new JLabel("");
		infoBid = new JLabel("");
		infoAsk = new JLabel("");
		infoTitle = new JLabel("[Most Recent BitCoin Info] - Number of Data Entries: " + dataCount);
		
		//Get Data from JSON Ticker
		Object obj=JSONValue.parse("[" + Ultilities.getTickerData(bitCoinTicker) + "]");
		JSONArray array=(JSONArray)obj; 
		JSONObject ticker=(JSONObject)array.get(0);
		
		timestamp = (String) ticker.get("timestamp");
		tickerData[0] = Double.parseDouble((String)ticker.get("last"));
		tickerData[1] = Double.parseDouble((String)ticker.get("volume"));
		tickerData[2] = Double.parseDouble((String)ticker.get("high"));
		tickerData[3] = Double.parseDouble((String)ticker.get("low"));
		tickerData[4] = Double.parseDouble((String)ticker.get("bid"));
		tickerData[5] = Double.parseDouble((String)ticker.get("ask"));
		
		dayTime = Ultilities.convertTimestamp(timestamp);
		
		//write to file
		try {
			write = new FileWriter(Ultilities.Path, true);
			writer = new PrintWriter(write);
			writer.println(dayTime[0] + "," + dayTime[1] + "," + tickerData[0] + ","
					+ tickerData[2] + "," + tickerData[3] + "," + tickerData[4] + "," + tickerData[5]); //doesn't record volume
			dataCount++;
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//set labels
		infoTimeStamp.setText("Timestamp: " + dayTime[0] + " " + dayTime[1]);
		infoLast.setText("Last Trade: $" + tickerData[0]);
		infoVolume.setText("Volume(over last 24 hours): " + tickerData[1]);
		infoHigh.setText("High(over last 24 hours): $" + tickerData[2]);
		infoLow.setText("Low(over last 24 hours): $" + tickerData[3]);
		infoBid.setText("Bid(highest buy order): $" + tickerData[4]);
		infoAsk.setText("Ask(lowest sell order): $" + tickerData[5]);
		
		//add to panel
		this.add(infoTitle);
		this.add(infoTimeStamp);
		this.add(infoLast);
		this.add(infoHigh);
		this.add(infoLow);
		this.add(infoVolume);
		this.add(infoBid);
		this.add(infoAsk);
		
		this.updateUI();
	}

}
