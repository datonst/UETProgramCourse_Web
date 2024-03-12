package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.Faculty_ProgramDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.service.FacultyService;
import com.futuresubject.admin.service.Faculty_ProgramService;
import com.futuresubject.admin.service.ProgramService;
import com.futuresubject.common.entity.JoinTable.Faculty_Program;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Faculty_ProgramController {
    @Autowired
    private Faculty_ProgramService facultyProgramService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private ProgramService programService;

    @GetMapping("/facultyprograms/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public Faculty_ProgramDto createFacultyProgram() {
        Faculty_ProgramDto facultyProgramDto = new Faculty_ProgramDto();
        facultyProgramDto.setListOfFacultyName(facultyService.listOfFacultyName());
        facultyProgramDto.setListOfProgramFullCode(programService.listOfProgramFullCode());
        return facultyProgramDto;
    }

    @PostMapping("/facultyprograms/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"ROLE_ADMIN"})
    public Faculty_Program saveFacultyProgram(@RequestBody Faculty_ProgramDto facultyProgramDto) throws NotFoundDataExeption {
//        if (facultyProgramDto.getProgramFullCode() == null
//        || facultyProgramDto.getFacultyName() == null) {
//            throw new NotFoundDataExeption("Not found - contain Null");
//        }
        return facultyProgramService.insert(facultyProgramDto);
    }
}
