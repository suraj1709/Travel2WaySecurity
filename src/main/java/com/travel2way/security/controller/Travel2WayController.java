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
@RequestMapping("/secure")
public class Travel2WayController {





    @GetMapping("/hello")
    public ResponseEntity<String> gethello(@RequestParam(name = "id") int id) {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }


}
