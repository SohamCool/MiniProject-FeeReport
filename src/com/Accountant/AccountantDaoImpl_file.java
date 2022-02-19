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
	File file = new File("acc.ser");

	ArrayList<Accountant> accountants = new ArrayList<Accountant>();
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAccountant(Accountant a) {
		boolean status=false;

		try {
			if(file.exists()==false)
				file.createNewFile();
			accountants.clear();
			ois = new ObjectInputStream(new FileInputStream(file));
			accountants = (ArrayList<Accountant>)ois.readObject();
			ois.close();
			accountants.add(a);
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(accountants);
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
			System.out.println(e);
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Accountant> viewAccountant() {		
				try {
					accountants.clear();	
					ois = new ObjectInputStream(new FileInputStream(file));
					accountants = (ArrayList<Accountant>)ois.readObject();
					ois.close();
				} catch (Exception e) {
					System.out.println(e);
				}
				return accountants;
	}

}
