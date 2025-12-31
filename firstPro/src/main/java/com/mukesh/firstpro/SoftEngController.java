package com.mukesh.firstpro;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Soft-Eng")
public class SoftEngController {
    private final SoftEngServise softEngServise;

    public SoftEngController(SoftEngServise softEngServise) {
        this.softEngServise = softEngServise;
    }

    @GetMapping
    public List<SoftEng> getSoftEng(){
        return softEngServise.getAllSoftEng();
    }

    @GetMapping("{id}")
    public SoftEng getSoftEngById(@PathVariable Integer id){
        return softEngServise.getAllSoftEngById(id);
    }

    @PostMapping
    public void addNewSoftEng(@RequestBody SoftEng softEng){
        softEngServise.insertSoftEng(softEng);
    }


}
