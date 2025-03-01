package com.splithome.application.entities.transaction;

import com.splithome.application.entities.Category;
import com.splithome.application.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class Transaction {

    @Id
    @UuidGenerator
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(unique = true)
    private UUID id;

    private TransactionType type;
    @NotBlank
    private double value;

    @NotBlank
    private Category category;

    @NotBlank
    private List<User> payers;

    @NotBlank
    @Column(name = "payment_date")
    private Date paymentDate;


}
