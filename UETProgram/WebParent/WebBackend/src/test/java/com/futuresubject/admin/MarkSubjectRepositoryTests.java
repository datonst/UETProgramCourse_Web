package com.futuresubject.admin;

import com.futuresubject.admin.student.MarkSubjectRepository;
import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
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
public class MarkSubjectRepositoryTests {
    @Autowired
    private MarkSubjectRepository markSubjectRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateFirstRoles(){
        Subject subject=entityManager.find(Subject.class,"INT2112");
        Student student=entityManager.find(Student.class,"22028245");
        MarkSubject markSubject = new MarkSubject(student,subject,4.0);
        markSubjectRepository.save(markSubject);
    }
}
