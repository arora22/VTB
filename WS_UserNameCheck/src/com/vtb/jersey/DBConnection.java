package com.vtb.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl,
					Constants.dbUser, Constants.dbPwd);
			
			System.out.println(Constants.dbUrl);
			System.out.println("In Try");
		} catch (Exception e) {
			System.out.println("In Catch ");
			throw e;
		} finally {
			System.out.println("In Final ");
			return con;
		}
	}

	
	public static boolean checkUnameAvailability(String uname) throws Exception {
		boolean isUnameAvailable = true;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM useraccount WHERE username = '"
					+ uname + "'";
			ResultSet rs = stmt.executeQuery(query);
			// Check whether uname exists in DB
			while (rs.next()) {
				isUnameAvailable = false;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {

			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isUnameAvailable;
	}
}
