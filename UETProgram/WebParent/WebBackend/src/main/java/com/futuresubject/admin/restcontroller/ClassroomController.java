package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.ClassroomDto;
import com.futuresubject.admin.dto.MarkSubjectDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.service.ClassroomService;
import com.futuresubject.common.entity.Classroom;
import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Subject;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/classrooms")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public List<ClassroomDto> getAllClass() {

        return classroomService.findAll();
    }


    @GetMapping("/classrooms/listCohort")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public List<String> getAllCohort() {
        return classroomService.findAllCohort();
    }

    @PostMapping("/classrooms/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"ROLE_ADMIN"})
    public Classroom saveClassroom(@RequestBody ClassroomDto classroomDto) throws NotFoundDataExeption {
//        if (classroomDto.getCohort()==null || classroomDto.getNameClass() ==null) {
//            throw new NotFoundDataExeption("Not found - classroom is null");
//        }
        return classroomService.insert(classroomDto);
    }

    @GetMapping("/classrooms/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public ClassroomDto createClassroom() {
        return new ClassroomDto();
    }


    @DeleteMapping("/classrooms/delete/{element}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"ROLE_ADMIN"})
    public void deleteClassrooms(@PathVariable(name = "element") String element) throws NotFoundDataExeption {
        classroomService.deleteClassFullName(element);
    }

}
