package logicTools;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.database;
import dataBase.Models.Member;

public class MemberRegistrationLogic {
	private Connection connection = null;
	 private Statement statement = null;
	 private ResultSet resultSet;
	 private PreparedStatement preparedStatement = null; //submits data to the sql 
	 
	 //data fro sql 
	 public ArrayList <Member> memberInfoList_sql = new ArrayList <>();
	 Member memberInfo_sql = new Member();
		

	public MemberRegistrationLogic() {
		receiveGroupDataFromDatabase();
		
	}


	
	public void submitMemberToDatabase(Member member) {
        connection = new database().createConnection();
        System.out.println(connection);
		sendMemberDataToDatabase(member);
    }

	
	private void sendMemberDataToDatabase(Member member) {
		System.out.println(member);
        try {
            if(connection!=null){
                preparedStatement = connection.prepareStatement(member.toSqlStatement());
                
                preparedStatement.setString (1,member.getMemberID());
                preparedStatement.setString (2, member.getFirstName());
                preparedStatement.setString(3,member.getLastName());
                preparedStatement.setInt(4, member.getAge());

                preparedStatement.execute();
                connection.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
	public void receiveGroupDataFromDatabase()  {
        connection = new database().createConnection();

			if (connection!=null)
			{
				String sql = "SELECT * FROM mwanzobarakadb.member_registration;";
				java.sql.Statement statement;
				try {
					statement = connection.createStatement();
					ResultSet result =  statement.executeQuery(sql);
					
					while (result.next())
					{
						System.out.println ("Fetching member data from sql");
						String memberId = result.getString("member_registrationID");
						String firstname  = result.getString("first_name");
						String secondname  = result. getString ("second_name");
						int age = result.getInt("age");
						memberInfo_sql = new Member (memberId ,firstname, secondname, age );
						memberInfoList_sql.add(memberInfo_sql);
						
						System.out.println( "Fetched data is \n"+"[memberId: "+memberId+" firstname="+firstname+" age: "+age+ "]");

						
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
