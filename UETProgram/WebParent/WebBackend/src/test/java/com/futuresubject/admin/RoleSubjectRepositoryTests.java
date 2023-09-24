package com.futuresubject.admin;

import com.futuresubject.admin.student.RoleSubjectRepository;
import com.futuresubject.common.entity.RoleSubject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleSubjectRepositoryTests {
    @Autowired
    private RoleSubjectRepository roleSubjectRepositoryRepo;
    @Test
    public void testCreateFirst(){
//        RoleSubject roleSubject=new RoleSubject("CN1","INT2202","Mandatory");
//        RoleSubject saveRole= roleSubjectRepositoryRepo.save(roleSubject);
//        assertThat(saveRole.getId()).isGreaterThan(0);
    }


}
