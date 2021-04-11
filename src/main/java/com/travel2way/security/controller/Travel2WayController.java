package com.travel2way.security.controller;


import com.travel2way.security.domain.TravelWayDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/travel")
public class Travel2WayController {


    private static final List<TravelWayDTO> STUDENT = Arrays.asList(new TravelWayDTO(1, "ghatsila", "Ghatsila ,Jharkhand")
            , new TravelWayDTO(2, "jamshedpur", "jamshedpur ,Jharkhand"));
    @GetMapping("/getTravelDetails")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_VISITOR')")
    public ResponseEntity<List<TravelWayDTO>> getTravelDetails() {
        return new ResponseEntity<>(STUDENT, HttpStatus.OK);
    }

    @PostMapping(path = "/insertTravelDetails",consumes ="application/json")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<String> insertTravelDetails(@RequestBody TravelWayDTO student) {
        return new ResponseEntity<>("SAVED", HttpStatus.OK);
    }
    @PutMapping("/updateTravelDetails")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<String> updateTravelDetails(@RequestBody TravelWayDTO student) {
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
    @DeleteMapping("/deleteTravelDetails")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<String> deleteTravelDetails(@RequestParam(name = "id") int id) {

        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
    @GetMapping("/getTravelDetail")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_VISITOR')")
    public ResponseEntity<TravelWayDTO> getTravelDetail(@RequestParam(name = "id") int id) {
        return new ResponseEntity<>(STUDENT.stream().filter(e->e.getId()==id).collect(Collectors.toList()).get(0), HttpStatus.OK);
    }
}
