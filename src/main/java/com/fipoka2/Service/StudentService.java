package com.fipoka2.Service;

import com.fipoka2.Dao.StudentDao;
import com.fipoka2.Entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Qualifier("mysql")
public class StudentService
{
    @Autowired
    private StudentDao studentDao;

    public Collection<Student> getAllStudents(){
        return studentDao.getAllStudents();
    }

    public Student getStudentByID(int id){
        return this.studentDao.getStudentById(id);
    }

    public void removeStudentById(int id)
    {
        this.studentDao.removeStudentById(id);
    }
    public void updateStudent(Student student){
        this.studentDao.updateStudent(student);

    }

    public void insertStudent(Student student)
    {
        studentDao.insertStudentToDb(student);
    }

    public Collection<String> getStudentsByCourse(String course){
        return this.studentDao.getStudentsByCourse(course);
    }
}
