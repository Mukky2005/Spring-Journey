package com.mukesh.firstpro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Objects;
@Entity
public class SoftEng {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String Name;
    private List<String> TechStack;


    public SoftEng() {

    }

    public SoftEng(Integer id, String name, List<String> techStack) {
        Id = id;
        Name = name;
        TechStack = techStack;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getTechStack() {
        return TechStack;
    }

    public void setTechStack(List<String> techStack) {
        TechStack = techStack;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftEng softEng = (SoftEng) o;
        return Objects.equals(Id, softEng.Id) && Objects.equals(Name, softEng.Name) && Objects.equals(TechStack, softEng.TechStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, TechStack);
    }
}
