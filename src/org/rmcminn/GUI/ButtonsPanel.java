package org.rmcminn.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.rmcminn.Ultilities.Ultilities;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	JButton openDataButton;
	
	public ButtonsPanel() {
		openDataButton = new JButton("Open Data Log (Closes Bitcoin Logger)");
		
		ActionListener openDataButtonListener = new OpenDataButtonListener();
		openDataButton.addActionListener(openDataButtonListener);
        
        this.add(openDataButton);
	}
	
	class OpenDataButtonListener implements ActionListener {
		@Override
        public void actionPerformed(ActionEvent event) {
			try {
				Runtime.getRuntime().exec("cmd /c start " + Ultilities.Path);//replace with Path
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			} 
        }
	}
}
