package com.mukesh.telusko_prg1.repo;

import com.mukesh.telusko_prg1.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends JpaRepository<product,Integer> {



}
