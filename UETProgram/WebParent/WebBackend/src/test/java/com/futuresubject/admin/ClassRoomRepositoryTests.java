package com.futuresubject.admin;

import com.futuresubject.admin.repository.ClassroomRepository;
import com.futuresubject.admin.service.MarkSubjectService;
import com.futuresubject.common.entity.Classroom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ClassRoomRepositoryTests {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private MarkSubjectService markSubjectService;

    @Test
    public void testClassroomFirst() {
        Classroom classroom = new Classroom();
//        classroom.setName("CA-CLC3");
        classroom.setCohort("K67");
        classroomRepository.save(classroom);
    }

}
