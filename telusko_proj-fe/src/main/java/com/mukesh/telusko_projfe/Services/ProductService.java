package com.mukesh.telusko_projfe.Services;


import com.mukesh.telusko_projfe.Model.Products;
import com.mukesh.telusko_projfe.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;


    public List<Products> getAllProducts() {
        return repo.findAll();
    }

    public Products getById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Products addProduct(Products product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public Products updateProduct(int id, Products product, MultipartFile imageFile) throws IOException {
        product.setId(id);

        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Products> searchProducts(String keyword) {
        return repo.SearchProducts(keyword);
    }
}
