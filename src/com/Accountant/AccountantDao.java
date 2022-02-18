package com.Accountant;

import java.sql.Connection;
import java.util.ArrayList;


public interface AccountantDao {
	public Connection getCon();
	public int addAccountant(Accountant a);
	public ArrayList viewAccountant();
	public boolean accountantLogin(String uname, String pwd);

}
