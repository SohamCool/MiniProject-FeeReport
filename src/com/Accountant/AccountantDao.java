package com.Accountant;

import java.sql.Connection;
import java.util.ArrayList;

//import java.sql.Connection;
//import java.sql.DriverManager;

//public interface AccountantDao
public interface AccountantDao {
	public Connection getCon();
	public int addAccountant(Accountant a);
	public ArrayList viewAccountant();
	public boolean accountantLogin(String uname, String pwd);
//	int addAccountant(Accountant a);
//	int addAcoountant(Accountant a);
	
//	public static Connection getCon() {
//		Connection con = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feereportdb","root","root");
//			}
//		catch(Exception e) {
//			System.out.println(e);
//		}
//		return con;
//	}
	//public static boolean validate();
	//int addAccountant(Accountant a);
}
