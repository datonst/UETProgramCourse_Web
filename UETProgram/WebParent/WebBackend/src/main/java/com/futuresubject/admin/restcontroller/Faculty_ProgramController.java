package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.FacultyDto;
import com.futuresubject.admin.dto.Faculty_ProgramDto;
import com.futuresubject.admin.service.FacultyService;
import com.futuresubject.admin.service.Faculty_ProgramService;
import com.futuresubject.admin.service.ProgramService;
import com.futuresubject.common.entity.Faculty;
import com.futuresubject.common.entity.Faculty_Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Faculty_ProgramController {
    @Autowired
    private Faculty_ProgramService facultyProgramService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private ProgramService programService;

    @GetMapping("/facultyprograms/new")
    public Faculty_ProgramDto createFacultyProgram() {
        Faculty_ProgramDto facultyProgramDto = new Faculty_ProgramDto();
        facultyProgramDto.setListOfFacultyName(facultyService.listOfFacultyName());
        facultyProgramDto.setListOfProgramFullCode(programService.listOfProgramFullCode());
        return facultyProgramDto;
    }

    @PostMapping("/facultyprograms/new/created")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Faculty_Program saveFacultyProgram(@RequestBody Faculty_ProgramDto facultyProgramDto) {
        return facultyProgramService.save(facultyProgramDto);
    }
}
