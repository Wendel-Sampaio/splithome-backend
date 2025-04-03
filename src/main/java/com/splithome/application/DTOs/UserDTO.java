package com.splithome.application.DTOs;

import com.splithome.application.entities.Family;

import java.util.UUID;

public record UserDTO(UUID id, String name, String email, String phoneNumber, String pixKey, String familyId) {
}
