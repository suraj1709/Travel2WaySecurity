package com.travel2way.security.controller;

import com.travel2way.security.domain.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {


    private static final List<Student> STUDENT = Arrays.asList(new Student(1, "suraj", "kashida")
            , new Student(2, "rahul", "kashida"));
    @GetMapping("/getStudents")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_VISITOR')")
    public ResponseEntity<List<Student>> getStudent() {
        return new ResponseEntity<>(STUDENT, HttpStatus.OK);
    }

    @PostMapping(path = "/insert",consumes ="application/json")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<String> insertStudent(@RequestBody Student student) {
        return new ResponseEntity<>("SAVED", HttpStatus.OK);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<String> deleteStudent(@RequestParam(name = "id") int id) {

        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
    @GetMapping("/getStudent")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_VISITOR')")
    public ResponseEntity<Student> getStudent(@RequestParam(name = "id") int id) {
        return new ResponseEntity<>(STUDENT.stream().filter(e->e.getId()==id).collect(Collectors.toList()).get(0), HttpStatus.OK);
    }
}
