package com.xiaoxin.gdata.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		try {
			//1、通过当前的类获得类加载器
			ClassLoader classloader = JDBCUtils.class.getClassLoader();
			//2、通过类加载器的方法获得一个输入流
			InputStream is = classloader.getResourceAsStream("db_qa.properties");
			//3、创建一个properties对象
			Properties props = new Properties();
			//4、加载输入流
			props.load(is);

			//5、获取相关的参数
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 获取资源链接
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}



	/**
	 * 关闭资源
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void release(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
