package com.Student;

import java.util.ArrayList;

public interface StudentDao {
	public boolean addStudent(Student s);
	public ArrayList<Student> viewStudent();
	public Student getStudentById(int id);
	public boolean editStudent(Student s);
	public ArrayList<Student> getDueList();
	public boolean removeStudent(int id);
}
