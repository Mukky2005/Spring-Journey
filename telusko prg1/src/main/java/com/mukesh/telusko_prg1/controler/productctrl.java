package com.mukesh.telusko_prg1.controler;

import com.mukesh.telusko_prg1.model.product;
import com.mukesh.telusko_prg1.service.productSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productctrl {
    @Autowired
    productSer Service;

    @GetMapping("/products")
    public List<product> getproducts(){
        return Service.getproducts();
    }
    @GetMapping("/products/{id}")
    public product getproductById(@PathVariable int id){
        return Service.getproductById(id);
    }

    @PostMapping("/products")
    public void appProduct (@RequestBody product prod){
        Service.addProduct(prod);
    }
    @PutMapping("/products")
    public void updateProduct(@RequestBody product prod){
        Service.updateProduct(prod);
    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id){
        Service.deleteProduct(id);
    }
}
