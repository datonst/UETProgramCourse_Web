package com.futuresubject.admin.restcontroller;


import com.futuresubject.admin.dto.FacultyDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.service.FacultyService;
import com.futuresubject.common.entity.Faculty;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FacultyController {
    @Autowired
    private FacultyService facultyService;
    @GetMapping("/faculties")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public List<FacultyDto> getAllFaculty() {
        return facultyService.findAll();
    }
    @GetMapping("/faculties/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public FacultyDto createFaculty() {
        return new FacultyDto();
    }

    @PostMapping("/faculties/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"ROLE_ADMIN"})
    public Faculty saveFaculty(@RequestBody FacultyDto facultyDto) throws NotFoundDataExeption {
//        if (facultyDto.getFacultyName() == null) {
//            throw new NotFoundDataExeption("Not found - contain null");
//        }
        return facultyService.insert(facultyDto);
    }
}
