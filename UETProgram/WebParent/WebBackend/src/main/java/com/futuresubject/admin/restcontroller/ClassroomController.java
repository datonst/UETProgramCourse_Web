package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.ClassroomDto;
import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.service.ClassroomService;
import com.futuresubject.common.entity.Classroom;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/classrooms")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<ClassroomDto> getAllClass() {

        return classroomService.findAll();
    }

    @PostMapping("/classrooms/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Classroom saveClassroom(@RequestBody ClassroomDto classroomDto) {
        return classroomService.save(classroomDto);
    }

    @GetMapping("/classrooms/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ClassroomDto createClassroom() {
        return new ClassroomDto();
    }
}
