package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId){
        return studentRepository.findById(studentId).get();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentNew = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentNew.isPresent())
            throw new IllegalStateException("email taken");
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists)
            throw new IllegalStateException("Student with id:" + studentId + " doesn't exists");

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Student student) {
        Student updatedStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new IllegalStateException("No Student found"));

        if (!student.getName().isEmpty() &&
                !Objects.equals(student.getName(), updatedStudent.getName()))
            updatedStudent.setName(student.getName());

        if (!student.getEmail().isEmpty() &&
                !Objects.equals(student.getEmail(), updatedStudent.getEmail())) {
            Optional<Student> studentByEmail =
                    studentRepository.findStudentByEmail(student.getEmail());
            if (studentByEmail.isPresent())
                throw new IllegalStateException("No Student found");
            updatedStudent.setEmail(student.getEmail());
        }

//        studentRepository.save(updatedStudent);
    }
}
