package GUImodule;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatLightLaf;

import dataBase.Models.GroupReg;
import dataBase.Models.MemberForGroup;
import logicTools.GroupMemberRegLogic;
import logicTools.GroupRegLogic;


public class GroupMemberRegPage extends JPanel {
	//This is updated from within
	String groupName;
	
	
	//gui construction below
	Panel pick_Group;
	
	Panel purpleSpace ;
	JPanel registerPanel;
	JButton registerButton;
	JLabel fNametxtBox;
	JLabel SNametxtBox;
	JLabel age;
	JLabel Selected_group;
	
	JScrollPane scrollPane = new JScrollPane();
	JList list = new JList();
	JLabel refreshICon = new JLabel("");
	JLabel DisplayGroupName = new JLabel();
	JPanel groupListPanel = new JPanel();
	
	//instances of the models we would use 
	GroupReg instance = new GroupReg();
	GroupRegLogic groupRegLogic = new GroupRegLogic();
	MemberForGroup memberForGroup = new MemberForGroup();
	GroupMemberRegLogic groupMemberRegLogic = new GroupMemberRegLogic();




	
	GroupMemberRegPage(){
		//adding the look and feel 
				try {
				    UIManager.setLookAndFeel( new FlatLightLaf    () );
				} catch( Exception ex ) {
				    System.err.println( "Failed to initialize theme. Using fallback." );
				}
				
				
				
				

				DisplayGroupName = new JLabel();

				
					purpleSpace = new Panel();
					purpleSpace.setLayout(null);
					purpleSpace.setVisible(true);
					purpleSpace.setBackground(new Color(204, 153, 255));
					purpleSpace.setBounds(0, 0, 873, 661);
					
					 				registerPanel =new whiteRoundedPanel2(50 , new Color(153, 51, 255));
									registerPanel.setLayout(null);
									registerPanel.setVisible(true);
									registerPanel.setBackground(new Color(153, 51, 255));
									registerPanel.setBounds(460, 20, 400, 300);
									registerPanel.setOpaque(false);

												//first name
												fNametxtBox = new JLabel();
												fNametxtBox.setForeground(Color.BLACK);
												setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
												fNametxtBox.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255)), "First Name", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
												fNametxtBox.setBackground(new Color(153, 51, 255));
												fNametxtBox.setBounds(56, 20, 262, 40);
												fNametxtBox.setFont(new Font ("Segoe UI Semilight",Font.PLAIN , 12));


												//second name
												SNametxtBox = new JLabel();
												SNametxtBox.setForeground(Color.BLACK);
												SNametxtBox.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255)), "Second Name", TitledBorder.LEFT, TitledBorder.TOP, null,new Color(255, 255, 255)));
												SNametxtBox.setBackground(new Color(153, 51, 255));
												SNametxtBox.setBounds(56, 70, 262, 40);
												SNametxtBox.setFont(new Font ("Segoe UI Semilight",Font.PLAIN , 12));
											
												//Date of birth
												age = new JLabel(  );
												age.setForeground(Color.BLACK);
												age.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255)), "Age", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
												age.setBounds(56, 120, 262, 40);
												age.setFont(new Font ("Segoe UI Semilight",Font.PLAIN , 12));
												age.isDisplayable();

												
												//this is will display the group selected 
												Selected_group = new JLabel();
												Selected_group.setForeground(new Color(240,240,240));
												Selected_group.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Selected Group", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(240, 240, 240)));
												Selected_group.setBackground(new Color(153, 51, 255));
												Selected_group.setBounds(56, 170, 262, 40);
												Selected_group.setText("Select group....");
												Selected_group.isDisplayable();
												Selected_group.setFont(new Font ("Segoe UI Semilight",Font.PLAIN , 12));
												Selected_group.setForeground(new Color(100, 0, 50));

												
										registerButton = new JButton("Register To Group");			
										registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
										registerButton.setFocusable(false);
										registerButton.setBounds(56, 220, 262, 30);
										registerButton.setForeground(getBackground());
										//sending the date to mysql
										registerButton.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(ActionEvent e) {
													if (Selected_group != null )
													{
														//String fname, String sname, int age, String groupId
														
														var memberForGroup = new MemberForGroup(fNametxtBox.getText().toString(),
																SNametxtBox.getText().toString(),
																Integer.parseInt(age.getText()) ,
																groupRegLogic.getGroupID(Selected_group.getText().toString()));
														
//														System.out.println(group);
//														groupRegLogic.submitGroupToDatabase(group);//submitting to the data base
														System.out.println("\n"+memberForGroup);
														groupMemberRegLogic.submitMemberGroupToDatabase(memberForGroup);
														
														//displaying the appreciation note to the user
														String confirmation = String.format("Thank you for joining %s",Selected_group.getText().toString().toUpperCase());
														JOptionPane.showMessageDialog(registerPanel, confirmation);
														//resetting the labels 
														fNametxtBox.setText("");
														SNametxtBox.setText("");
														age.setText("");
														Selected_group.setText("");
														
														//closing this page
														GroupMemberRegPage.this.setVisible(false);
														
														
													}
													else 
													{
														JOptionPane.showMessageDialog(registerPanel, "Ensure All required Fields are present");
													}
													
												}
											}
												
												);
										
									registerPanel.add(Selected_group);	
									registerPanel.add(registerButton);
									registerPanel.add(SNametxtBox);
									registerPanel.add(fNametxtBox);
									registerPanel.add(age);


									groupListPanel = new whiteRoundedPanel2(50 , new Color(186, 85, 211));
									groupListPanel.setBorder(null);
									groupListPanel.setBackground(new Color(204, 153, 255));
									groupListPanel.setBounds(10, 20, 430, 300);
									groupListPanel.setLayout(null);
													scrollPane = new JScrollPane();
													scrollPane.setFont(new Font ("Segoe UI Semilight", Font.BOLD, 18));
													scrollPane.setViewportBorder(new LineBorder(new Color(204, 204, 204)));
													scrollPane.setBounds(63, 68, 262, 165);
																list = new JList();
																list.setBackground(new Color(186, 85, 211));
																scrollPane.setViewportView(list);
																list.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
																list.setVisibleRowCount(5);
																list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
																list.addMouseListener(new MouseListener()
																		{

																			@Override
																			public void mouseClicked(MouseEvent e) {
																				// TODO Auto-generated method stub
																				
																			}

																			@Override
																			public void mousePressed(MouseEvent e) {
																				DisplayGroupName.setText(list.getSelectedValue().toString());
																				DisplayGroupName.setForeground(Color.BLACK);
																				DisplayGroupName.setFont(new Font ("Segoe UI Semilight", Font.PLAIN, 18));
																				
																				Selected_group.setText(list.getSelectedValue().toString());
																				Selected_group.setForeground(Color.BLACK);
																				Selected_group.setFont(new Font ("Segoe UI Semilight", Font.PLAIN, 18));
																			}

																			@Override
																			public void mouseReleased(MouseEvent e) {
																				// TODO Auto-generated method stub
																				
																			}

																			@Override
																			public void mouseEntered(MouseEvent e) {
																				// TODO Auto-generated method stub
																				
																			}

																			@Override
																			public void mouseExited(MouseEvent e) {
																				// TODO Auto-generated method stub
																				
																			}
																	
																		});
													
													refreshICon = new JLabel("");
													refreshICon.setIcon(new ImageIcon(MainFrame.class.getResource("/resourcesPics/Very-Basic-Reload-icon.png")));
													refreshICon.setBounds(29, 78, 24, 39);
													refreshICon.addMouseListener(new MouseAdapter() {
														
														@Override
														public void mouseClicked(MouseEvent e) {
															//get the list of the goups from the data base
															//groupRegLogic.groupInfo
															ArrayList <String> groupNames = new ArrayList<>();
															groupRegLogic = new GroupRegLogic();
															groupNames = groupRegLogic.groupNamesFromSQL;
															
															DefaultListModel list_model = new DefaultListModel();
															DefaultListModel listModel = new DefaultListModel();
															for (int i = 0; i < groupNames.size(); i++)
															{
															    listModel.addElement(groupNames.get(i));
															}
															
															list.setModel((listModel));
			
															}

														
														});
												/**/
														
														DisplayGroupName.setForeground(Color.BLACK);
														DisplayGroupName.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Selected Group", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
														DisplayGroupName.setBackground(new Color(186, 85, 211));
														DisplayGroupName.setBounds(63, 11, 262, 40);
														DisplayGroupName.setText("Select group....");
														DisplayGroupName.setFont(new Font ("Times New Roman",Font.BOLD , 16));
														DisplayGroupName.setForeground(new Color(100, 0, 50));

														
														
															
								groupListPanel.add(DisplayGroupName);
								groupListPanel.add(scrollPane);				
								groupListPanel.add(refreshICon);
												
									
									
						JLabel imageelegant = new JLabel();
						imageelegant.setFont(new Font("Tahoma", Font.BOLD, 12));
						imageelegant.setIcon(new ImageIcon(MemberRegistrationPage.class.getResource("/resourcesPics/purpleLady24.png")));
						imageelegant.setHorizontalAlignment(SwingConstants.CENTER);
						imageelegant.setBounds(4, 140, 500, 700);
						
				
					purpleSpace.add(groupListPanel);
					purpleSpace.add(registerPanel);
					purpleSpace.add(imageelegant);
					
					
				this.add(purpleSpace);
				this.setBounds(0, 0, 873, 662);
				this.setLayout(null);
				this.setVisible(true);	
			}
	
	
			


		}		
	

