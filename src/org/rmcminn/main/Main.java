package org.rmcminn.main;

import javax.swing.JFrame;
import org.rmcminn.GUI.BCIFrame;

public class Main {
	public static void main(String[] args){
		JFrame bciFrame = new BCIFrame();
		
		bciFrame.setTitle("Bitcoin Investor");
		bciFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bciFrame.setLocationRelativeTo(null);
		bciFrame.setResizable(false);
		bciFrame.setVisible(true);

	}
}
