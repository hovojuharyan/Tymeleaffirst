package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.example.demo.model.User user=userRepository.findOneByEmail(s);
        if (user==null){
            throw new UsernameNotFoundException(String.format("User with %s not found",s));
        }
        return new CurrentUser(user);
    }
}
