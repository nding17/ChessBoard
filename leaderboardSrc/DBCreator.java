import java.sql.*;

public class DBCreator {

	public static final int PORT = 3306;
	
	public void create() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT + "/chess?user=root&password=root");
				Statement stmt = conn.createStatement();) {

			String sql = "create table leaderboard ( " + "user varchar(50), " + "points int," + "primary key (user));";
			stmt.execute(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void delete() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT + "/chess?user=root&password=root");
				Statement stmt = conn.createStatement();) {

			String sql = "drop table leaderboard";
			stmt.execute(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public boolean tableExists(String name) {
		try (Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:" + PORT + "/chess?user=root&password=root");) {
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, name, null);
			return tables.next();
		} catch (SQLException ex){
			ex.printStackTrace();
			return false;
		}
		
	}
}
