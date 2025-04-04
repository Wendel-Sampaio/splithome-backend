package com.splithome.application.entities.transaction;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Purchase extends Transaction {

    @NotNull(message = "O comprador não pode ser nulo")
    private UUID purchaserId;

    @NotNull(message = "A data não pode ser nula")
    private LocalDate purchaseDate;
}
