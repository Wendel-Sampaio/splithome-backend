package com.splithome.application.entities.transaction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.splithome.application.entities.Family;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
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

    @NotBlank(message = "A transação precisa ter um título")
    private String title;

    @NotNull
    private Category category;

    @NotNull
    private double value;

    @ElementCollection
    private List<String> payers;

    @NotNull
    private LocalDate paymentDate;

    @ElementCollection
    private List<String> remainingPayers;

    @ManyToOne
    @JoinColumn(name = "family_id")
    @JsonBackReference
    private Family family;

    public String getFamilyCode() {
        return family != null ? family.getFamilyCode() : null;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", value=" + value +
                ", payers=" + payers +
                ", paymentDate=" + paymentDate +
                ", remainingPayers=" + remainingPayers +
                ", familyCode=" + family.getFamilyCode() +
                '}';
    }
}
