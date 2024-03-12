package com.futuresubject.admin.repository;



import com.futuresubject.common.entity.Account.Role;


import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.annotation.Rollback;


//@RunWith(SpringRunner.class)

// bắt buộc phải là import org.junit.Test;  --> junit 4
// https://viblo.asia/p/viet-unit-test-service-layer-trong-spring-boot-voi-junit-5-bJzKmreBZ9N
//@DataJpaTest(showSql = false)
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
public class RoleRepositoryTests {
//    @Autowired
//    public RoleRepository roleRepository;
//
//    @Mock
//    public SubjectRepository subjectRepository;
//
//
//    @Test
//    public void Test1() {
//        Role role = new Role();
//        role.setName("ROLE_VIEWER");
//        if (roleRepository !=null) {
//            System.out.println("abcdcasdasdasdas");
//        }
//        assert roleRepository != null;
//        Role c = null;
//        c = roleRepository.save(role);
//        System.out.println(c.getName());
//    }




//    @Test
//    public void Test2() {
//        Role role = new Role();
//        role.setName("ROLE_ADMIN");
//        repository.save(role);
//    }
//
//    @Test
//    public void Test3() {
//        Role role = new Role();
//        role.setId(3);
//        role.setName("ROLE_EDIT");
//        repository.save(role);
//    }
}
