package com.GNManagementSystem.GnManagementSystem.entity;

import com.GNManagementSystem.GnManagementSystem.enums.IncomeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Income {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique = true, nullable = false,columnDefinition = "VARCHAR(36)")
    private String id;
    @Enumerated(EnumType.STRING)
    private IncomeType incomeType;
    private String incomeSource;
    private BigDecimal incomeValue;
    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;
}
