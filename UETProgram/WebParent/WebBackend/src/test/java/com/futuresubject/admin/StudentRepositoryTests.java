package com.futuresubject.admin;

import com.futuresubject.admin.student.StudentRepository;
import com.futuresubject.common.entity.Course;
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
        Course course = entityManager.find(Course.class,"CN8");
        Student student = new Student("22028245","Trần Văn Sơn","03/08/2004","K67-CA-CLC4","22028245@vnu.edu.vn","hello");
        student.addCourse(course);
        Student saveStudent = studentRepository.save(student);

    }
}
