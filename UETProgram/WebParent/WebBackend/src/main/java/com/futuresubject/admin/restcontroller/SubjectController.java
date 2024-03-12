package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.service.SubjectService;
import com.futuresubject.common.entity.Entity.Subject;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @GetMapping("/subjects")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_VIEWER","ROLE_ADMIN"})
//    @RolesAllowed({"ROLE_VIEWER", "ROLE_ADMIN"})
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<SubjectDto> listSubjects(
            @RequestParam(value = "page", defaultValue = "0")  int page
            , @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Pageable pagination = PageRequest.of(page, size);
        return subjectService.findAll(pagination);
    }

    @GetMapping("/subjects/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public SubjectDto subjectsDto() {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
        if (subjectDto.getPrerequisiteSubjectId()==null) {
            subjectDto.setPrerequisiteSubjectId(new ArrayList<>());
        }
        return subjectDto;
    }

    @PostMapping("/subjects/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"ROLE_ADMIN"})
    public Subject createSubject(@RequestBody SubjectDto subjectDto) throws NotFoundDataExeption {
//        if (subjectDto.getSubjectid() == null) {
//            throw new NotFoundDataExeption("Not found - subject contains null");
//        }
        return subjectService.insert(subjectDto);
    }


    @GetMapping("/subjects/edit/{subjectid}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public SubjectDto editSubject(@PathVariable(name = "subjectid") String subjectid) {
        try {
            SubjectDto subjectDto = subjectService.get(subjectid);
            subjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
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
    @RolesAllowed({"ROLE_ADMIN"})
    public void putEditSubject(@RequestBody SubjectDto subjectDto) {
        subjectService.updateFromDto(subjectDto);
    }

    @DeleteMapping("/subjects/delete/{subjectid}")
    @RolesAllowed({"ROLE_ADMIN"})
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEditSubject(@PathVariable(name = "subjectid") String subjectid) {
            subjectService.deleteBySubjectid(subjectid);
    }
}
