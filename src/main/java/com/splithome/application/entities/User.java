package com.splithome.application.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
public class User {

    @Id
    @UuidGenerator
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID id;

    private String nome;

    private String email;


}
