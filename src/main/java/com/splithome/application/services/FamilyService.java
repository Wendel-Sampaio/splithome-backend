package com.splithome.application.services;

import com.splithome.application.entities.Family;
import com.splithome.application.exceptions.FamilyNotFoundException;
import com.splithome.application.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;


    public void saveFamily(Family family) {
        if(familyRepository.existsById(family.getId())) {
            familyRepository.save(family);
        }
    }

    public Family getFamilyById(UUID id) {
        if (familyRepository.existsById(id)) {
            return familyRepository.findById(id).get();
        } else {
            throw new FamilyNotFoundException("Family with ID" + id +  " not found");
        }
    }

    public List<Family> getAllFamily() {
        return familyRepository.findAll();
    }

    public Family updateFamily(Family family) {
        if (familyRepository.existsById(family.getId())) {
            familyRepository.save(family);
            return family;
        } else {
            throw new FamilyNotFoundException("Family with ID" + family.getId() +  " not found");
        }
    }

    public void removeFamily(UUID id) {
        if (familyRepository.existsById(id)) {
            familyRepository.deleteById(id);
        } else {
            throw new FamilyNotFoundException("Family with ID" + id +  " not found");
        }
    }
}
