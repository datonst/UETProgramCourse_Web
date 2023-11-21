package com.futuresubject.admin.controller;

import com.futuresubject.admin.service.SubjectService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SearchStudentControllerTests {
    @Autowired
    public SubjectService subjectService;

//    @Test
//    public void ma() {
//        System.out.println(subjectService.findAll());
//    }
}
