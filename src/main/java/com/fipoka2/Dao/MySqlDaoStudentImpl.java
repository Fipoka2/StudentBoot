package com.fipoka2.Dao;

import com.fipoka2.Entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;



@Repository("mysql")
@Primary
public class MySqlDaoStudentImpl implements StudentDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class StudentRowMapper implements  RowMapper<Student>{


        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException
        {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setCourse(resultSet.getString("course"));
            return student;
        }
    }

    @Override
    public Collection<Student> getAllStudents()
    {
        //select all students sql
        final String sql = "SELECT id,name,course FROM student";
        List<Student>students = jdbcTemplate.query(sql, new StudentRowMapper());
        return students;
    }

    @Override
    public Student getStudentById(int id)
    {
       final String sql = "SELECT id,name,course FROM student WHERE id = ?";
       Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(),id);
       return  student;
    }

    @Override
    public void removeStudentById(int id)
    {
        final String sql = "DELETE FROM student WHERE id = ?";
        jdbcTemplate.update(sql,id);

    }

    @Override
    public void updateStudent(Student student)
    {
        final String sql = "UPDATE student SET name = ?,course = ? WHERE id = ?";
        final int id = student.getId();
        final String name = student.getName();
        final String course = student.getCourse();
        jdbcTemplate.update(sql,new Object[] {name,course,id});
    }

    @Override
    public void insertStudentToDb(Student student)
    {
        final String sql = "INSERT INTO student (name,course) VALUES (?,?)";
        final String name = student.getName();
        final String course = student.getCourse();
        jdbcTemplate.update(sql,new Object[] {name,course});
    }

    @Override
    public Collection<String> getStudentsByCourse(String course)
    {
        final String sql = "SELECT name FROM student WHERE course = ?";
        Collection<String>students = jdbcTemplate.query(sql, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException
            {
                String name = resultSet.getString("name");
                return name;
            }
        },course);
        return students;
    }
}
