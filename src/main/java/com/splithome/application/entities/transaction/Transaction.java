package com.splithome.application.entities.transaction;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transaction {

    @Id
    @UuidGenerator
    @Column(updatable = false)
    private UUID id;

    @NotNull
    private TransactionType type;

    @NotBlank(message = "A transação precisa ter um título")
    private String title;

    @NotNull
    private Category category;

    @NotNull
    private double value;

    @ElementCollection
    private List<String> payers;

    @NotNull
    private Date paymentDate;

    @ElementCollection
    private List<String> remainingPayers;
}
