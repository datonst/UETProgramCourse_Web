package com.futuresubject.admin;

import com.futuresubject.admin.repository.StudentRepository;
import com.futuresubject.common.entity.Classroom;
import com.futuresubject.common.entity.GenderType;
import com.futuresubject.common.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTests {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFirstStudent() {
        Student student = new Student();
        student.setStudentId("22028242");
        Classroom classroom =entityManager.find(Classroom.class,"1");
        student.setClassroom(classroom);
        student.setAge(15);
        student.setName("Son");
        student.setGender(GenderType.Male);
        student.setAddress("PhuMinh - Soc Son");
        studentRepository.save(student);
    }
}
