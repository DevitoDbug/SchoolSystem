package GUImodule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.formdev.flatlaf.FlatLightLaf;

import dataBase.Models.GroupReg;
import dataBase.Models.Member;
import logicTools.GroupRegLogic;

public class GroupRegistrationPage extends JPanel implements ActionListener{
	JPanel inGroupRegistration_panel = new JPanel();
	JLabel goupName_label = new JLabel("Group Name");
	JTextField groupName_txtField = new JTextField();
	JLabel lblNumberOfMembers = new JLabel("Number of members");
	JTextField numberOfMembers_txtF = new JTextField();
	JLabel lblRegistrationFee = new JLabel("Registration Fee");
	JTextField textField = new JTextField();
	JButton createGroup_btn = new JButton("Create");
	
	GroupRegLogic groupRegLogic = new GroupRegLogic();



	
	
	GroupRegistrationPage()
	{
		//setting the look and feel 
				try {
				    UIManager.setLookAndFeel( new FlatLightLaf() );
				} catch( Exception ex ) {
				    System.err.println( "Failed to initialize theme. Using fallback." );
				}
			
				
				//adding the image to the panel 
				Icon imgIcon = new ImageIcon(this.getClass().getResource("/resourcesPics/whiteBackSponge.jpg"));
				JLabel label = new JLabel(imgIcon);
				label.setBounds(650, 20, 200, 250); // for example, you can use your own values
			
				
		inGroupRegistration_panel = new RoundedPanel1(50 , new Color(216, 191, 216));
		inGroupRegistration_panel.setBounds(50, 30, 550, 250);
		inGroupRegistration_panel.setBackground(Color.WHITE);
		inGroupRegistration_panel.setLayout(null);
		
					goupName_label = new JLabel("Group Name");
					goupName_label.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
					goupName_label.setBounds(44, 48, 122, 27);
							groupName_txtField = new JTextField();
							groupName_txtField.setBounds(170, 48, 319, 27);

					lblNumberOfMembers = new JLabel("Number of members");	
					lblNumberOfMembers.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
					lblNumberOfMembers.setBounds(44, 86, 122, 27);
							numberOfMembers_txtF = new JTextField();
							numberOfMembers_txtF.setColumns(10);
							numberOfMembers_txtF.setBounds(170, 86, 319, 27);
							
					lblRegistrationFee = new JLabel("Registration Fee");
					lblRegistrationFee.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
					lblRegistrationFee.setBounds(44, 125, 122, 27);
							textField = new JTextField();
							textField.setText("1000");
							textField.setForeground(Color.gray);
							textField.setBounds(170, 125, 319, 27);

					createGroup_btn = new JButton("Set up");
					createGroup_btn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
					createGroup_btn.setBounds(170, 184, 89, 25);
					createGroup_btn.setFocusable(false);
					createGroup_btn.setBackground(Color.WHITE);
					createGroup_btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(groupName_txtField.getText()== null && numberOfMembers_txtF.getText() == null )
									{
										JOptionPane.showMessageDialog(inGroupRegistration_panel, "Enter the required fields");
									}
							
							else 
							{
								if (Integer.parseInt(numberOfMembers_txtF.getText())<2 )
								{
									JOptionPane.showMessageDialog(null, "You cannot have a group with less than 2 members ");

								}else {

										if (Integer.parseInt(textField.getText()) == 1000)
												{
													int a=JOptionPane.showConfirmDialog(null,"Confirm creation of "+groupName_txtField.getText()+" group");  
													if(a==JOptionPane.YES_OPTION){// 
														var group = new GroupReg(
															groupName_txtField.getText(),//Group name 
															Integer.parseInt(textField.getText()),//registration fee
															Integer.parseInt(numberOfMembers_txtF.getText())//no of members															//no of members 
															);
														System.out.println(group);
														groupRegLogic.submitGroupToDatabase(group);//submitting to the data base
														
														groupName_txtField.setText("");
														numberOfMembers_txtF.setText("");
														textField.setText("");
														numberOfMembers_txtF.setText("");	
													}
													if (a == JOptionPane.NO_OPTION)
													{
														groupName_txtField.setText("");
													}
													if (a == JOptionPane.CANCEL_OPTION)
													{
														groupName_txtField.setText("");
														numberOfMembers_txtF.setText("");
														textField.setText("");
														numberOfMembers_txtF.setText("");	
														//add here option to return to main page
													}
												}
										else
										{
											JOptionPane.showMessageDialog(inGroupRegistration_panel, "The registration fee is strictly KSH 1000 ");
										}
									}
							}
							
							
							
						}
					});
					
		
		inGroupRegistration_panel.add(createGroup_btn);			
		inGroupRegistration_panel.add(textField);													
		inGroupRegistration_panel.add(lblRegistrationFee);			
		inGroupRegistration_panel.add(lblNumberOfMembers);			
		inGroupRegistration_panel.add(numberOfMembers_txtF);			
		inGroupRegistration_panel.add(groupName_txtField);			
		inGroupRegistration_panel.add(goupName_label);
		
		this.add(label);
		this.add(inGroupRegistration_panel);
		this.setBounds(0, 0, 873, 300);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setVisible(true);	
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}



