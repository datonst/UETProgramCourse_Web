package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.AttendanceDto;
import com.futuresubject.admin.dto.FacultyDto;
import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.ObtainCertDto;
import com.futuresubject.admin.service.ObtainCertService;

import com.futuresubject.common.entity.Enum.CertificateType;
import com.futuresubject.common.entity.Enum.LevelLanguage;
import com.futuresubject.common.entity.Faculty;
import com.futuresubject.common.entity.ObtainCert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ObtainCertController {
    @Autowired
    private ObtainCertService obtainCertService;
    @GetMapping("/obtainCert")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<ObtainCertDto> getAllFaculty() {

        return obtainCertService.findAllObtainCert();
    }
    @GetMapping("/obtainCert/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ObtainCertDto createFaculty() {
        ObtainCertDto obtainCertDto = new ObtainCertDto();
        obtainCertDto.setLevelLanguageList(Arrays.asList(LevelLanguage.values()));
        obtainCertDto.setCertificateTypeList(Arrays.asList(CertificateType.values()));
        return obtainCertDto;
    }

    @PostMapping("/obtainCert/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public ObtainCert saveFaculty(@RequestBody ObtainCertDto obtainCertDto) throws NotFoundDataExeption {
//        if (facultyDto.getFacultyName() == null) {
//            throw new NotFoundDataExeption("Not found - contain null");
//        }

        return obtainCertService.insert(obtainCertDto);
    }

    @GetMapping("/obtainCert/edit/{studentid}/{levelLanguage}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ObtainCertDto editObtainCert(@PathVariable(name = "studentid") String studentId,
                            @PathVariable(name = "levelLanguage") LevelLanguage levelLanguage){
        ObtainCertDto obtainCertDto = obtainCertService.getByStudentIdAndLevelLanguage(studentId,levelLanguage);
        obtainCertDto.setLevelLanguageList(Arrays.asList(LevelLanguage.values()));
        obtainCertDto.setCertificateTypeList(Arrays.asList(CertificateType.values()));
        return obtainCertDto;
    }

    @PutMapping("/obtainCert/edit/save")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putEditObtainCert(@RequestBody ObtainCertDto obtainCertDto) {
        obtainCertService.updateFromDto(obtainCertDto);
    }

    @DeleteMapping("/obtainCert/delete/{studentid}/{levelLanguage}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveFaculty(@PathVariable(name = "studentid") String studentId,
                                  @PathVariable(name = "levelLanguage") LevelLanguage levelLanguage){
        obtainCertService.deleteByStudentIdAndLevel(studentId,levelLanguage);
    }
}
