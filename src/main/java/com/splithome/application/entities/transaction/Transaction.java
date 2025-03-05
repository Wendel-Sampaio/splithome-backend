package com.splithome.application.entities.transaction;

import com.splithome.application.DTOs.TransactionDTO;
import com.splithome.application.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public abstract class Transaction {

    @Id
    @UuidGenerator
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(unique = true)
    private UUID id;

    private TransactionType type;

    @NotBlank
    private String title;

    @NotBlank
    private Category category;

    @NotBlank
    private double value;

    @OneToMany(mappedBy = "transaction")
    @JoinColumn(name = "transaction_id")
    private List<User> payers;

    @NotBlank
    private Date paymentDate;

    @OneToMany(mappedBy = "transaction")
    @JoinColumn(name = "transaction_id")
    private List<User> remainingPayers;

    public abstract void updateTransaction(TransactionDTO transactionDTO);
}
