package com.example.demo.service;

//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import jakarta.annotation.PostConstruct;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    //private List<Student> students = new ArrayList<>(Arrays.asList());
    private AtomicLong IdGenerator = new AtomicLong();

    @PostConstruct
    public void unit(){}
    public List<Student> getAll(){
        return studentRepository.findAll();
    }
    public List<Student> getAllByName(String name){
        return studentRepository.findAllByName(name);
    }
    public Student create(Student student){
        return studentRepository.save(student);
    }
    public Student getById(Long id) {//true
        return studentRepository.findById(id).orElse(null);
            
    }
    public Student update(Long id, Student student) {//true
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setName(student.getName());
            existingStudent.setGroupName(student.getGroupName());
            existingStudent.setRecentEntries(student.getRecentEntries());
            return studentRepository.save(existingStudent);
        }).orElse(null);
    }
    public boolean deleteById(Long id) {//true
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
