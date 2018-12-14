import java.sql.*;
import java.util.ArrayList;

public class DBCommands {
	public void insert(String username){
		try (
				Connection conn = DriverManager
						.getConnection("jdbc:mysql://localhost:" + DBCreator.PORT + "/chess?user=root&password=root"); 
				Statement stmt = conn.createStatement();
				) 
		{
			String sqlInsert = "insert into leaderboard " 
					+ "values ( '" + username + "', 0)";
			stmt.execute(sqlInsert);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<String> select(){
		ArrayList<String> leaderboard = new ArrayList<String>();
		try (
				Connection conn = DriverManager
						.getConnection("jdbc:mysql://localhost:" + DBCreator.PORT + "/chess?user=root&password=root"); 
				Statement stmt = conn.createStatement();
				) 
		{
			String sqlSelect = "select user, points from leaderboard";
			ResultSet rset = stmt.executeQuery(sqlSelect);
			while(rset.next()){
				String username = rset.getString("user");
				int points = rset.getInt("points");
				leaderboard.add("" + points + ", " + username);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return leaderboard;
	}
	
	public void update(String username, int amount){
		try (
				Connection conn = DriverManager
						.getConnection("jdbc:mysql://localhost:" + DBCreator.PORT + "/chess?user=root&password=root"); 
				Statement stmt = conn.createStatement();
				) 
		{
			String sqlUpdate = "update leaderboard set points = points + " + amount + " where user = '" + username +"'";
			stmt.execute(sqlUpdate);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
