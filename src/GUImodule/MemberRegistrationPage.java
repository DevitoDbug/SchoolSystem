package GUImodule;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import dataBase.database;
import dataBase.Models.Member;
import logicTools.MemberRegistrationLogic;



public class MemberRegistrationPage extends JPanel{
	private final String pattern = "MM/dd/yyyy";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement = null; //submits data to the sql 
    
	MemberRegistrationLogic memberreglogic = new MemberRegistrationLogic();

	
	Panel purpleSpace ;
	JPanel registerPanel;
	JButton registerButton;
	JTextField fNametxtBox;
	JTextField SNametxtBox;
	JDateChooser dateChooser;
	JTextField regFee;
	
	int age ;
	public Date birthDate;
	
	GroupMemberRegPage regAs_group = new GroupMemberRegPage();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


	
	

  

	MemberRegistrationPage()
	{
		//adding the look and feel 
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf    () );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize theme. Using fallback." );
		}
		
		regAs_group.setVisible(false);

		
			purpleSpace = new Panel();
			purpleSpace.setLayout(null);
			purpleSpace.setVisible(true);
			purpleSpace.setBackground(new Color(204, 153, 255));
			purpleSpace.setBounds(0, 0, 873, 661);
			
			 				registerPanel =new RoundedPanel1(50 , new Color(153, 51, 255));
							registerPanel.setLayout(null);
							registerPanel.setVisible(true);
							registerPanel.setBackground(new Color(153, 51, 255));
							registerPanel.setBounds(460, 71, 384, 267);
							registerPanel.setOpaque(false);

										//first name
										fNametxtBox = new JTextField();
										fNametxtBox.setForeground(Color.BLACK);
										fNametxtBox.setColumns(10);
										fNametxtBox.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "First Name", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(204, 204, 204)));
										fNametxtBox.setBackground(new Color(153, 51, 255));
										fNametxtBox.setBounds(56, 20, 262, 40);

										//second name
										SNametxtBox = new JTextField();
										SNametxtBox.setForeground(Color.BLACK);
										SNametxtBox.setColumns(10);
										SNametxtBox.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Second Name", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(204, 204, 204)));
										SNametxtBox.setBackground(new Color(153, 51, 255));
										SNametxtBox.setBounds(56, 70, 262, 40);
									
										//Date of birth
										dateChooser = new JDateChooser( "dd/MM/yy" , "##/##/##" , '_' );
										dateChooser.getCalendarButton().setBounds(240, 1, 21, 32);
										dateChooser.setBounds(58, 120, 262, 30);
										dateChooser.setBackground(Color.BLUE);
										
										
										//this is the registration fee 
										//second name
										regFee = new JTextField();
										regFee.setForeground(Color.BLACK);
										regFee.setColumns(10);
										regFee.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registraion Fee", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(204, 204, 204)));
										regFee.setBackground(new Color(153, 51, 255));
										regFee.setBounds(58, 160, 262, 40);
										regFee.setText("1000");
		
										 
								registerButton = new JButton("Register");			
								registerButton.setFont(new Font("Tahoma", Font.BOLD, 13));
								registerButton.setFocusable(false);
								registerButton.setBounds(56, 218, 89, 23);
								registerButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										//calculating the age 
														
										if (fNametxtBox.getText().toString() != null && SNametxtBox.getText().toString() != null 
												&& dateChooser.getDate() != null && Integer.parseInt(regFee.getText()) == 1000)
										{
														int a=JOptionPane.showConfirmDialog(null,"Register As A Group Member?"); 
												
														if(a==JOptionPane.YES_OPTION){ 
															//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
													        LocalDate currentDate = LocalDate.now();
													        
													        Integer value  = Period.between(  dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()  ,currentDate).getYears();
													        System.out.println("\n"+value);

															regAs_group.fNametxtBox.setText(MemberRegistrationPage.this.fNametxtBox.getText().toString());
															regAs_group.SNametxtBox.setText(MemberRegistrationPage.this.SNametxtBox.getText().toString());
															regAs_group.age.setText(value.toString());
															regAs_group.setVisible(true);
														
														}  
														if (a==JOptionPane.NO_OPTION)
														{
															var member = new Member(
																	fNametxtBox.getText(),
																	SNametxtBox.getText(),
																	getMemberDOB()
																	);
															System.out.println(member);
															memberreglogic.submitMemberToDatabase(member);
															
															//resetting the testboxes 
															fNametxtBox.setText("");
															SNametxtBox.setText("");
															dateChooser.setDate(null);
															JOptionPane.showMessageDialog(null,"Welcome to Manzo Baraka! Time for a new beginning");
															
														}
														if (a == JOptionPane.CANCEL_OPTION)
														{
															fNametxtBox.setText("");
															SNametxtBox.setText("");
															dateChooser.setDate(null);
															
														}
													
										}
										else if (Integer.parseInt(regFee.getText()) != 1000 )
										{
											JOptionPane.showMessageDialog(purpleSpace,"Registration Fee is strictly 1000 KSH");
											regFee.setText("");		
										}else
										{
											JOptionPane.showMessageDialog( null , "Please fill all the required information");
										}
									}
									
								});
								
							registerPanel.add(registerButton);
							registerPanel.add(SNametxtBox);
							registerPanel.add(fNametxtBox);
							registerPanel.add(dateChooser);
							registerPanel.add(regFee);


			
							
							
				JLabel imageelegant = new JLabel();
				imageelegant.setFont(new Font("Tahoma", Font.BOLD, 12));
				imageelegant.setIcon(new ImageIcon(MemberRegistrationPage.class.getResource("/resourcesPics/background.jpg")));
				imageelegant.setHorizontalAlignment(SwingConstants.CENTER);
				imageelegant.setBounds(0, 0, 430, 661);
				
		
			purpleSpace.add(registerPanel);
			purpleSpace.add(imageelegant);
			
			regAs_group.setBounds(0, 0, 873, 662);
			
		this.add(regAs_group)	;
		this.add(purpleSpace);
		this.setBounds(0, 0, 873, 662);
		this.setLayout(null);
		this.setVisible(true);	
	}
	
	
	public static int calculateAge(LocalDate dob)   
	{  
			LocalDate curDate = LocalDate.now();  
			if ((dob != null) && (curDate != null))   
			{  
				return Period.between(dob, curDate).getYears();  
			}  
			else  
			{  
				return 0; 
			}
	} 
	
	
	
	
	
	
	
	//sql updates 
	
	private Date getMemberDOB() {
        String stringDate =  simpleDateFormat.format(dateChooser.getDate());
        try {
            return new Date(simpleDateFormat.parse(stringDate).getTime());
        } catch (Exception e){
            return null;
        }
    }
	
	

}
