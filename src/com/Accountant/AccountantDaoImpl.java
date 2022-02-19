package com.Accountant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.Admin.AdminSection;
import com.feeReport.FeeReport;

public class AccountantDaoImpl implements AccountantDao{
	static Connection con = null;
	static PreparedStatement pt = null;
	static ResultSet rs = null;
	
//	@Override
	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feereportdb","root","root");
			}
		catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	
//	@Override
//	public int addAccountant(Accountant a) {
//		int status = 0;
//		try {
//			con = getCon();
//			pt = con.prepareStatement("insert into Accountant(Name, Password, Email, ContactNo) values(?,?,?,?)");
//			pt.setString(1, a.getName());
//			pt.setString(2, a.getPwd());
//			pt.setString(3, a.getEmail());
//			pt.setString(4, a.getPhone());
//			status = pt.executeUpdate();
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return status;
//	}
	@Override
	public ArrayList viewAccountant() {
		ArrayList<Accountant> accountants = new ArrayList<>();
		try {
			con = getCon();
			pt = con.prepareStatement("select * from Accountant");
			rs = pt.executeQuery();
			while(rs.next()) {
				Accountant a = new Accountant();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPwd(rs.getString(3));
				a.setEmail(rs.getString(4));
				a.setPhone(rs.getString(5));
				accountants.add(a);
			}
		}catch(Exception e) {
		System.out.println(e);
		}
		return accountants;
	}
//		ListIterator<Accountant> itr = accountants.listIterator();
//		while(itr.hasNext()) {
//			Accountant accdetails = itr.next();
//			System.out.println("\nID: "+accdetails.getId()+"\nName: "+accdetails.getName()+"\nPassword: "+accdetails.getPwd()+"\nEmail: "+accdetails.getEmail()+"\nContact No: "+accdetails.getPhone()+"\n");
//		}

//	}
//	public int accountantLogin(String name, String pwd) {
//		int status=0;
//		try {
//			con = getCon();
//			pt = con.prepareStatement("select * from Accountant where Name = ? and Password = ?");
//			pt.setString(1, name);
//			pt.setString(2, pwd);
//			rs =pt.executeQuery();
//			if(rs.next())
//				++status;
//			else
//				--status;
//		}catch(Exception e) {System.out.println(e);}
//		return status;
//	}

	@Override
	public boolean addAccountant(Accountant a) {
		int status = 0;
		try {
			con = getCon();
			pt = con.prepareStatement("insert into Accountant(Name, Password, Email, ContactNo) values(?,?,?,?)");
			pt.setString(1, a.getName());
			pt.setString(2, a.getPwd());
			pt.setString(3, a.getEmail());
			pt.setString(4, a.getPhone());
			status = pt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		if(status > 0)
			return true;
		return false;
	}

	@Override
	public boolean accountantLogin(String uname, String pwd) {
		boolean status = false;
		try {
			con = getCon();
			pt = con.prepareStatement("select * from Accountant where Name = ? and Password = ?");
			pt.setString(1, uname);
			pt.setString(2, pwd);
			rs =pt.executeQuery();
			if(rs.next())
				status= true;
			else
				status= false;
		}catch(Exception e) {System.out.println(e);}
		return status;
	}


//	public static int addAccountant(Accountant a) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
