package GUImodule;

import java.awt.*;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class MainFrame extends JFrame {
	
	/*additional ui controls*/
	Boolean hClicked = false; 
	Boolean regClicked = false;
	Boolean groupClicked = false;
	
	/*********/
	JPanel panel = new JPanel();//holds the who navigation bar 
	JPanel panel2 =new JPanel();
	
	JPanel companyMoto = new JPanel();
	JLabel motoLabel = new JLabel("    YOU NEED IT....!");
	JLabel motoLabel2 = new JLabel("      we've got it.");
	JLabel emojiIcon = new JLabel();



	JPanel homeCommandPanel = new JPanel();
	JLabel home_label = new JLabel();
	JLabel lblNewLabel_1 = new JLabel("Home");
	JPanel memberRegistration = new JPanel();
	JLabel registerMemberLable = new JLabel("Member Registration ");
	JLabel regMemberIcon = new JLabel();
	JPanel groupRegPanel = new JPanel();
	JLabel groupRegistration = new JLabel("Group Registration ");
	JLabel goupRegIcon = new JLabel();
	
	//instances of the new panels 
	DashBoardAd page0 = new DashBoardAd();
	MemberRegistrationPage page1= new MemberRegistrationPage();
	GroupRegistrationPage page2 =   new GroupRegistrationPage();
	GroupMemberRegPage page2Extension = new GroupMemberRegPage();




	public MainFrame()  {
		//setting the look and feel 
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize theme. Using fallback." );
		}
		//hiding of the panels not needed
		
		page0.setVisible(true);
		page0.setLayout(null);
		page0.setBounds(0, 0, 700, 700);
		
		page1.setVisible(false);
			page2.setLayout(null);

		page2.setVisible(false);
			page2.setLayout(null);
			page2.setBounds(500, 0, 700, 700);
			
		page2Extension.setVisible(false);
		//regpage.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);


		
				//below is the company motto section i.e top of the navigation bar
				companyMoto.setBackground(new Color(75, 0, 130));
				companyMoto.setBounds(10, 11, 190, 118);
				companyMoto.setLayout(null);
				companyMoto.add(motoLabel);
				companyMoto.add(motoLabel2);
				companyMoto.add(emojiIcon);

							motoLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
							motoLabel.setForeground(new Color(204, 204, 204));
							motoLabel.setBounds(0, 0, 190, 40);
							
							motoLabel2.setFont(new Font("Arial Black", Font.BOLD, 13));
							motoLabel2.setBounds(0, 39, 118, 24);
							motoLabel2.setForeground(new Color(0, 0, 51));
							
							emojiIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/resourcesPics/idiotic-smile-icon.png")));
							emojiIcon.setBounds(104, 39, 86, 79);
							
							
		
		
				//below are the panels that hold the commands within the navigation bar 
				homeCommandPanel.setBounds(0, 140, 210, 60);
				homeCommandPanel.setBackground(new Color(100,14, 200));
				if (groupClicked == false && regClicked == false && hClicked ==false)
				homeCommandPanel.addMouseListener(new MouseAdapter() {
			         public void mouseEntered(MouseEvent evt) {
							homeCommandPanel.setBackground(new Color(80, 20, 100));
	/**/						
			          }
			          public void mouseExited(MouseEvent evt) {
			        	  if (hClicked == false)
							homeCommandPanel.setBackground(new Color(100,14, 200));
			          }});
				SpringLayout sl_homeCommandPanel = new SpringLayout();
				homeCommandPanel.setLayout(sl_homeCommandPanel);
				homeCommandPanel.add(home_label);
				homeCommandPanel.add(lblNewLabel_1);
							home_label.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									homeCommandPanel.setBackground(new Color(80, 20, 100));
					        		groupRegPanel.setBackground(new Color(100,14, 200));
					  				memberRegistration.setBackground(new Color(100,14, 200));


									hClicked = true;
									add_panel(0);

								}
							});
							sl_homeCommandPanel.putConstraint(SpringLayout.NORTH, home_label, 10, SpringLayout.NORTH, homeCommandPanel);
							sl_homeCommandPanel.putConstraint(SpringLayout.WEST, home_label, 10, SpringLayout.WEST, homeCommandPanel);
							sl_homeCommandPanel.putConstraint(SpringLayout.SOUTH, home_label, -10, SpringLayout.SOUTH, homeCommandPanel);
							sl_homeCommandPanel.putConstraint(SpringLayout.EAST, home_label, 54, SpringLayout.WEST, homeCommandPanel);
							home_label.setHorizontalAlignment(SwingConstants.CENTER);
							home_label.setIcon(new ImageIcon(MainFrame.class.getResource("/resourcesPics/Home-icon.png")));
							
							lblNewLabel_1.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {

									homeCommandPanel.setBackground(new Color(80, 20, 100));
					        		groupRegPanel.setBackground(new Color(100,14, 200));
					  				memberRegistration.setBackground(new Color(100,14, 200));


									hClicked = true;
									add_panel(0);

								}
							});
							if (groupClicked == false && regClicked == false && hClicked ==false)
							lblNewLabel_1.addMouseListener(new MouseAdapter() {
						         public void mouseEntered(MouseEvent evt) {
										homeCommandPanel.setBackground(new Color(80, 20, 100));

						          }
						          public void mouseExited(MouseEvent evt) {
						        	  if (hClicked == false)
										homeCommandPanel.setBackground(new Color(100,14, 200));
						          }});
							lblNewLabel_1.setForeground(new Color(204, 204, 204));
							lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
							sl_homeCommandPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 10, SpringLayout.NORTH, homeCommandPanel);
							sl_homeCommandPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 6, SpringLayout.EAST, home_label);
							sl_homeCommandPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, home_label);
							sl_homeCommandPanel.putConstraint(SpringLayout.EAST, lblNewLabel_1, 118, SpringLayout.EAST, home_label);
						


							
				memberRegistration.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						add_panel(1);
						memberRegistration.setBackground(new Color(80, 20, 100));
						homeCommandPanel.setBackground(new Color(100,14, 200));
						groupRegPanel.setBackground(new Color(100,14, 200));
						regClicked = true;
					}
				});
				if (groupClicked == false && regClicked == false && hClicked ==false)
				memberRegistration.addMouseListener(new MouseAdapter() {
			         public void mouseEntered(MouseEvent evt) {
							memberRegistration.setBackground(new Color(80, 20, 100));


			          }
			          public void mouseExited(MouseEvent evt) {
			        	  if (regClicked == false)
			  				memberRegistration.setBackground(new Color(100,14, 200));
			          }});
				memberRegistration.setBackground(new Color(100, 14, 200));
				memberRegistration.setBounds(0, 202, 210, 60);
				SpringLayout sl_panel_2_1 = new SpringLayout();
				memberRegistration.setLayout(sl_panel_2_1);	
				memberRegistration.add(registerMemberLable);
				memberRegistration.add(regMemberIcon);
							registerMemberLable.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									add_panel(1);
									memberRegistration.setBackground(new Color(80, 20, 100));
									homeCommandPanel.setBackground(new Color(100,14, 200));
					        		groupRegPanel.setBackground(new Color(100,14, 200));
									regClicked = true;			
								}

							});
							if (groupClicked == false && regClicked == false && hClicked ==false)
							registerMemberLable.addMouseListener(new MouseAdapter() {
						         public void mouseEntered(MouseEvent evt) {
										memberRegistration.setBackground(new Color(80, 20, 100));
						          }
						          public void mouseExited(MouseEvent evt) {
						        	  if (regClicked == false)
						  				memberRegistration.setBackground(new Color(100,14, 200));
						          }});
							sl_panel_2_1.putConstraint(SpringLayout.NORTH, registerMemberLable, 11, SpringLayout.NORTH, memberRegistration);
							sl_panel_2_1.putConstraint(SpringLayout.WEST, registerMemberLable, 60, SpringLayout.WEST, memberRegistration);
							sl_panel_2_1.putConstraint(SpringLayout.SOUTH, registerMemberLable, 51, SpringLayout.NORTH, memberRegistration);
							sl_panel_2_1.putConstraint(SpringLayout.EAST, registerMemberLable, 200, SpringLayout.WEST, memberRegistration);
							registerMemberLable.setForeground(new Color(204, 204, 204));
							registerMemberLable.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));

							regMemberIcon.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									add_panel(1);
									memberRegistration.setBackground(new Color(80, 20, 100));
									homeCommandPanel.setBackground(new Color(100,14, 200));
					        		groupRegPanel.setBackground(new Color(100,14, 200));
									regClicked = true;
								}
							});
							if (groupClicked == false && regClicked == false && hClicked ==false)
							regMemberIcon.addMouseListener(new MouseAdapter() {
						         public void mouseEntered(MouseEvent evt) {
										memberRegistration.setBackground(new Color(80, 20, 100));

						          }
						          public void mouseExited(MouseEvent evt) {
						        	  if (regClicked == false)
						  				memberRegistration.setBackground(new Color(100,14, 200));
						          }});
							sl_panel_2_1.putConstraint(SpringLayout.NORTH, regMemberIcon, 11, SpringLayout.NORTH, memberRegistration);
							sl_panel_2_1.putConstraint(SpringLayout.WEST, regMemberIcon, 10, SpringLayout.WEST, memberRegistration);
							sl_panel_2_1.putConstraint(SpringLayout.SOUTH, regMemberIcon, 51, SpringLayout.NORTH, memberRegistration);
							sl_panel_2_1.putConstraint(SpringLayout.EAST, regMemberIcon, 54, SpringLayout.WEST, memberRegistration);
							regMemberIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/resourcesPics/Apps-user-info-icon.png")));
							regMemberIcon.setHorizontalAlignment(SwingConstants.CENTER);
					
							
							
							
							
				groupRegPanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						add_panel(2);
						groupRegPanel.setBackground(new Color(80, 20, 100));
						homeCommandPanel.setBackground(new Color(100,14, 200));
		  				memberRegistration.setBackground(new Color(100,14, 200));
						groupClicked = true;	
					}
				});
				if (groupClicked == false && regClicked == false && hClicked ==false)
				groupRegPanel.addMouseListener(new MouseAdapter() {
			         public void mouseEntered(MouseEvent evt) {
			        	 groupRegPanel.setBackground(new Color(80, 20, 100));


			          }
			          public void mouseExited(MouseEvent evt) {
			        	  if (groupClicked == false)
			        		  groupRegPanel.setBackground(new Color(100,14, 200));
			          }});
				groupRegPanel.setBackground(new Color(100, 14, 200));
				groupRegPanel.setBounds(0, 264, 210, 60);
				groupRegPanel.setLayout(null);
				groupRegPanel.add(groupRegistration);
				groupRegPanel.add(goupRegIcon);

							groupRegistration.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									add_panel(2);
									groupRegPanel.setBackground(new Color(80, 20, 100));
									homeCommandPanel.setBackground(new Color(100,14, 200));
					  				memberRegistration.setBackground(new Color(100,14, 200));
									groupClicked = true;


									}
							});
							if (groupClicked == false && regClicked == false && hClicked ==false)
							groupRegistration.addMouseListener(new MouseAdapter() {
						         public void mouseEntered(MouseEvent evt) {
						        	 groupRegPanel.setBackground(new Color(80, 20, 100));

						          }
						          public void mouseExited(MouseEvent evt) {
						        	  if (groupClicked == false)
						        		  groupRegPanel.setBackground(new Color(100,14, 200));
						          }});
							groupRegistration.setForeground(new Color(204, 204, 204));
							groupRegistration.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
							groupRegistration.setBounds(60, 11, 140, 40);
							
							goupRegIcon.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									add_panel(2);
									groupRegPanel.setBackground(new Color(80, 20, 100));
									homeCommandPanel.setBackground(new Color(100,14, 200));
					  				memberRegistration.setBackground(new Color(100,14, 200));
									groupClicked = true;


									
								}
							});
							if (groupClicked == false && regClicked == false && hClicked ==false)
							goupRegIcon.addMouseListener(new MouseAdapter() {
						         public void mouseEntered(MouseEvent evt) {
						        	 groupRegPanel.setBackground(new Color(80, 20, 100));


						          }
						          public void mouseExited(MouseEvent evt) {
						        	  if (groupClicked == false)
						        		  groupRegPanel.setBackground(new Color(100,14, 200));
						          }});
							goupRegIcon.setIcon(new ImageIcon(MainFrame.class.getResource("/resourcesPics/Healthcare-Groups-icon.png")));
							goupRegIcon.setHorizontalAlignment(SwingConstants.CENTER);
							goupRegIcon.setBounds(10, 11, 44, 40);
							
					
								
			panel.add(companyMoto);
			panel.add(groupRegPanel);
			panel.add(memberRegistration);	
			panel.add(homeCommandPanel);
			panel.setLayout(null);
			panel.setBounds(0, 0, 210, 661);
			panel.setBackground(new Color(75, 0, 130));
			
			panel2.add(page0);
			panel2.add(page2Extension);
			panel2.add(page1);
			panel2.add(page2);
			panel2.setLayout(null);
			panel2.setBounds(210, 0, 874, 661);
			panel2.setBackground(Color.white/*new Color(153, 51, 255)*/);
			
		this.add(panel2);
		this.add(panel);
		this.setLayout(null);
		this.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		this.setResizable(false);
		this.setBounds(100, 0, 1100, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}	
	

	private void add_panel(int page) {
		
		if (page == 1)
		{
			
			page1.setBounds(0,0, 900 ,700);
			page1.setVisible(true);
			
			//closing the other pages 
			page0.setVisible(false);
			page2.setVisible(false);

			

			
		}
		if (page == 2)
		{
			page2.setBounds(0,0, 900 ,700);
			page2.setVisible(true);
			
			//closing the other pages
			page0.setVisible(false);
			page1.setVisible(false);


		}
		
		if (page == 0)
		{
			
			page0.setBounds(0,0, 900 ,700);
			page0.setVisible(true);
			page1.setVisible(false);
			page2.setVisible(false);

			
		}
		
	}

}
