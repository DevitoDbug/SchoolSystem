package logicTools;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.database;
import dataBase.Models.GroupReg;
import dataBase.Models.Member;
import dataBase.Models.MemberForGroup;

public class GroupMemberRegLogic {
		public static String something = "";
		
		private Connection connection = null;
		 private PreparedStatement preparedStatement = null; //submits data to the sql 
		 
		 public static ArrayList <GroupReg> groupInfo = new ArrayList<>();
		 
		 //data fro sql 
		 public ArrayList <MemberForGroup> memberInfoList_sql = new ArrayList <>();
		 MemberForGroup memberForgroup_sql = new MemberForGroup();
		

		 

		public GroupMemberRegLogic() {
			receiveGroupDataFromDatabase();
		}
		

		public void submitMemberGroupToDatabase(MemberForGroup memberForGroup) {
	        connection = new database().createConnection();
			sendGroupDataToDatabase(memberForGroup);
			
	    }

		
		
		
		private void sendGroupDataToDatabase(MemberForGroup Mgroup) {
			connection = new database().createConnection();
			System.out.println("\n connected to member group data base" + Mgroup);
			System.out.println("\n"+connection+"\n");
	        try {
	            if(connection != null){
		                preparedStatement = connection.prepareStatement(Mgroup.toSqlStatement());
		                //regID, first_name, second_name, age, goup_ID
		            	
		                preparedStatement.setString (1,Mgroup.getMg_regID());
		                preparedStatement.setString(2,Mgroup.getFname());
		                preparedStatement.setString(3, Mgroup.getSname());
		                preparedStatement.setInt(4, Mgroup.getAge());
		                preparedStatement.setString(5,Mgroup.getGroupId());
	
		                preparedStatement.execute();
		                connection.close();
		                System.out.println("\nWe are sending group member info  to the sql");
	            	}
	            connection.close();

	        	} 
	        catch (Exception e) {
	        	System.out.println("\n data has not been sent to sql");
	            System.out.println(e.getMessage());
	        }
	    }
		
		
		public void receiveGroupDataFromDatabase()  {
	        connection = new database().createConnection();

				if (connection!=null)
				{
					String sql = "SELECT * FROM mwanzobarakadb.group_member_registration;";
					java.sql.Statement statement;
					try {
						statement = connection.createStatement();
						ResultSet result =  statement.executeQuery(sql);
						
						while (result.next())
						{
//							regID, first_name, second_name, age, goup_ID
							System.out.println ("Fetching Group member data from sql");
							String memberId = result.getString("regID");
							String firstname  = result.getString("first_name");
							String secondname  = result. getString ("second_name");
							int age = result.getInt("age");
							String group_ID = result.getString("goup_ID");
//							String regID_sql, String fName_sql, String sName_sql, int age_sql, String goup_ID_sql
							memberForgroup_sql = new MemberForGroup (memberId ,firstname, secondname, age, group_ID );
							memberInfoList_sql.add(memberForgroup_sql);
							
							System.out.println( "Fetched data is \n"+"[regID: "+memberId+" firstname: "+secondname+" secondname: "+age+ " group_ID: "+group_ID+"]");

							
						}
						connection.close();
						
					}
					
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
		}
		
		
				
}

		
		


