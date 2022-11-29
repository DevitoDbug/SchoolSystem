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

public class GroupRegLogic {
	public static String something = "";
	
	private Connection connection = null;
	 private Statement statement = null;
	 private ResultSet resultSet;
	 private PreparedStatement preparedStatement = null; //submits data to the sql 
	 
	 public static ArrayList <GroupReg> groupInfo = new ArrayList<>();
	 public static ArrayList <String> groupNamesFromSQL = new ArrayList <>();
	 public static String groupIDFromSQL ;
		

	public GroupRegLogic() {
		receiveGroupDataFromDatabase();
	}
	

	public void submitGroupToDatabase(GroupReg group) {
        connection = new database().createConnection();
		sendGroupDataToDatabase(group);
    }

	
	private void sendGroupDataToDatabase(GroupReg group) {
		System.out.println("2     ." + group);
        try {
            if(connection!=null){
                preparedStatement = connection.prepareStatement(group.toSqlStatement());
                
               
                preparedStatement.setString (1, group.getgId());
                preparedStatement.setString(2,group.getgName());
                preparedStatement.setInt(3, group.getRegFee());
                preparedStatement.setInt(4,group.getNo_members());
            	

                preparedStatement.execute();
                connection.close();
                System.out.println("We are sending values to the sql");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
	public void receiveGroupDataFromDatabase()  {
        connection = new database().createConnection();

			if (connection!=null)
			{
				String sql = "SELECT * FROM mwanzobarakadb.groups";
				java.sql.Statement statement;
				try {
					statement = connection.createStatement();
					ResultSet result =  statement.executeQuery(sql);
					
					while (result.next())
					{
						groupNamesFromSQL.add(result.getString("group_name")); 

						
					}
					connection.close();
					
				}
				
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			
	}
	
	public String getGroupID(String groupName)
	{
		 connection = new database().createConnection();

			if (connection!=null)
			{
				String sql = String.format("SELECT groupsID FROM mwanzobarakadb.groups WHERE group_name = \"%s\"", groupName);
				java.sql.Statement statement;
				try {
					statement = connection.createStatement();
					ResultSet result =  statement.executeQuery(sql);
					
					while (result.next())
					{
						groupIDFromSQL = result.getString("groupsID");

						
					}
					System.out.println("The id for the group "+groupName+" is "+groupIDFromSQL);
					connection.close();
					
				}
				
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
			return groupIDFromSQL;
		
	}



}
