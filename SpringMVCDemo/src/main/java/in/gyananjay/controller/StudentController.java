package in.gyananjay.controller;

import in.gyananjay.entity.Student;
import in.gyananjay.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student studentReq) {
        Student studentResp = studentService.craeteStudent(studentReq);
        return ResponseEntity.ok(studentResp);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id){
        Student studentResp = studentService.getStudentById(id);

        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentResp = studentService.getAllStudents();
        return ResponseEntity.ok(studentResp);
    }
}
