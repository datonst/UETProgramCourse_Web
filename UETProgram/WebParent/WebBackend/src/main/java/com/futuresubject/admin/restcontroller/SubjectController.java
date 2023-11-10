package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.service.SubjectService;
import com.futuresubject.common.entity.Enum.RoleType;
import com.futuresubject.common.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    @GetMapping("/subjects/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public SubjectDto subjectsDto() {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
        subjectDto.setListRoleType(Arrays.asList(RoleType.values()));
        if (subjectDto.getPrerequisiteSubjectId()==null) {
            subjectDto.setPrerequisiteSubjectId(new ArrayList<>());
        }
        return subjectDto;
    }

    @PostMapping("/subjects/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Subject createSubject(@RequestBody SubjectDto subjectDto) throws NotFoundDataExeption {
//        if (subjectDto.getSubjectid() == null) {
//            throw new NotFoundDataExeption("Not found - subject contains null");
//        }
        return subjectService.insert(subjectDto);
    }


    @GetMapping("/subjects/edit/{subjectid}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public SubjectDto editSubject(@PathVariable(name = "subjectid") String subjectid) {
        try {
            SubjectDto subjectDto = subjectService.get(subjectid);
            subjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
            subjectDto.setListRoleType(Arrays.asList(RoleType.values()));
            if (subjectDto.getPrerequisiteSubjectId()==null) {
                subjectDto.setPrerequisiteSubjectId(new ArrayList<>());
            }
            return subjectDto;
        } catch (NotFoundDataExeption e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/subjects/edit/save")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public void putEditSubject(@RequestBody SubjectDto subjectDto) {
        subjectService.updateFromDto(subjectDto);
    }

    @DeleteMapping("/subjects/delete/{subjectid}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEditSubject(@PathVariable(name = "subjectid") String subjectid) {
            subjectService.deleteBySubjectid(subjectid);
    }
}
