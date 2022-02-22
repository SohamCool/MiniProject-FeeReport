package com.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

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
	


	
//	@SuppressWarnings("unchecked")
//	@Override
//	public boolean editStudent(Student s) {
//		boolean checked= true;
//		File tempfile = new File("stud1.ser");
//		Student s1 = new Student();
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile));
//			Scanner sc = new Scanner(file);
//			Scanner sc1 = new Scanner(tempfile);
//			while(sc.hasNext()) {
//				String currentLine = sc.nextLine();
//				int id =2;
//				String[] tokens = currentLine.split(" ");
//				if(Integer.valueOf(tokens[0])==id && checked) {
//					currentLine = tokens[0]+" "+s.getName()+" "+s.getEmail()+" "+s.getCourse()+" "+s.getFee()+" "+s.getPaid()+" "+s.getDue()+" "+s.getAddress()+" "+s.getCity()+" "+s.getState()+" "+s.getCountry()+" "+s.getContactno();
//				}
//				writer.write(currentLine + System.getProperty("line.Seperator"));
//				writer.close();
//				sc.close();
//				file.delete();
//				b = tempfile.renameTo(file);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return b;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean editStudent(Student s) {
		boolean checked= true;
		File tempfile = new File("stud1.ser");
		try {
			oos = new ObjectOutputStream(new FileOutputStream(tempfile));
			ois = new ObjectInputStream(new FileInputStream(file));
			students = (ArrayList<Student>)ois.readObject();
			
			for(Student s1 : students) {
				
				String[] data = new String[] { Integer.toString(s1.getId()), s1.getName(), s1.getEmail(), s1.getCourse(), Integer.toString(s1.getFee()), Integer.toString(s1.getPaid()), Integer.toString(s1.getDue()), s1.getAddress(), s1.getCity(),
						s1.getState(), s1.getCountry(), s1.getContactno() };
				for(int i=0;i<12;i++) {
					String currentLine = data[i];
					String[] tokens = currentLine.split(" ");
					if(Integer.valueOf(tokens[0])==s.getId() && checked) {
						currentLine = tokens[0]+" "+s.getName()+" "+s.getEmail()+" "+s.getCourse()+" "+s.getFee()+" "+s.getPaid()+" "+s.getDue()+" "+s.getAddress()+" "+s.getCity()+" "+s.getState()+" "+s.getCountry()+" "+s.getContactno();
					}
					oos.writeObject(currentLine + System.getProperty("line.Seperator"));
					oos.close();
					ois.close();
					file.delete();
					b = tempfile.renameTo(file);
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
