package org.rmcminn.GUI;

import java.awt.GridLayout;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BCIFrame extends JFrame {
	
	private static int FRAME_HEIGHT = 400;
	private static int FRAME_WIDTH = 800;
	
	private URL bitCoinTicker;
	
	private JPanel infoPanel;
	private JPanel buttonPanel;
	
	
	public BCIFrame(){
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setLayout(new GridLayout(1,2));
		
		//Initial setup
		try{
			//uses BitStamp.com
			bitCoinTicker = new URL("https://www.bitstamp.net/api/ticker/");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		infoPanel = new InfoPanel(bitCoinTicker);
		buttonPanel = new ButtonsPanel();
		add(infoPanel);
		add(buttonPanel);
	}
}
