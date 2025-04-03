package com.splithome.application.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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

    @OneToMany (mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> members;

    private String familyCode;

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", familyCode='" + familyCode + '\'' +
                ", members=" + members +
                '}';
    }


}

