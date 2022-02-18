package com.Accountant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class AccountantDaoImpl_file implements AccountantDao {

	Accountant a = new Accountant();
	Scanner sc = new Scanner(System.in);
	static ObjectOutputStream oos = null;
	static ObjectInputStream ois = null;
	ListIterator li = null;
	
	//public int addAccountant(Accountant a) {
//		ArrayList<Accountant> accountants = new ArrayList<>();
//		int status = 0;
//		try {
//			accountants.add(a);
//			File f = new File("Accountant.txt");
//			if(!f.exists()) 
//				f.createNewFile();
//			
//			oos = new ObjectOutputStream(new FileOutputStream("Accountant.txt"));
//			oos.writeObject(accountants);
//			++status;
//			oos.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return status;
	//}

	@Override
	public Connection getCon() {
		// TODO Auto-generated method stub
		return null;
	}

	public int addAcoountant(Accountant a) {
		ArrayList<Accountant> accountants = new ArrayList<>();
		int status = 0;
		try {
			accountants.add(a);
			File f = new File("Accountant.txt");
			if(!f.exists()) 
				f.createNewFile();
			
			oos = new ObjectOutputStream(new FileOutputStream("Accountant.txt"));
			oos.writeObject(accountants);
			status=1;
			oos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public ArrayList viewAccountant() {
		ArrayList<Accountant> accountants = new ArrayList<>();

		try {
			ois = new ObjectInputStream(new FileInputStream("Accountant.txt"));
			accountants = (ArrayList<Accountant>)ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return accountants;
	}

	@Override
	public int addAccountant(Accountant a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean accountantLogin(String uname, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
