package com.feeReport;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import com.Accountant.Accountant;
import com.Accountant.AccountantDao;
import com.Accountant.AccountantDaoImpl;
import com.Accountant.AccountantDaoImpl_file;
import com.Accountant.AccountantLogin;
import com.Accountant.AccountantSection;
import com.Admin.AdminLogin;
import com.Admin.AdminSection;
import com.Student.Student;
import com.Student.StudentDao;
import com.Student.StudentDaoImpl;
import com.Student.StudentDaoImpl_file;

public class FeeReport {
	public static int ans = 0;
	static Scanner sc = new Scanner(System.in);
	AccountantDao obj=new AccountantDaoImpl();
	AccountantDao objf=new AccountantDaoImpl_file();
	StudentDao sobj = new StudentDaoImpl();
	StudentDao sobjf = new StudentDaoImpl_file();
	Accountant a = new Accountant();
	Student s = new Student();


	public static void main(String[] args) {
		FeeReport obj = new FeeReport();
		int ch;
		System.out.println("\nSelect an option to do operations on file or database...\n");
		System.out.println("1. File System\n2. Database System");
		ans = sc.nextInt();

		if (ans == 1 || ans == 2)
			System.out.println("\nOpening Fee Report...");
		else
			System.out.println();

		System.out.println("\n\n=============== Fee Report ==============\n");
		System.out.println("1. Admin Login\n2. Accountant Login\n3. Exit");
		ch = sc.nextInt();
		switch (ch) {
		case 1:
			obj.checkAdmin();
			// AdminLogin.main(new String[] {});
			break;
		case 2:
			obj.checkAccountant();
			break;
		case 3:
			System.exit(0);
		default:
			System.out.println("Please enter 1 for Admin Login or 2 for Accountant Login");
		}
	}

	// Changes starts here
	public void checkAdmin() {
		String uname, pwd;
		System.out.println("----------- Admin Login ----------\n");
		System.out.println("Admin Username: ");
		uname = sc.next();
		System.out.println("Admin Password: ");
		pwd = sc.next();
		System.out.println();
		if (uname.equals("admin") && pwd.equals("admin123")) {
			System.out.println("Logged in Successfully");
			adminSection();
		} else {
			System.out.println("Admin Username or Password is wrong, Try Again!\n");
			FeeReport.main(new String[] {});
		}
	}

	public void checkAccountant() {
		boolean s;
		String uname, pwd;
		System.out.println("----------- Accountant Login ----------\n");
		System.out.println("Accountant Username: ");
		uname=sc.next();
		System.out.println("Accountant Password: ");
		pwd=sc.next();
		System.out.println();
		s= obj.accountantLogin(uname, pwd);
		if(s==true) {
			System.out.println("Logged in successfully...\n****** Welcome "+uname+" *******\n");
			accountantSection();
		}else {
			System.out.println("Incorrect Username or Password, Try again");
			FeeReport.main(new String[] {});
		}
	}
	public void adminSection() {
		int id = 1;
		boolean s;
		String name, pwd, email, phone;
		ArrayList<Accountant> accountants = new ArrayList<>();
		System.out.println("============ Admin Section ============");
		System.out.println("1. Add Accountant\n2. View Accountant List\n3. Logout\n");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			System.out.println("---------- Add Accountant ---------");
			System.out.println("Enter UserName: ");
			name = sc.next();
			System.out.println("Enter Password: ");
			pwd = sc.next();
			System.out.println("Enter Email: ");
			email = sc.next();
			System.out.println("Enter Contact No: ");
			phone = sc.next();
		//	Accountant a = new Accountant(id, name, pwd, email, phone);
			a.setName(name);
			a.setPwd(pwd);
			a.setEmail(email);
			a.setPhone(phone);
			if (ans == 1)
				s = objf.addAccountant(a);
			else
				s = obj.addAccountant(a);

			if (s==true)
				System.out.println(name + "'s data is added to the database successfully...\n");
			else
				System.out.println("Unable to add this data to the database!\n");
			adminSection();
			break;
		case 2:
			System.out.println("---------- Accountant Details ----------");
			if(ans == 1)
				accountants = objf.viewAccountant();
			else
				accountants = obj.viewAccountant();

			ListIterator<Accountant> itr = accountants.listIterator();
			while (itr.hasNext()) {
				Accountant accdetails = itr.next();
				System.out.println("\nID: " + accdetails.getId() + "\nName: " + accdetails.getName() + "\nPassword: "
						+ accdetails.getPwd() + "\nEmail: " + accdetails.getEmail() + "\nContact No: "
						+ accdetails.getPhone() + "\n");
			}
			adminSection();
			break;
		case 3:
			FeeReport.main(new String[] {});
		}
	}

	public void accountantSection() {
		int ch;
		boolean b;
		ArrayList<Student> students = new ArrayList<>();
		System.out.println("========== Accountant Section =========");
		System.out.println("\n1. Add Student\n2. View Students\n3. Edit Student\n4. Due Fee List\n5. Remove Student\n6. Logout\n");
		ch = sc.nextInt();
		switch (ch) {
		case 1:		System.out.println("\n------------ Add Student ------------\n");
					s=getStudentData();
			if(ans == 1) 
				b = sobjf.addStudent(s); 
			else
				b = sobj.addStudent(s);
			if (b == true)
				System.out.println("Students data added to the database successfully...\n");
			else
				System.out.println("Unable to add Student's data to the database! \n");
			accountantSection();
			break;

		case 2:
			if(ans == 1) 
				students = sobjf.viewStudent(); 
			else
				students = sobj.viewStudent();
			System.out.println("\n");
			System.out.println("\n-------------- Student List --------------\n");
			printData(students);
			System.out.println();
			accountantSection();
			break;

		case 3:// ArrayList<Student> students = new ArrayList<>();
			System.out.println("\n---------- Search Student Details -----------\n");
			System.out.println("Enter Student ID to edit details: ");
			int id = sc.nextInt();
			s = sobjf.getStudentById(id);
			Student s1 = new Student();
			if(ans == 1) {
				Student s = getStudentData();
			System.out.println("\n------------ Update Student Details ------------\n");
		//	Student s1 = getStudentData();
			s1.setId(id);
			b = sobjf.editStudent(s1);
			}
			else {
				s = sobj.getStudentById(id);
				System.out.println("\n------------ Update Student Details ------------\n");
			//	Student s1 = getStudentData();
				s1.setId(id);
				b = sobj.editStudent(s1);
			}
			
			if (b == true)
				System.out.println(s1.getName() + "'s data updated to the database successfully...\n");

			else
				System.out.println("Unable to update " + s1.getName() + "'s data to the database! \n");
			accountantSection();
			break;

		case 4:
			if(ans == 1) {
				students = sobjf.getDueList();
				printData(students);
			}
			else {
				students = sobj.getDueList();
				printData(students);
			}
			accountantSection();
			break;

		case 5:
			System.out.println("\n------------ Remove Student ------------\n");
			System.out.println("Enter Student ID to remove details: ");
			int id1 = sc.nextInt();
			if(ans == 1) {
				b = sobjf.removeStudent(id1);
			}else {
				b = sobj.removeStudent(id1);
			}
			
			if(b == true) 
				System.out.println("\nRemoved Successfully...\n");
			else
				System.out.println("\nFailed to remove Student!\n");
			accountantSection();
			break;
		case 6:
			FeeReport.main(new String[] {});
			break;
		default:
			System.out.println("Sorry, Invalid choice!");
		}
	}

	public static Student getStudentData() {
		int id = 0, due, paid, fee;
		String name, email, course, address, city, state, country, phoneno;
		System.out.println("Name: ");
		name = sc.next();
		System.out.println("Email: ");
		email = sc.next();
		System.out.println("Course: ");
		course = sc.next();
		System.out.println("Fee: ");
		fee = sc.nextInt();
		System.out.println("Paid: ");
		paid = sc.nextInt();
		System.out.println("Due: ");
		due = fee - paid;
		sc.nextLine();
		System.out.println(due);
		System.out.println("Address: ");
		address = sc.nextLine();
		System.out.println("City: ");
		city = sc.next();
		System.out.println("State: ");
		state = sc.next();
		System.out.println("Country: ");
		country = sc.next();
		System.out.println("Contact No: ");
		phoneno = sc.next();
		Student s = new Student(id, name, email, course, fee, paid, due, address, city, state, country, phoneno);
		// addStudent(s);
		return s;
	}

	public static void printData(ArrayList students) {
		// ArrayList<Student> students = new ArrayList<>();
		ListIterator<Student> li = students.listIterator();
		String[] cols = new String[] { "ID", "Name", "Email", "Course", "Fee", "Paid", "Due", "Address", "City",
				"State", "Country", "Contactno" };
		System.out.println();
		System.out.printf("%-5s%-10s%-23s%-10s%-10s%-10s%-10s%-15s%-10s%-15s%-15s%-10s%n", cols[0], cols[1], cols[2],
				cols[3], cols[4], cols[5], cols[6], cols[7], cols[8], cols[9], cols[10], cols[11]);
		while (li.hasNext()) {
			Student sd = li.next();
			System.out.printf("%-5s%-10s%-23s%-10s%-10s%-10s%-10s%-15s%-10s%-15s%-15s%-10s%n", sd.getId(), sd.getName(),
					sd.getEmail(), sd.getCourse(), sd.getFee(), sd.getPaid(), sd.getDue(), sd.getAddress(),
					sd.getCity(), sd.getState(), sd.getCountry(), sd.getContactno());
		}
	}

}
