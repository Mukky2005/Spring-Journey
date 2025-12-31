package com.mukesh.securityex.Service;


import com.mukesh.securityex.Model.Users;
import com.mukesh.securityex.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private jwtService jwtS;
    @Autowired
    private UserRepo repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register (Users user ){

        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }


    public String verify(Users user) {

        Authentication auth =
                authManager.authenticate( new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (auth.isAuthenticated()) {
            return jwtS.generateToken(user.getUserName());
        }
        return "fail";
    }
}
