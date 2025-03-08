package com.splithome.application.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements UserDetails {

    @Id
    @UuidGenerator
    @Column(updatable = false)
    private UUID id;

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(min = 2, max = 50, message = "O nome precisa ter entre 2 à 50 caracteres.")
    private String name;

    @NotBlank(message = "O email não pode ser vazio.")
    @Email(message = "O endereço de email precisa ter um formato válido.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    private String password;

    @Column(name = "phone_number", unique = true, length = 11)
    private String phoneNumber;

    @Column(name = "pix_key", unique = true)
    private String pixKey;

    public User(String name, String email, String encryptedPassword) {
        this.name = name;
        this.email = email;
        this.password = encryptedPassword;
        this.phoneNumber = null;
        this.pixKey = null;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
