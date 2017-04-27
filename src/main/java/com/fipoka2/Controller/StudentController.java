package com.fipoka2.Controller;

import com.fipoka2.Entity.Student;
import com.fipoka2.Service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
        System.out.println(getName());
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id){
        return studentService.getStudentByID(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)

    public String deleteStudentById(@PathVariable("id") int id){
            studentService.removeStudentById(id);
            return "1111";
    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
        return "updated";
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertStudent(@RequestBody Student student){
        studentService.insertStudent(student);
        return new ResponseEntity<>("it is ok",HttpStatus.OK);
    }
    @RequestMapping(value = "/byCourse/{username}",method = RequestMethod.GET)
    @PreAuthorize("authentication.name == #name " )
    public Collection<String> getStudentByCourse(@PathVariable("username") String name){
        return studentService.getStudentsByCourse(name);
    }


    private String getName()
    {
        return (SecurityContextHolder.getContext().getAuthentication()).getName();
    }

}
