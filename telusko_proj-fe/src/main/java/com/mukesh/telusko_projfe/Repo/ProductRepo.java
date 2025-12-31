package com.mukesh.telusko_projfe.Repo;

import com.mukesh.telusko_projfe.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {

    @Query("SELECT p FROM Products p WHERE " +
                    "(LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')))"
    )
    List<Products> SearchProducts(@Param("keyword") String keyword);
}
