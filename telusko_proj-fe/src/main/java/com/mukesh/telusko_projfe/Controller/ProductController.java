package com.mukesh.telusko_projfe.Controller;


import com.mukesh.telusko_projfe.Model.Products;
import com.mukesh.telusko_projfe.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService Service;

    @GetMapping("/products")
    public ResponseEntity <List<Products>> getAllProducts() {
        return new ResponseEntity<>(Service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable int id) {
        Products product = Service.getById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/product",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(@RequestPart Products product,
                                        @RequestPart MultipartFile imageFile) {
        try{
            Products products = Service.addProduct(product, imageFile);
            return new ResponseEntity<>(products, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageProdId(@PathVariable int id) {
        Products product = Service.getById(id);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping(value = "/product/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                                @RequestPart Products product,
                                                @RequestPart MultipartFile imageFile){
        try {
            Products products = Service.updateProduct(id,product,imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("failed to update",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {

        Products product = Service.getById(id);
        if (product != null) {
            Service.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/products/search")
    public ResponseEntity<List<Products>> searchProducts(@RequestParam String keyword) {

        List<Products> products = Service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }



}
