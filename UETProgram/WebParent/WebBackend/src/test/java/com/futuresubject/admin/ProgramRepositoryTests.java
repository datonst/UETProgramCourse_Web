package com.futuresubject.admin;

import com.futuresubject.admin.repository.ProgramRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProgramRepositoryTests {
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateFirstCourse(){
//        Subject subject=entityManager.find(Subject.class,"INT2112");
//        RoleSubject roleSubject=entityManager.find(RoleSubject.class,1);
//        Course course = new Course("CN8","Khoa học máy tính","Công nghệ thông tin",158,102,
//                30,3,4,6,3,10);
//        course.addRoleSubject(roleSubject);
//        course.addSubject(subject);
//        Course saveCourse= courseRepository.save(course);
    }
}
