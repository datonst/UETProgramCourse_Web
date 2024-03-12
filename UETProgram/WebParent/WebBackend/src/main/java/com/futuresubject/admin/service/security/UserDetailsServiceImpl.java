package com.futuresubject.admin.service.security;

import com.futuresubject.admin.repository.UserRepository;
import com.futuresubject.common.entity.Account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + login));
        UserDetailsImpl u = UserDetailsImpl.build(user);
        System.out.println(u.getUsername());
        return u;
    }
}
