package com.splithome.application.entities.transaction;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Expense extends Transaction {

    @NotNull(message = "O responsável não pode ser nulo")
    private UUID responsibleId;
}
