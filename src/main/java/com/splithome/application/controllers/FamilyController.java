package com.splithome.application.controllers;


import com.splithome.application.entities.Family;
import com.splithome.application.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/family")
@CrossOrigin(origins = "http://localhost:4200")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @PostMapping()
    public ResponseEntity<Family> createFamily(@RequestBody Family family) {
        familyService.saveFamily(family);
        return ResponseEntity.ok(family);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Family> getFamilyById(@PathVariable UUID id) {
        Family family = familyService.getFamilyById(id);
        return ResponseEntity.ok(family);
    }

    @GetMapping
    public ResponseEntity<List<Family>> getAllFamily() {
        List<Family> families = familyService.getAllFamily();
        return ResponseEntity.ok(families);
    }

    @PutMapping
    public ResponseEntity<Family> updateFamily(@RequestBody Family family) {
        Family newFamily = familyService.updateFamily(family);
        return ResponseEntity.ok(newFamily);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFamily(@PathVariable UUID id) {
        familyService.removeFamily(id);
        return ResponseEntity.ok("family deleted");
    }

}
