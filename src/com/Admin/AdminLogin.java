package com.Admin;

import java.util.Scanner;

import com.feeReport.FeeReport;
public class AdminLogin {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String uname, pwd;
		System.out.println("----------- Admin Login ----------\n");
		System.out.println("Admin Username: ");
		uname=sc.next();
		System.out.println("Admin Password: ");
		pwd=sc.next();
		System.out.println();
		if(uname.equals("admin") && pwd.equals("admin123")) {
			System.out.println("Logged in Successfully");
			AdminSection.main(new String []{});
		}
		else {
			System.out.println("Admin Username or Password is wrong, Try Again!\n");
			FeeReport.main(new String[] {});
		}
	}

}
