package in.gyananjay.service;

import in.gyananjay.entity.Student;
import in.gyananjay.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    public StudentRepository studentRepository;
    private final AtomicLong idCounter = new AtomicLong(1);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student craeteStudent(Student studentReq){
        if (studentReq.getId() == null) {
            studentReq.setId(idCounter.getAndIncrement());
        }
        return studentRepository.save(studentReq);
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}