package com.Accountant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class AccountantDaoImpl_file implements AccountantDao {

	Accountant a = new Accountant();
	Scanner sc = new Scanner(System.in);
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ListIterator li = null;
	File file = new File("acc.txt");

	ArrayList<Accountant> accountants = new ArrayList<Accountant>();
	
	
	@Override
	public Connection getCon() {
		// TODO Auto-generated method stub
		
		return null;
	}
	
//	@Override
//	public int addAcoountant(Accountant a) {
		//ArrayList<Accountant> accountants = new ArrayList<>();
//		int status = 0;
//		System.out.println(a.getName());
//		File f = new File("Accountant.txt");
//		System.out.println(f.exists());

//		try {
//			oos = new ObjectOutputStream(new FileOutputStream("Accountant.txt"));
//			oos.writeObject(a);
//			accountants.add(a);
//		//	File f = new File("Accountant.txt");
////			if(!f.exists()) 
////				f.createNewFile();
//			System.out.println(f.exists());
//			oos = new ObjectOutputStream(new FileOutputStream("Accountant.txt"));
//			oos.writeObject(a);
//			System.out.println("Data added to file");
//			status=1;
//			oos.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return status;
//	}

//	public ArrayList viewAccountant() {
//		ArrayList<Accountant> accountants = new ArrayList<>();
//
//		try {
//			ois = new ObjectInputStream(new FileInputStream(file));
//			accountants = (ArrayList<Accountant>)ois.readObject();
//			ois.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return accountants;
//	}
//last option to change return type int to boolean
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAccountant(Accountant a) {
		boolean status=false;
//		System.out.println("inadd accountant");
//		System.out.println(a.getName());
		
		try {
			if(file.exists()==false)
				file.createNewFile();
			accountants.clear();
			ois = new ObjectInputStream(new FileInputStream(file));
			accountants = (ArrayList<Accountant>)ois.readObject();
			ois.close();
			accountants.add(a);
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(a);
			oos.close();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean accountantLogin(String uname, String pwd) {
		boolean s =false;
		String pass = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			accountants = (ArrayList<Accountant>)ois.readObject();
			ListIterator<Accountant> li = accountants.listIterator();
			while(li.hasNext()) {
				Accountant a = (Accountant)li.next();
				if(a.getName().equals(uname) && a.getPwd().equals(pwd)) {
					s=true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Accountant> viewAccountant() {
	//	ArrayList<Accountant> accountants = new ArrayList<Accountant>();
		
				try {
					accountants.clear();	
					ois = new ObjectInputStream(new FileInputStream(file));
					accountants = (ArrayList<Accountant>)ois.readObject();
//					a = (Accountant)ois.readObject();
					ois.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			//	return accountants;
				return accountants;
	}

//	public static void main(String[] args) {
//		AccountantDaoImpl_file fobj = new AccountantDaoImpl_file();
//		Accountant a = new Accountant();
//		a.setName("abc");
//		a.setEmail("abcgmail");
//		a.setPwd("abc");
//		a.setPhone("9898989898");
//		fobj.addAccountant(a);
//		System.out.println("end");
//	}		

}
