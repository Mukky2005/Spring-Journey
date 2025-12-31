package com.mukesh.telusko_prg1.service;

import com.mukesh.telusko_prg1.model.product;
import com.mukesh.telusko_prg1.repo.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productSer {

    @Autowired
    productRepo productRepo;

//    List<product> products = new ArrayList<>( Arrays.asList(
//            new product(101,"phone", 5000.0),
//            new product(102,"phone", 5000.0)
//    ));

    public List<product> getproducts(){
        return productRepo.findAll();
    }

    public product getproductById(int id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void addProduct(product prod){
        productRepo.save(prod);
    }

    public void updateProduct(product prod) {
        productRepo.save(prod);
    }

    public void deleteProduct( int id) {
        productRepo.deleteById(id);
    }
}
