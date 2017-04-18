package com.fipoka2.Controller;

import com.fipoka2.Entity.Student;
import com.fipoka2.Service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id){
        return studentService.getStudentByID(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteStudentById(@PathVariable("id") int id){
            studentService.removeStudentById(id);
            return "deleted";
    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
        return "updated";
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String insertStudent(@RequestBody Student student){
        studentService.insertStudent(student);
        return "inserted";
    }

    @RequestMapping(value = "/byCourse/{name}",method = RequestMethod.GET)
    public Collection<String> getStudentByCourse(@PathVariable String name){
        return studentService.getStudentsByCourse(name);
    }

}
