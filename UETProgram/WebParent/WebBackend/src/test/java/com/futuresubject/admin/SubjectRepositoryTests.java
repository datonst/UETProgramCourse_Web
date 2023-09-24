package com.futuresubject.admin;


import com.futuresubject.admin.student.SubjectRepository;
import com.futuresubject.common.entity.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SubjectRepositoryTests {

    @Autowired
    private SubjectRepository subjectRepository;
    @Test
    public void testCreateFirst(){
        Subject subject=new Subject("INT2112","Lập trình nâng cao",4);
        Subject saveSubject= subjectRepository.save(subject);
        assertThat(saveSubject.getSubjectId()).isNotNull();
    }
    @Test
    public void testCreateSecond(){
        Subject subject=new Subject("MAT1321","Giải tích 1",4);
        Subject saveSubject= subjectRepository.save(subject);
        assertThat(saveSubject.getSubjectId()).isNotNull();
    }
}
