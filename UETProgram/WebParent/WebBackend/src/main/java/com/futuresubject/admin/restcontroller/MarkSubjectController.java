package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.MarkSubjectDto;
import com.futuresubject.admin.service.MarkSubjectService;
import com.futuresubject.admin.service.StudentService;
import com.futuresubject.admin.service.SubjectService;
import com.futuresubject.common.entity.MarkSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarkSubjectController {
    @Autowired
    MarkSubjectService markSubjectService;
    @Autowired
    StudentService studentService;
    @Autowired
    SubjectService subjectService;

    @GetMapping("/marksubjects/new")
    public MarkSubjectDto createMarkSubject() {
        MarkSubjectDto markSubjectDto = new MarkSubjectDto();
        markSubjectDto.setListOfStudentId(studentService.listOfStudentId());
        markSubjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
        return markSubjectDto;
    }

    @PostMapping("/marksubjects/new/created")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public String saveMarkSubject(@RequestBody MarkSubjectDto markSubjectDto) {
        markSubjectService.save(markSubjectDto);
        return "SUCCESS";

    }
}
