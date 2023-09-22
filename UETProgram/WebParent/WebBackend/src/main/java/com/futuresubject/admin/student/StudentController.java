package com.futuresubject.admin.student;

import com.futuresubject.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> studentList = studentService.listAll();
        model.addAttribute("studentList", studentList);
        return "";
    }

    @GetMapping("/student/{mssv}")
    public String studentAccount(@PathVariable(name = "mssv") String mssv,
                                 Model model) {
        Student student = null;
        try {
            student = studentService.get(mssv);
//            Set<Course> listCourse = student.getListCourse();
//            model.addAttribute("listCourse", listCourse);
//            model.addAttribute("student", student);
            return "";
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/student/{mssv}/{nameCourse}")
    public String studySubjectList(@PathVariable(name = "mssv") String mssv,
                                   Model model,
                                   @PathVariable(name = "nameCourse") String nameCourse) {
        return "";

    }

    @GetMapping("/student/{mssv}/{nameCourse}?=")

    public String getAllThisTypeSubject(@PathVariable(name = "mssv") String mssv,
                                        Model model,
                                        @PathVariable(name = "nameCourse") String nameCourse,
                                        @Param("typeSubject") String typeSubject) {
        return "";
    }
}
