package com.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import com.Accountant.AccountantDao;
import com.Accountant.AccountantSection;
import com.feeReport.FeeReport;

public class StudentDaoImpl {
	static Connection con = null;
	static PreparedStatement pt = null;
	static ResultSet rs = null;
	static Scanner sc = new Scanner(System.in);

//	public static Student getStudentData() {
//		int id = 0,due,paid,fee;
//		String name,email,course,address,city,state,country,phoneno;
//		System.out.println("Name: ");
//		name = sc.next();
//		System.out.println("Email: ");
//		email = sc.next();
//		System.out.println("Course: ");
//		course = sc.next();
//		System.out.println("Fee: ");
//		fee = sc.nextInt();
//		System.out.println("Paid: ");
//		paid = sc.nextInt();
//		System.out.println("Due: ");
//		due = fee-paid;
//		sc.nextLine();
//		System.out.println(due);
//		System.out.println("Address: ");
//		address = sc.nextLine();
//		System.out.println("City: ");
//		city = sc.next();
//		System.out.println("State: ");
//		state = sc.next();
//		System.out.println("Country: ");
//		country = sc.next();
//		System.out.println("Contact No: ");
//		phoneno = sc.next();
//		Student s = new Student(id, name, email, course, fee, paid, due, address, city, state, country, phoneno);
//	//	addStudent(s);
//		return s;
//	}
		
	public static int addStudent() {
		System.out.println("\n------------ Add Student ------------\n");
		Student s = FeeReport.getStudentData();
		int status=0;
		try {
			con = AccountantDao.getCon();
			pt = con.prepareStatement("insert into Student(Name, Email, Course, Fee, Paid, Due, Address, City, State, Country, Contactno) values(?,?,?,?,?,?,?,?,?,?,?)");
			pt.setString(1, s.getName());
			pt.setString(2, s.getEmail());
			pt.setString(3, s.getCourse());
			pt.setInt(4, s.getFee());
			pt.setInt(5, s.getPaid());
			pt.setInt(6, s.getDue());
			pt.setString(7, s.getAddress());
			pt.setString(8, s.getCity());
			pt.setString(9, s.getState());
			pt.setString(10, s.getCountry());
			pt.setString(11, s.getContactno());
			status= pt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);		}
		return status;
//		if(status>0) 
//			System.out.println(s.getName()+"'s data added to the database successfully...\n");
//		
//		else 
//			System.out.println("Unable to add "+s.getName()+"'s data to the database! \n");
//		AccountantSection.main(new String[] {});

	}
	
	public static void viewStudent() {
		ArrayList<Student> students = new ArrayList<>();
		Student s=null;
		try {
			con = AccountantDao.getCon();
			pt = con.prepareStatement("select * from Student");
			rs = pt.executeQuery();
			while(rs.next()) {
				s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setEmail(rs.getString(3));
				s.setCourse(rs.getString(4));
				s.setFee(rs.getInt(5));
				s.setPaid(rs.getInt(6));
				s.setDue(rs.getInt(7));
				s.setAddress(rs.getString(8));
				s.setCity(rs.getString(9));
				s.setState(rs.getString(10));
				s.setCountry(rs.getString(11));
				s.setContactno(rs.getString(12));
				students.add(s);
			}
			
		}catch(Exception e) {System.out.println(e);}
		
		ListIterator<Student> li = students.listIterator();
		System.out.println("\n-------------- Student List --------------\n");
		FeeReport.printData(students, s);
//		String[] cols = new String[]{"ID", "Name", "Email", "Course", "Fee", "Paid", "Due", "Address", "City", "State", "Country", "Contactno"};
//		System.out.println();
//		System.out.printf("%-5s%-10s%-23s%-10s%-10s%-10s%-10s%-15s%-10s%-15s%-15s%-10s%n",cols[0],cols[1],cols[2],cols[3],cols[4],cols[5],cols[6],cols[7],cols[8],cols[9],cols[10],cols[11]);
//		while(li.hasNext()) {
//			Student sd = li.next();
//			System.out.printf("%-5s%-10s%-23s%-10s%-10s%-10s%-10s%-15s%-10s%-15s%-15s%-10s%n",sd.getId(),sd.getName(),sd.getEmail(),sd.getCourse(),sd.getFee(),sd.getPaid(),sd.getDue(),sd.getAddress(),sd.getCity(),sd.getState(),sd.getCountry(),sd.getContactno());
//		}
//		System.out.println("\n");
//	AccountantSection.main(new String[] {});
	}
	
	public static void getStudentById(int id) {
		ArrayList<Student> students = new ArrayList<>();
//		System.out.println("\n---------- Search Student Details -----------\n");
//		System.out.println("Enter Student ID to edit details: ");
//		int id=sc.nextInt();
		Student s = new Student();

		try {
			con = AccountantDao.getCon();
			pt = con.prepareStatement("select * from Student where ID=?");
			pt.setInt(1, id);
			rs=pt.executeQuery();
			while(rs.next()) {
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setEmail(rs.getString(3));
				s.setCourse(rs.getString(4));
				s.setFee(rs.getInt(5));
				s.setPaid(rs.getInt(6));
				s.setDue(rs.getInt(7));
				s.setAddress(rs.getString(8));
				s.setCity(rs.getString(9));
				s.setState(rs.getString(10));
				s.setCountry(rs.getString(11));
				s.setContactno(rs.getString(12));
				students.add(s);
				//con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		FeeReport.printData(students, s);

		
//		System.out.println("\n------------ Update Student Details ------------\n");
//		Student s1 = FeeReport.getStudentData();
//		s1.setId(id);
//		
//		editStudent(s1);
	}
	public static int editStudent(Student s1) {
		int status = 0;
		try {
			con = AccountantDao.getCon();
	//		pt1 = con.prepareStatement("select * f)
			pt = con.prepareStatement("update Student set Name=?,Email=?,Course=?,Fee=?,Paid=?,Due=?,Address=?,City=?,State=?,Country=?,Contactno=? where ID=?");
				pt.setString(1,s1.getName());
				pt.setString(2,s1.getEmail());
				pt.setString(3, s1.getCourse());
				pt.setInt(4,s1.getFee());
				pt.setInt(5,s1.getPaid());
				pt.setInt(6,s1.getDue());
				pt.setString(7,s1.getAddress());
				pt.setString(8,s1.getCity());
				pt.setString(9,s1.getState());
				pt.setString(10,s1.getCountry());
				pt.setString(11,s1.getContactno());
				pt.setInt(12,s1.getId());
				status=pt.executeUpdate();
				con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
//		if(status>0) 
//			System.out.println(s1.getName()+"'s data updated to the database successfully...\n");
//		
//		else 
//			System.out.println("Unable to update "+s1.getName()+"'s data to the database! \n");
//		AccountantSection.main(new String[] {});
		return status;
	}
	
	public static void getDueList() {
		ArrayList<Student> students = new ArrayList<>();
		int status =0;
		Student s = null;
		try {
			con = AccountantDao.getCon();
			pt = con.prepareStatement("select * from Student where Due>0");
			rs = pt.executeQuery();
			while(rs.next()) {
				s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setEmail(rs.getString(3));
				s.setCourse(rs.getString(4));
				s.setFee(rs.getInt(5));
				s.setPaid(rs.getInt(6));
				s.setDue(rs.getInt(7));
				s.setAddress(rs.getString(8));
				s.setCity(rs.getString(9));
				s.setState(rs.getString(10));
				s.setCountry(rs.getString(11));
				s.setContactno(rs.getString(12));
				students.add(s);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		FeeReport.printData(students,s);
	}
	
	public static int removeStudent(int id) {
		int status=0;
//		System.out.println("\n------------ Remove Student ------------\n");
//		System.out.println("Enter Student ID to remove details: ");
//		int id = sc.nextInt();
		try {
			con = AccountantDao.getCon();
			pt = con.prepareStatement("delete from Student where ID=?");
			pt.setInt(1, id);
			status =pt.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		return status;
//		if(status>0) 
//			System.out.println("\nRemoved Successfully...\n");
//		else
//			System.out.println("\nFailed to remove Student!\n");
//		AccountantSection.main(new String[] {});

	}
	
//	public static void printData(ArrayList students, Student s) {
//	//	ArrayList<Student> students = new ArrayList<>();
//		ListIterator<Student> li = students.listIterator();
//			String[] cols = new String[]{"ID", "Name", "Email", "Course", "Fee", "Paid", "Due", "Address", "City", "State", "Country", "Contactno"};
//			System.out.println();
//			System.out.printf("%-5s%-10s%-23s%-10s%-10s%-10s%-10s%-15s%-10s%-15s%-15s%-10s%n",cols[0],cols[1],cols[2],cols[3],cols[4],cols[5],cols[6],cols[7],cols[8],cols[9],cols[10],cols[11]);
//			while(li.hasNext()) {
//				Student sd = li.next();
//				System.out.printf("%-5s%-10s%-23s%-10s%-10s%-10s%-10s%-15s%-10s%-15s%-15s%-10s%n",sd.getId(),sd.getName(),sd.getEmail(),sd.getCourse(),sd.getFee(),sd.getPaid(),sd.getDue(),sd.getAddress(),sd.getCity(),sd.getState(),sd.getCountry(),sd.getContactno());
//			}		
//	}
}
