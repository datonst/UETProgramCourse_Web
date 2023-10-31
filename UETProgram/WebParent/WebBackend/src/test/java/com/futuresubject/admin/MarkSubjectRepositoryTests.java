package com.futuresubject.admin;

import com.futuresubject.admin.repository.MarkSubjectRepository;
import com.futuresubject.common.entity.MarkSubject;
import com.futuresubject.common.entity.Student;
import com.futuresubject.common.entity.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

;
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
        Subject subject=entityManager.find(Subject.class,1);
        Student student=entityManager.find(Student.class,1);
        MarkSubject markSubject = new MarkSubject(student,subject,4.0);
        markSubjectRepository.save(markSubject);
    }
}
