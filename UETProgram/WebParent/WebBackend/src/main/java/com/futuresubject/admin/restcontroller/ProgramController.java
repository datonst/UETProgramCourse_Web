package com.futuresubject.admin.restcontroller;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.ProgramDto;
import com.futuresubject.admin.service.FacultyService;
import com.futuresubject.admin.service.ProgramService;

import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.Program;
import com.futuresubject.common.entity.Enum.ProgramType;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class ProgramController {
    @Autowired
    private ProgramService programService;
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/programs")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN","ROLE_VIEWER"})
    public List<ProgramDto> getAllProgram() {
        return programService.findAll();
    }


    @PostMapping("/programs/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"ROLE_ADMIN"})
    public Program saveProgram(@RequestBody ProgramDto programDto) throws NotFoundDataExeption {
//        if (programDto == null ||programDto.getProgramCode() ==null||programDto.getProgramName()==null) {
//            throw new NotFoundDataExeption("Not found - program contain null");
//        }
        return programService.insert(programDto);


    }

    @GetMapping("/programs/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public ProgramDto createProgram() {
        ProgramDto programDto = new ProgramDto();
        programDto.setListOfFacultyName(facultyService.listOfFacultyName());
        programDto.setProgramTypeList(Arrays.asList(ProgramType.values()));
        programDto.setLevelLanguageList(Arrays.asList(LevelLanguage.values()));
        return programDto;
    }


    @GetMapping("/programs/edit/{programFullName}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public ProgramDto getProgram(@PathVariable(name = "programFullName") String programFullName) throws NotFoundDataExeption {
        ProgramDto programDto = programService.get(programFullName);
        programDto.setListOfFacultyName(facultyService.listOfFacultyName());
        programDto.setProgramTypeList(Arrays.asList(ProgramType.values()));
        programDto.setLevelLanguageList(Arrays.asList(LevelLanguage.values()));
        return programDto;
    }
    @PutMapping("/programs/edit/{programFullName}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"ROLE_ADMIN"})
    public Program putEditProgram(@PathVariable(name = "programFullName") String programFullName,
                                      @RequestBody ProgramDto programDto) {
        return programService.updateFromDto(programDto);
    }

    @DeleteMapping("/programs/delete/{programFullCode}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"ROLE_ADMIN"})
    public void deleteProgram(@PathVariable(name = "programFullCode") String programFullCode) {
        programService.deleteByProgramFullCode(programFullCode);
    }

}
