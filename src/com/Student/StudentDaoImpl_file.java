package com.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;

public class StudentDaoImpl_file implements StudentDao{
	Student s = new Student();
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	ListIterator li = null;
	File file = new File("stud.ser");
	ArrayList<Student> students = new ArrayList<>();
	boolean b = false;

	@SuppressWarnings("unchecked")
	@Override
	public boolean addStudent(Student s) {
		int id;
		try {
			if(!file.exists())
				file.createNewFile();
			students.clear();
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
			ois.close();
			students.add(s);
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(students);
			oos.close();
			b = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Student> viewStudent() {
		try {
			students.clear();
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
		} catch (Exception e) {
			System.out.println(e);
		}
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getStudentById(int id) {
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
			ListIterator<Student> li = students.listIterator();
			while(li.hasNext()) {
				Student s1 = (Student)li.next();
				if(s1.getId() == id) {
					s = s1;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return s;
	}

	@Override
	public boolean editStudent(Student s1) {
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
			for(Student s:students) {
				if(s.getId() == s1.getId()) {
					s.setName(s1.getName());
					s.setEmail(s1.getEmail());
					s.setCourse(s1.getCourse());
					s.setFee(s1.getFee());
					s.setPaid(s1.getPaid());
					s.setDue(s1.getDue());
					s.setAddress(s1.getAddress());
					s.setCity(s1.getCity());
					s.setState(s1.getState());
					s.setCountry(s1.getCountry());
					s.setContactno(s1.getContactno());
					b = true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Student> getDueList() {
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
	
		} catch (Exception e) {
		}
		return students;
	}

	@Override
	public boolean removeStudent(int id) {
		
		return false;
	}

}
