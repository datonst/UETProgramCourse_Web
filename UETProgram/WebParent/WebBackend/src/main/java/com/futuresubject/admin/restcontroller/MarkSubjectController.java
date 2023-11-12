package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.MarkSubjectDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.StudentDto;
import com.futuresubject.admin.service.MarkSubjectService;
import com.futuresubject.admin.service.StudentService;
import com.futuresubject.admin.service.SubjectService;
import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class MarkSubjectController {

    @Autowired
    MarkSubjectService markSubjectService;

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;


    @GetMapping("/marksubjects")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<MarkSubjectDto> getAllMarkSubject() {
        return markSubjectService.getAllMarkSubject();
    }

    @GetMapping("/marksubjects/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public MarkSubjectDto createMarkSubject() {
        MarkSubjectDto markSubjectDto = new MarkSubjectDto();
        markSubjectDto.setListOfStudentId(studentService.listOfStudentId());
        markSubjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
        return markSubjectDto;
    }


    @PostMapping("/marksubjects/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public MarkSubject saveMarkSubject(@RequestBody MarkSubjectDto markSubjectDto) throws NotFoundDataExeption {
//        if (markSubjectDto.getSubjectId() == null ||
//        markSubjectDto.getStudentId() == null ||
//        markSubjectDto.getMark() == null) {
//            throw new NotFoundDataExeption("Not found - mark subject contain null");
//        }
        return markSubjectService.insert(markSubjectDto);
    }

    @GetMapping("/marksubjects/edit/{element}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public MarkSubjectDto getEditMark(@PathVariable(name = "element")
                                          String element) throws NotFoundDataExeption {
        String[] arrOfStr = element.split("&", 2);
        String studentId = arrOfStr[0];
        String subjectId = arrOfStr[1];
        MarkSubjectDto markSubjectDto =  markSubjectService.get(studentId,subjectId);
        markSubjectDto.setListOfStudentId(studentService.listOfStudentId());
        markSubjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
        return markSubjectDto;
    }

    @PutMapping("/marksubjects/edit/save")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public String putEditMark(@RequestBody MarkSubjectDto markSubjectDto) throws NotFoundDataExeption {
        String studentId = markSubjectDto.getStudentId();
        String subjectId = markSubjectDto.getSubjectId();
        Double mark = markSubjectDto.getMark();
        markSubjectService.updateMark(studentId,subjectId,mark);
        return "successs";
    }

    @DeleteMapping("/marksubjects/delete/{element}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEditStudent(@PathVariable(name = "element") String element) {
        String[] arrOfStr = element.split("&", 2);
        String studentid = arrOfStr[0];
        String subjectid = arrOfStr[1];
        markSubjectService.deleteMark(studentid,subjectid);
    }

}
