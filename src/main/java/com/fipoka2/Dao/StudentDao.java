package com.fipoka2.Dao;

import com.fipoka2.Entity.Student;

import java.util.Collection;

/**
 * Created by Dmitry on 16.04.2017.
 */
public interface StudentDao
{
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentById(int id);

    void updateStudent(Student student);

    void insertStudentToDb(Student student);

    Collection<String> getStudentsByCourse(String course);
}
