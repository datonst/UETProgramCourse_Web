package com.futuresubject.admin;

import com.futuresubject.admin.student.RoleSubjectRepository;
import com.futuresubject.common.entity.Course;
import com.futuresubject.common.entity.RoleSubject;
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
public class RoleSubjectRepositoryTests {
    @Autowired
    private RoleSubjectRepository roleSubjectRepositoryRepo;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateFirstRoles(){
        Subject subject=entityManager.find(Subject.class,"INT2112");
        RoleSubject roleSubject=new RoleSubject(subject,"Mandatory");
        RoleSubject saveRole= roleSubjectRepositoryRepo.save(roleSubject);
        assertThat(saveRole.getId()).isGreaterThan(0);
    }


}
