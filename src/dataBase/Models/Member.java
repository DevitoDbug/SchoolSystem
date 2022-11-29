package dataBase.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import dataBase.database;

public class Member {
	private Connection connection = null;

	
	private static final String tableTitle = "member_registration";
	private static final String tableFields = 
			"member_registrationID, " +
			"first_name, " + 
			"second_name, " + 
			"age";

	
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	
	
	//this is to store hold member data from sql
	String memberID;
	
	//
	String g_regID;//id for the group member 
	
	ArrayList <String> m_idlist = new ArrayList<>();

	


	String ftName_sql ;
	String sName_sql ;
	int age_sql;
	String memberID_sql;
	
	public Member(String memberID_sql ,String ftName_sql, String sName_sql, int age_sql) {
		super();
		this.memberID_sql = memberID_sql;
		this.ftName_sql = ftName_sql;
		this.sName_sql = sName_sql;
		this.age_sql = age_sql;
	}
	
	String goup_ID_sql;

	
	
	public String getMemberID_sql() {
		return memberID_sql;
	}

	public String getFtName_sql() {
		return ftName_sql;
	}

	public String getsName_sql() {
		return sName_sql;
	}

	public int getAge_sql() {
		return age_sql;
	}

	////
	
	
	public Member()
	{
		
	}
	
	
	public Member(String firstName, String lastName, Date dateOfBirth) {
		generateID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(getDateOfBirth().toLocalDate(),currentDate).getYears();
    }
	
	public String getMemberID() {
		return g_regID;
	}
	
	public String toSqlStatement() {
        StringBuilder tableValues = new StringBuilder();
        for(String ignored : tableFields.split(",")){
            tableValues.append("?,");
        }
        tableValues.deleteCharAt(tableValues.length() - 1);

        return String.format("INSERT INTO %s (%s) VALUE (%s)", tableTitle, tableFields, tableValues);
    }
	
	private void generateID() 
	{
		connection = new database().createConnection();
        System.out.println(connection);
		if (connection!=null)
		{
			String sql = "SELECT member_registrationID FROM mwanzobarakadb.member_registration;";
			java.sql.Statement statement;
			try {
				statement = connection.createStatement();
				ResultSet result =  statement.executeQuery(sql);
				
				while (result.next())
				{
					m_idlist.add(result.getString("regID"));
				}
				int size = m_idlist.size();
				
				System.out.println("here");
				System.out.println(m_idlist.isEmpty());
				
				g_regID = (size == 0)?"Im-1":String.format("Im-%s", size+1);//generated the group member id 
				
				connection.close();
			} catch (SQLException e) {
				System.out.println("\nDid not generate goup id error");
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	@Override
	public String toString() {
		return "Member[g_regID="+g_regID + " firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
}
