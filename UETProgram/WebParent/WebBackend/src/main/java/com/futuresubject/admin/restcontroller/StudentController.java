package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.StudentDto;
import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.service.ClassroomService;
import com.futuresubject.admin.service.StudentService;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    ClassroomService classroomService;

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

    @GetMapping("/students/new")
    public StudentDto createStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setListOfClassroom(classroomService.listOfClassroom());
        return studentDto;
    }

    @PostMapping("/students/new/created")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Student createSubject(@RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }
}
