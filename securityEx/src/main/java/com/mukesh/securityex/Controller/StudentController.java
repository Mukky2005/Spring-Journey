package com.mukesh.securityex.Controller;


import com.mukesh.securityex.Model.Students;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Students> students = new ArrayList<>(List.of(
            new Students(1,"Mukesh",80),
            new Students(2,"Harish",98)
    ));


    @GetMapping("/students")
    public List<Students> getStudents() {
        return students;
    }


    @GetMapping("/csrf")
    public CsrfToken csrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Students addStudent(@RequestBody Students student) {
        students.add(student);
        return student;
    }
}
