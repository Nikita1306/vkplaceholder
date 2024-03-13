package com.vk.vktest.services;

import com.vk.vktest.models.SystemUser;
import com.vk.vktest.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class SystemUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public SystemUser register(SystemUser user) {
        log.info("{}", user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(user.getRoles());
        return userRepository.save(user);
    }
    public SystemUser login(SystemUser user) {

        SystemUser checkUser = userRepository.findByLogin(user.getLogin()).get();
        if (bCryptPasswordEncoder.matches(user.getPassword(), checkUser.getPassword())) {
            return checkUser;
        } else return null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SystemUser> user = userRepository.findByLogin(username);
        return user.get();
    }

}
