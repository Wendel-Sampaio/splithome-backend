package com.splithome.application.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Family {

    @Id
    @UuidGenerator
    @Column(updatable = false)
    private UUID id;


    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(min = 2, max = 50, message = "O nome precisa ter entre 2 à 50 caracteres.")
    private String name;

    private String familyCode;

    @OneToMany(mappedBy = "family")
    @JsonManagedReference
    private List<User> members;





    public void addMember (User user) {
        this.members.add(user);
    }



}

