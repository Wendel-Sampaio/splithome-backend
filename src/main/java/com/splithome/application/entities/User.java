package com.splithome.application.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @UuidGenerator
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(unique = true)
    private UUID id;

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(min = 2, max = 50, message = "O nome precisa ter entre 2 à 50 caracteres.")
    private String nome;

    @NotBlank(message = "O email não pode ser vazio.")
    @Email(message = "O endereço de email precisa ter um formato válido.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    @Size(min = 8, max = 50, message = "A senha precisa ter entre 8 à 50 caracteres.")
    private String senha;

    @NotNull(message = "O telefone não pode ser nulo.")
    @Column(unique = true, length = 11)
    private String telefone;

    @NotNull(message = "A chave Pix não pode ser nula.")
    @Column(name = "chave_pix", unique = true)
    private String chavePix;


}
