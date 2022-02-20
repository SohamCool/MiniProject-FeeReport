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
		int id = 0;
		try {
			if(!file.exists())
				file.createNewFile();
			students.clear();
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
			for(Student s1 : students) {
				id =s1.getId();
				id+=1;
			}
			ois.close();
			s.setId(id);
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
		Student s1 = new Student();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
			for(Student s: students) {
				if(s.getId() == id) {
					s1.setId(s.getId());
					s1.setName(s.getName());
					s1.setEmail(s.getEmail());
					s1.setCourse(s.getCourse());
					s1.setFee(s.getFee());
					s1.setPaid(s.getPaid());
					s1.setDue(s.getDue());
					s1.setAddress(s.getAddress());
					s1.setCity(s.getCity());
					s1.setState(s.getState());
					s1.setCountry(s.getCountry());
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return s1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean editStudent(Student s) {
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
			for(Student s1:students) {
				if(s1.getId() == s.getId()) {
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
		ArrayList<Student> li = new ArrayList<>();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
			for(Student s : students) {
				if(s.getDue()>0) {
					li.add(s);
				}
			}
		} catch (Exception e) {
		}
		return li;
	}

	@Override
	public boolean removeStudent(int id) {
		
		return false;
	}

}
