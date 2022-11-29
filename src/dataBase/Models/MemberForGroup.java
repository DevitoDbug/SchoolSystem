package dataBase.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import dataBase.database;

public class MemberForGroup {
	private Connection connection = null;
	//regID, first_name, second_name, age, goup_ID
	private static final String tableTitle = "mwanzobarakadb.group_member_registration";
	private static final String tableFields = 
			"regID, " + 
			"first_name, " + 
			"second_name, "+
			"age, "+
			"goup_ID"
			; 
	
	String Mg_regID;//id for the group member 
	String fname;
	String Sname;
	int age;
	String GroupId ;
	
	ArrayList <String> gm_idlist = new ArrayList<>();
	
//	regID, first_name, second_name, age, goup_ID
//constractor for data from sql 
	String regID_sql;
	String fName_sql;
	String sName_sql;
	int age_sql;
	String goup_ID_sql;
		
	public MemberForGroup(String regID_sql, String fName_sql, String sName_sql, int age_sql, String goup_ID_sql) {
		this.regID_sql = regID_sql;
		this.fName_sql = fName_sql;
		this.sName_sql = sName_sql;
		this.age_sql = age_sql;
		this.goup_ID_sql = goup_ID_sql;
	}
	
	public String getRegID_sql() {
		return regID_sql;
	}

	public String getfName_sql() {
		return fName_sql;
	}

	public String getsName_sql() {
		return sName_sql;
	}

	public int getAge_sql() {
		return age_sql;
	}

	public String getGoup_ID_sql() {
		return goup_ID_sql;
	}
//


	public MemberForGroup( String fname, String sname, int age, String groupId) {
		generateID();
		this.fname = fname;
		Sname = sname;
		this.age = age;
		GroupId = groupId;
		
	}
	
	
	public MemberForGroup()
	{
		
	}
	

	


	public String getMg_regID() {
		return Mg_regID;
	}

	public void setMg_regID(String mg_regID) {
		Mg_regID = mg_regID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGroupId() {
		return GroupId;
	}

	public void setGroupId(String groupId) {
		GroupId = groupId;
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
			String sql = "SELECT regID FROM mwanzobarakadb.group_member_registration;";
			java.sql.Statement statement;
			try {
				statement = connection.createStatement();
				ResultSet result =  statement.executeQuery(sql);
				
				while (result.next())
				{
					gm_idlist.add(result.getString("regID"));
				}
				int size = gm_idlist.size();
				
				System.out.println("here");
				System.out.println(gm_idlist.isEmpty());
				
				Mg_regID = (size == 0)?"Gm-1":String.format("Gm-%s", size+1);//generated the group member id 
				
				connection.close();
			} catch (SQLException e) {
				System.out.println("\nDid not generate goup id error");
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
	//regID, first_name, second_name, age, goup_ID

	@Override
	public String toString() {
		return "Member [regID=" + Mg_regID + ", first_name=" + fname + ", second_name=" + Sname + ", Age= "+age+" ,goup_ID " + GroupId+"]";
	}
	
	
}
