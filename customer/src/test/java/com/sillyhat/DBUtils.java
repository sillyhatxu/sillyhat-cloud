package com.sillyhat;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DBUtils {

	private static DBUtils db = new DBUtils();

	private DBUtils(){
		
	}

	
	public static Connection getConnection(){
		Connection conn = null;
		try{
			String user = "root";
//			String user = "deja_read_portal";
			String password = "mozat2014";
			String url = "jdbc:mysql://deja-di.ccf2gesv8s9h.ap-southeast-1.rds.amazonaws.com:3306?useSSL=false";
//			String url = "jdbc:mysql://deja-di.ccf2gesv8s9h.ap-southeast-1.rds.amazonaws.com:3306?"
//					+ "user=deja_read_portal&password=mozat2014";
			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url);
//			log.info("url:"+url);
//			conn = DriverManager.getConnection(url);
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			log.error("getConnection error",e);
		}
		return conn;
	}
	public static void close(Connection con , PreparedStatement p , ResultSet rs){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				log.error("Connection close error" + e.getMessage());
			}
		}
		if(p != null){
			try {
				p.close();
			} catch (SQLException e) {
				log.error("PreparedStatement close error" + e.getMessage());
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("ResultSet close error" + e.getMessage());
			}
		}
	}
	public static void main(String args[]){
		for (int i = 0; i < 50; i++) {
			Connection con = DBUtils.getConnection();
			System.out.println(con);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
