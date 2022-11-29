package GUImodule;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import dataBase.Models.Member;
import dataBase.Models.MemberForGroup;
import logicTools.GroupMemberRegLogic;
import logicTools.MemberRegistrationLogic;

import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JMenuBar;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ScrollPaneConstants;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;

public class IndividulaMembSharesPage extends JFrame  implements ActionListener{

	
	JPanel memberListPanel;
	JPanel selectMembPanel ;
	JScrollPane scrollPane;
	JScrollPane scrollPane2 ;
	JPanel panel ;
	JPanel panel2 ;
	JLabel nameINlist;
	JLabel lblNewLabel_2;
	JLabel infoINlist;


	
	private JPanel contentPane;
	
	JPopupMenu popupmenu = new JPopupMenu();   
    JMenuItem gMembers = new JMenuItem("Group Members");  
    JMenuItem inMembers = new JMenuItem("Individual Members ");  
    JMenuItem home = new JMenuItem("Home ");  
    
    //these are the dynamically added date to the panels 
    String name  = ".... ";
    String group ;
    
    //
    

	/**
	 * Launch the application.
	 * 
	 * 
	 */
    
    //instances of the classes 
    MemberRegistrationLogic memberRegistrationLogic  = new MemberRegistrationLogic ();
    GroupMemberRegLogic  groupMemberRegLogic = new GroupMemberRegLogic ();
    //
	ArrayList <Member> memberFromSql = new ArrayList <>();
	ArrayList <MemberForGroup> groupMemberFromsql = new ArrayList <>();

    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndividulaMembSharesPage frame = new IndividulaMembSharesPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IndividulaMembSharesPage() {
		//adding the look and feel 
				try {
				    UIManager.setLookAndFeel( new FlatLightLaf    () );
				} catch( Exception ex ) {
				    System.err.println( "Failed to initialize theme. Using fallback." );
				}
		JPanel displayPanel = new RoundedPanel3(50 , new Color(248, 248, 255));
		displayPanel.setBackground(Color.WHITE);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		

		
		selectMembPanel = new RoundedPanel3(20 , new Color(240, 240, 240));
		sl_contentPane.putConstraint(SpringLayout.NORTH, selectMembPanel, 35, SpringLayout.NORTH, contentPane);
		selectMembPanel.setBackground(new Color(221, 160, 221));
		sl_contentPane.putConstraint(SpringLayout.WEST, selectMembPanel, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, selectMembPanel, 250, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, selectMembPanel, 0, SpringLayout.SOUTH, contentPane);
		contentPane.add(selectMembPanel);
		selectMembPanel.setLayout(new BorderLayout());
		
								memberListPanel = new RoundedPanel3(20 , new Color(240, 240, 240));
								memberListPanel.setBackground(Color.WHITE);
								memberListPanel.setSize(1, 690);
								GridBagLayout gbl_memberListPanel = new GridBagLayout();
								gbl_memberListPanel.columnWidths = new int[]{0};
								gbl_memberListPanel.rowHeights = new int[]{0};
								gbl_memberListPanel.columnWeights = new double[]{Double.MIN_VALUE};
								gbl_memberListPanel.rowWeights = new double[]{Double.MIN_VALUE};
								memberListPanel.setLayout(gbl_memberListPanel);
								sl_contentPane.putConstraint(SpringLayout.EAST, displayPanel, 0, SpringLayout.EAST, contentPane);
								contentPane.add(displayPanel);

							    GridBagConstraints gbc = new GridBagConstraints();
							    
							 
							    memberFromSql = new ArrayList <>();
							    fromsql();//gets the date from the sql
							    
							    for (int i = 0; i <memberFromSql.size(); i++) {
							         int top =3, left = 3, bottom = 3, right = 3;
							    	 Insets k = new Insets(top, left, bottom, right);
							    	 //new Color(179, 255, 255)
							    	panel = new RoundedPanel3(20 , new Color(194, 239, 239));
									panel.setBounds(28, 11, 346, 80);
									panel.setLayout(new GridLayout(2, 3, 20, 20));

											nameINlist = new JLabel();
											nameINlist.setText(memberFromSql.get(i).getFtName_sql());
											nameINlist.setHorizontalAlignment(SwingConstants.CENTER);
											
											lblNewLabel_2 = new JLabel();
											lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
											lblNewLabel_2.setIcon(new ImageIcon(MainFrame.class.getResource("/resourcesPics/personL.png")));
											String id  = memberFromSql.get(i).getMemberID_sql() ;
											String fname = memberFromSql.get(i).getFtName_sql();
											
										    gbc.gridwidth = 10;
											infoINlist = new JLabel("<html>Group Member"+"<br>ID: " + id + "<br>Name: " + fname +" </html>");
											infoINlist.setFont(new Font("Segoe UI Semibold", Font.BOLD,10));
											infoINlist.setHorizontalAlignment(SwingConstants.CENTER);
											panel.add(lblNewLabel_2);
											panel.add(infoINlist);
											panel.add(nameINlist);
											
							        gbc.insets = k;
							        gbc.gridx = 0;
							        gbc.gridy = i;
							        
							        memberListPanel.add(panel, gbc);
							    }
							    scrollPane = new JScrollPane(memberListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
								scrollPane.setBorder(null);
								scrollPane.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) { 
										
						            }    
							          });
								
								selectMembPanel.add(scrollPane, BorderLayout.NORTH);
								
								
							    
		sl_contentPane.putConstraint(SpringLayout.NORTH, displayPanel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, displayPanel, 3, SpringLayout.EAST, selectMembPanel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, displayPanel, 0, SpringLayout.SOUTH, selectMembPanel);
		displayPanel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(IndividulaMembSharesPage.class.getResource("/resourcesPics/folder-users-icon (1).png")));
		lblNewLabel_4.setBounds(505, 1, 221, 279);
		displayPanel.add(lblNewLabel_4);
		
		JPanel introPanel = new RoundedPanel3(40, Color.cyan);
		introPanel.setBackground(new Color (248, 248, 255));
		introPanel.setBounds(10, 94, 801, 186);
		displayPanel.add(introPanel);
		introPanel.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Hi, "+ name);
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Serif", Font.BOLD, 45));
		lblNewLabel_5.setBounds(39, 44, 420, 77);
		introPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Want to check your contribution history?..");
		lblNewLabel_6.setForeground(new Color(105, 105, 105));
		lblNewLabel_6.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_6.setBounds(39, 109, 337, 21);
		introPanel.add(lblNewLabel_6);
		
		JPanel ContributePanel = new RoundedPanel3(50, Color.BLACK);
		ContributePanel.setForeground(Color.WHITE);
		ContributePanel.setBackground(new Color (248, 248, 255));
		ContributePanel.setBounds(10, 308, 277, 259);
		displayPanel.add(ContributePanel);
		SpringLayout sl_ContributePanel = new SpringLayout();
		ContributePanel.setLayout(sl_ContributePanel);
		
		JLabel lblNewLabel_7 = new JLabel("Make Contribution");
		sl_ContributePanel.putConstraint(SpringLayout.EAST, lblNewLabel_7, 277, SpringLayout.WEST, ContributePanel);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_7.setForeground(Color.WHITE);
		sl_ContributePanel.putConstraint(SpringLayout.NORTH, lblNewLabel_7, 10, SpringLayout.NORTH, ContributePanel);
		sl_ContributePanel.putConstraint(SpringLayout.WEST, lblNewLabel_7, 10, SpringLayout.WEST, ContributePanel);
		sl_ContributePanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_7, 60, SpringLayout.NORTH, ContributePanel);
		ContributePanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Months");
		sl_ContributePanel.putConstraint(SpringLayout.NORTH, lblNewLabel_8, 6, SpringLayout.SOUTH, lblNewLabel_7);
		sl_ContributePanel.putConstraint(SpringLayout.WEST, lblNewLabel_8, 10, SpringLayout.WEST, ContributePanel);
		sl_ContributePanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_8, 32, SpringLayout.SOUTH, lblNewLabel_7);
		sl_ContributePanel.putConstraint(SpringLayout.EAST, lblNewLabel_8, 122, SpringLayout.WEST, ContributePanel);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_8.setIcon(new ImageIcon(IndividulaMembSharesPage.class.getResource("/resourcesPics/dropdown.jpg")));
		lblNewLabel_8.setForeground(Color.WHITE);
		ContributePanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Amount");
		sl_ContributePanel.putConstraint(SpringLayout.NORTH, lblNewLabel_9, 31, SpringLayout.SOUTH, lblNewLabel_8);
		sl_ContributePanel.putConstraint(SpringLayout.WEST, lblNewLabel_9, 10, SpringLayout.WEST, ContributePanel);
		sl_ContributePanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_9, -110, SpringLayout.SOUTH, ContributePanel);
		sl_ContributePanel.putConstraint(SpringLayout.EAST, lblNewLabel_9, -51, SpringLayout.EAST, lblNewLabel_8);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_9.setForeground(Color.WHITE);
		ContributePanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_7_1 = new JLabel("30000");
		sl_ContributePanel.putConstraint(SpringLayout.EAST, lblNewLabel_7_1, -53, SpringLayout.EAST, ContributePanel);
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		ContributePanel.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_10 = new JLabel("KSH");
		sl_ContributePanel.putConstraint(SpringLayout.NORTH, lblNewLabel_7_1, -17, SpringLayout.NORTH, lblNewLabel_10);
		sl_ContributePanel.putConstraint(SpringLayout.WEST, lblNewLabel_7_1, 6, SpringLayout.EAST, lblNewLabel_10);
		sl_ContributePanel.putConstraint(SpringLayout.NORTH, lblNewLabel_10, 53, SpringLayout.SOUTH, lblNewLabel_8);
		sl_ContributePanel.putConstraint(SpringLayout.WEST, lblNewLabel_10, 6, SpringLayout.EAST, lblNewLabel_9);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_10.setForeground(Color.WHITE);
		ContributePanel.add(lblNewLabel_10);
		
		JPanel panel = new RoundedPanel3(30,Color.WHITE);
		panel.setBackground(Color.BLACK);
		sl_ContributePanel.putConstraint(SpringLayout.NORTH, panel, 18, SpringLayout.SOUTH, lblNewLabel_7_1);
		sl_ContributePanel.putConstraint(SpringLayout.WEST, panel, 36, SpringLayout.WEST, ContributePanel);
		sl_ContributePanel.putConstraint(SpringLayout.SOUTH, panel, 67, SpringLayout.SOUTH, lblNewLabel_10);
		sl_ContributePanel.putConstraint(SpringLayout.EAST, panel, -148, SpringLayout.EAST, ContributePanel);
		ContributePanel.add(panel);
		
		JPanel panel_2 = new  RoundedPanel3(30,Color.CYAN);
		panel_2.setBackground(Color.BLACK);
		sl_ContributePanel.putConstraint(SpringLayout.NORTH, panel_2, 18, SpringLayout.SOUTH, lblNewLabel_7_1);
		sl_ContributePanel.putConstraint(SpringLayout.WEST, panel_2, 21, SpringLayout.EAST, panel);
		sl_ContributePanel.putConstraint(SpringLayout.SOUTH, panel_2, 1, SpringLayout.SOUTH, panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("Decline");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_11.setBounds(10, 11, 73, 21);
		panel.add(lblNewLabel_11);
		sl_ContributePanel.putConstraint(SpringLayout.EAST, panel_2, -27, SpringLayout.EAST, ContributePanel);
		ContributePanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_11_1 = new JLabel("Submit");
		lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_11_1.setForeground(Color.WHITE);
		lblNewLabel_11_1.setBounds(10, 11, 73, 21);
		panel_2.add(lblNewLabel_11_1);
		
		JPanel panel_3 = new RoundedPanel3(50,new Color(224, 224, 210));
		panel_3.setBackground(new Color(248, 248, 255));
		panel_3.setBounds(298, 368, 513, 272);
		displayPanel.add(panel_3);
		
		JPanel panel_4 = new RoundedPanel3(50 , new Color(224, 224, 210));
		panel_4.setBackground(new Color (248, 248, 255));
		panel_4.setBounds(297, 308, 513, 55);
		displayPanel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_12 = new JLabel("Contribution History");
		lblNewLabel_12.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_12.setBounds(28, 11, 209, 33);
		panel_4.add(lblNewLabel_12);
		
						
						
		
		
		
		
		JPanel toolbarPanel = new RoundedPanel3(20 , new Color(51, 204, 204));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, toolbarPanel, 30, SpringLayout.NORTH, contentPane);
		toolbarPanel.setBackground(Color.WHITE);
		sl_contentPane.putConstraint(SpringLayout.NORTH, toolbarPanel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, toolbarPanel, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, toolbarPanel, 250, SpringLayout.WEST, contentPane);
		contentPane.add(toolbarPanel);
		SpringLayout sl_ContributePanel1 = new SpringLayout();
		toolbarPanel.setLayout(sl_ContributePanel1);
		
					 popupmenu = new JPopupMenu(); 
			         gMembers = new JMenuItem("Group Members");  
			         gMembers.addActionListener(this);
			         home = new JMenuItem("Home "); 
			         home.addActionListener(this);

			        popupmenu.add(gMembers); popupmenu.add(home);   
					
		JLabel lblNewLabel = new JLabel("");
		sl_ContributePanel1.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, toolbarPanel);
		sl_ContributePanel1.putConstraint(SpringLayout.WEST, lblNewLabel, 20, SpringLayout.WEST, toolbarPanel);
		lblNewLabel.setIcon(new ImageIcon(IndividulaMembSharesPage.class.getResource("/resourcesPics/Data-List-icon (1).png")));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {              
                popupmenu.show( toolbarPanel, e.getX(), e.getY());  
            }    
	          });
		toolbarPanel.add(lblNewLabel);
		
		
		JLabel lblNewLabel_3 = new JLabel("More");
		sl_ContributePanel1.putConstraint(SpringLayout.EAST, lblNewLabel_3, 99, SpringLayout.WEST, toolbarPanel);
		lblNewLabel_3.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		sl_ContributePanel1.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 8, SpringLayout.NORTH, toolbarPanel);
		sl_ContributePanel1.putConstraint(SpringLayout.WEST, lblNewLabel_3, 50, SpringLayout.WEST, toolbarPanel);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {              
                popupmenu.show( toolbarPanel, e.getX(), e.getY());  
            }    
	          });
		toolbarPanel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 204, 204));
		sl_ContributePanel1.putConstraint(SpringLayout.NORTH, panel_1, -27, SpringLayout.SOUTH, toolbarPanel);
		sl_ContributePanel1.putConstraint(SpringLayout.WEST, panel_1, -85, SpringLayout.EAST, lblNewLabel_3);
		sl_ContributePanel1.putConstraint(SpringLayout.SOUTH, panel_1, -3, SpringLayout.SOUTH, toolbarPanel);
		sl_ContributePanel1.putConstraint(SpringLayout.EAST, panel_1, -10, SpringLayout.EAST, lblNewLabel_3);
		toolbarPanel.add(panel_1);
								
						contentPane.setBackground(Color.WHITE)		;							
						this.setBounds(100, 0, 1100, 700);
						this.setBackground(Color.WHITE);
			}
	
	public void importMembers()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		if (e.getSource() == gMembers)
		{
		   		}
		if (e.getSource() == inMembers)
		{			
		}
		if (e.getSource() == home)
		{

		}
		
	}
	
	public	void fromsql ()
	{
		memberFromSql = memberRegistrationLogic.memberInfoList_sql;
		
	}
	
	
	

	
	
	
}
