package com.futuresubject.admin.restcontroller;


import com.futuresubject.admin.dto.FacultyDto;
import com.futuresubject.admin.service.FacultyService;
import com.futuresubject.common.entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/faculties/new")
    public FacultyDto createFaculty() {
        return new FacultyDto();
    }
    @PostMapping("/faculties/new/created")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Faculty saveFaculty(@RequestBody FacultyDto facultyDto) {
        return facultyService.save(facultyDto);
    }
}
