package com.mukesh.securityex.Repo;

import com.mukesh.securityex.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByUserName(String userName);



}
