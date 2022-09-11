package com.bribri.kitchen.service;

import com.bribri.kitchen.dao.UserRepository;
import com.bribri.kitchen.dto.UserDto;
import com.bribri.kitchen.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Lazy
        @Autowired
        private PasswordEncoder bcryptEncoder;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUserName(username);
            if (user == null) {
                System.out.println(user.toString());
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                    new ArrayList<>());
        }

        public UserDto save(User user) {
            User newUser = new User();
            newUser.setUserName(user.getUserName());
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            return new UserDto(userRepository.save(newUser));
        }
}
