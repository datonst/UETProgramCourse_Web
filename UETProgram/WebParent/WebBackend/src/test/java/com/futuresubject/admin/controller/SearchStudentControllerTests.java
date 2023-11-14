package com.futuresubject.admin.controller;

import com.futuresubject.admin.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@SpringBootTest
public class SearchStudentControllerTests {
    @Autowired
    public SubjectService subjectService;

}
