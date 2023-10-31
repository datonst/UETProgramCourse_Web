package com.futuresubject.admin;


import com.futuresubject.admin.dto.SubjectDto;
import com.futuresubject.admin.mapper.SubjectMapper;
import com.futuresubject.admin.repository.SubjectRepository;
import com.futuresubject.common.entity.Enum.RoleType;
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
public class SubjectRepositoryTests {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateFirst(){
        Subject subject = new Subject();
        subject.setSubjectName("Lập trình nâng cao");
        subject.setSubjectid("INT2203");
        subject.setCredit(10);
        subject.setRoleType(RoleType.MANDATORY);
        subjectRepository.save(subject);
    }
    @Test
    public void testCreateSecond(){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectName("Công nghệ máy tính");
        subjectDto.setSubjectid("INT2501");
        subjectDto.setCredit(4);
        subjectDto.setRoleType(RoleType.OPTIONAL);
        Subject subject = SubjectMapper.INSTANCE.toEntity(subjectDto);
        subjectRepository.save(subject);
    }
}
