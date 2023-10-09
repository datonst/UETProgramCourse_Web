package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.service.SubjectService;
import com.futuresubject.common.entity.RoleType;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @GetMapping("/subjects")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectDto> listStudents() {
        return subjectService.findAll();
    }

    @PostMapping("/subjects/new/created")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Subject createSubject(@RequestBody SubjectDto subjectDto) {
        return subjectService.save(subjectDto);
    }
    @GetMapping("/subjects/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public SubjectDto subjectsDto() {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
        return subjectDto;
    }
}
