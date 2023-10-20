package com.futuresubject.admin.restcontroller;
import com.futuresubject.admin.dto.ClassroomDto;
import com.futuresubject.admin.dto.ProgramDto;
import com.futuresubject.admin.service.FacultyService;
import com.futuresubject.admin.service.ProgramService;
import com.futuresubject.common.entity.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProgramController {
    @Autowired
    private ProgramService programService;
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/programs")
    public List<ProgramDto> getAllProgram() {
        return programService.findAll();
    }
    @PostMapping("/programs/new/created")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Program saveProgram(@RequestBody ProgramDto programDto) {
        return programService.save(programDto);
    }

    @GetMapping("/programs/new")
    public ProgramDto createProgram() {
        ProgramDto programDto = new ProgramDto();
        programDto.setListOfFacultyName(facultyService.listOfFacultyName());
        return programDto;
    }
}
