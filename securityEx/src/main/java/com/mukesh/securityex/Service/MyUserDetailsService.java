package com.mukesh.securityex.Service;

import com.mukesh.securityex.Model.UserPrincipals;
import com.mukesh.securityex.Model.Users;
import com.mukesh.securityex.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found"+username);
        }
        return new UserPrincipals(user) ;
    }
}
