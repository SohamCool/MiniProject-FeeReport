package com.Accountant;

import java.sql.Connection;
import java.util.ArrayList;


public interface AccountantDao {
	public Connection getCon();
	public boolean addAccountant(Accountant a);
	public ArrayList<Accountant> viewAccountant();
	public boolean accountantLogin(String uname, String pwd);

}
