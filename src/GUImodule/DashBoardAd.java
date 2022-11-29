package GUImodule;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DashBoardAd extends JPanel{
	

	public DashBoardAd() {
		
		Icon imgIcon = new ImageIcon(this.getClass().getResource("/resourcesPics/spongeBoB.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setBounds(0, 0, 500, 500); // for example, you can use your own values
		
		this.add(label);
		this.setBounds(0, 0, 873, 662);
		this.setLayout(null);
		this.setVisible(true);	
	}

}
