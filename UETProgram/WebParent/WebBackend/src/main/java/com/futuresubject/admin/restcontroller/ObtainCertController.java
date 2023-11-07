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
import com.futuresubject.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ObtainCertController {
    @Autowired
    private ObtainCertService obtainCertService;
    @GetMapping("/obtaincerts")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<ObtainCertDto> getAllFaculty() {

        return obtainCertService.findAllObtainCert();
    }
    @GetMapping("/obtaincerts/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ObtainCertDto createFaculty() {
        ObtainCertDto obtainCertDto = new ObtainCertDto();
        obtainCertDto.setLevelLanguageList(Arrays.asList(LevelLanguage.values()));
        obtainCertDto.setCertificateTypeList(Arrays.asList(CertificateType.values()));
        return obtainCertDto;
    }

    @PostMapping("/obtaincerts/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public ObtainCert saveFaculty(@RequestBody ObtainCertDto obtainCertDto) throws NotFoundDataExeption {
//        if (facultyDto.getFacultyName() == null) {
//            throw new NotFoundDataExeption("Not found - contain null");
//        }

        return obtainCertService.insert(obtainCertDto);
    }
    @GetMapping("/obtaincerts/search")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public  List<ObtainCertDto> editObtainCerts(@Param("studentid")String studentid){
        List<ObtainCertDto> obtainCertDto = obtainCertService.getByStudentId(studentid);
        return  obtainCertDto;
    }

    @GetMapping("/obtaincerts/edit/{studentid}/{type}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ObtainCertDto editObtainCert(@PathVariable(name = "studentid") String studentId,
                            @PathVariable(name = "type") CertificateType certificateType){
        ObtainCertDto obtainCertDto = obtainCertService.getByStudentIdAndLevelLanguage(studentId,certificateType);
        obtainCertDto.setLevelLanguageList(Arrays.asList(LevelLanguage.values()));
        obtainCertDto.setCertificateTypeList(Arrays.asList(CertificateType.values()));
        return obtainCertDto;
    }

    @PutMapping("/obtaincerts/edit/save")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putEditObtainCert(@RequestBody ObtainCertDto obtainCertDto) {
        obtainCertService.updateFromDto(obtainCertDto);
    }

    @DeleteMapping("/obtaincerts/delete/{element}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveFaculty(@PathVariable(name = "element") String element){
        String[] arrOfStr = element.split("&", 2);
        String studentid = arrOfStr[0];
        CertificateType certificateType = CertificateType.valueOf(arrOfStr[1]);
        obtainCertService.deleteByStudentIdAndLevel(studentid,certificateType);
    }
}
