package com.splithome.application.services;

import com.splithome.application.entities.Family;
import com.splithome.application.exceptions.FamilyNotFoundException;
import com.splithome.application.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    public void saveFamily(Family family) {
        family.setFamilyCode(this.generateFamilyCode());
        familyRepository.save(family);
    }

    public Family getFamilyById(UUID id) {
        if (familyRepository.existsById(id)) {
            return familyRepository.findById(id).get();
        } else {
            throw new FamilyNotFoundException();
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
            throw new FamilyNotFoundException();
        }
    }

    public void removeFamily(UUID id) {
        if (familyRepository.existsById(id)) {
            familyRepository.deleteById(id);
        } else {
            throw new FamilyNotFoundException();
        }
    }

    private String generateFamilyCode() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder code;
        String generatedCode;

        do {
            code = new StringBuilder(8);
            for (int i = 0; i < 8; i++) {
                int index = random.nextInt(caracteres.length());
                code.append(caracteres.charAt(index));
            }
            generatedCode = code.toString();
        } while (familyRepository.existsByFamilyCode(generatedCode));

        return generatedCode;
    }
}
