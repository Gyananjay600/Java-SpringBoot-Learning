package in.gyananjay.controller;

import in.gyananjay.entity.Student;
import in.gyananjay.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/view/students")
public class StudentViewController {

    private final StudentService studentService;

    public StudentViewController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; // resolves to /WEB-INF/views/students.jsp
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent"; // resolves to /WEB-INF/views/addStudent.jsp
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        studentService.craeteStudent(student);
        return "redirect:/view/students";
    }
}