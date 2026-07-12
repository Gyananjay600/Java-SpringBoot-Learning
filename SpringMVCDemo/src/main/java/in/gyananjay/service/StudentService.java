package in.gyananjay.service;

import in.gyananjay.entity.Student;
import in.gyananjay.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student craeteStudent(Student studentReq){
        return studentRepository.save(studentReq);
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
