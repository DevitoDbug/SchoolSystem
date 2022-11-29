package dataBase.Models;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.database;
import logicTools.GroupRegLogic;

public class GroupReg {
	public String generatedIDvalue;

	
	private static final String tableTitle = "mwanzobarakadb.groups";
	private static final String tableFields = 
			"groupsID, " + 
			"group_name, "+
			"regFee, " +
			"no_members" 
			;

	private String gName;
	private String gId;
	private int regFee;
	private int no_members;
	
	//values from sql
	public  String gNameSql;
	private String gIdSql;
	private int regFeeSql;
	private int no_membersSql;
	
	
	
	private Connection connection = null;
	 private Statement statement = null;
	 private ResultSet resultSet;
	 private PreparedStatement preparedStatement = null; //submits data to the sql 
	 
	 ArrayList <String> IDlist = new ArrayList <>();
	 ArrayList <GroupReg> groupList = new ArrayList <>();
	
	 
	 //constructors 
	public  GroupReg(String gName , int  regFee, int no_members) {
		generateID();
		this.gName = gName;
		this.regFee = regFee;
		this.no_members = no_members;

	}
	
	public GroupReg()
	{
		
	}
	
	public GroupReg(String gNameSql, String gIdSql, int regFeeSql, int no_membersSql) {
		super();
		this.gNameSql = gNameSql;
		this.gIdSql = gIdSql;
		this.regFeeSql = regFeeSql;
		this.no_membersSql = no_membersSql;
	}
	
	
	
	
	

	
	public String getgNameSql() {
		return gNameSql;
	}

	public String getgIdSql() {
		return gIdSql;
	}

	public int getRegFeeSql() {
		return regFeeSql;
	}

	public int getNo_membersSql() {
		return no_membersSql;
	}
	
	
	

	public int getNo_members() {
		return no_members;
	}


	public void setNo_members(int no_members) {
		this.no_members = no_members;
	}


	public int getRegFee() {
		return regFee;
	}

	public void setRegFee(int regFee) {
		this.regFee = regFee;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgId() {
		return gId;
	}

	public void setgId(String gId) {
		this.gId = gId;
	}
	
	public String toSqlStatement() {
        StringBuilder tableValues = new StringBuilder();
        for(String ignored : tableFields.split(",")){//used to insert the place holders for the sql
            tableValues.append("?,");
        }
        tableValues.deleteCharAt(tableValues.length() - 1);

        return String.format("INSERT INTO %s (%s) VALUE (%s)", tableTitle, tableFields, tableValues);
    }

	@Override
	public String toString() {
		return "Member [gName=" + gName + ", gId=" + gId + ", regFee= "+ regFee +", no_members= " +no_members+"]";
	}
	
	
	
	
	private void generateID() 
	{
		connection = new database().createConnection();
        System.out.println(connection);
		if (connection!=null)
		{
			String sql = "SELECT groupsID FROM mwanzobarakadb.groups;";
			java.sql.Statement statement;
			try {
				statement = connection.createStatement();
				ResultSet result =  statement.executeQuery(sql);
				
				while (result.next())
				{
					IDlist.add(result.getString("groupsID"));
				}
				int size = IDlist.size();
				
				System.out.println("here");
				System.out.println(IDlist.isEmpty());
				
				gId = (size == 0)?"G-1":String.format("G-%s", size+1);//generated the member id 
				
				connection.close();
				System.out.println(generatedIDvalue);
			} catch (SQLException e) {
				System.out.println("here");
				e.printStackTrace();
			}
			
		}
		System.out.println("\n\n The generated id for the group is: "+generatedIDvalue);
		
	}
	
	
}
	

