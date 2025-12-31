package com.mukesh.firstpro;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftEngServise {
    private final SoftEngRepo softEngRepo;

    public SoftEngServise(SoftEngRepo softEngRepo) {
        this.softEngRepo = softEngRepo;
    }

    public List<SoftEng> getAllSoftEng() {
        return softEngRepo.findAll();
    }

    public void insertSoftEng(SoftEng softEng) {

        softEngRepo.save(softEng);
    }


    public SoftEng getAllSoftEngById(Integer id) {
        return softEngRepo.findById(id).orElseThrow(() -> new IllegalStateException(id + "Not Found"));
    }
}
