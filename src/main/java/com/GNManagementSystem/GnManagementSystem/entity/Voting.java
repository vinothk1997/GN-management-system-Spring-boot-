package com.GNManagementSystem.GnManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Voting {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    private String id;
    private String serialNo;
    private String isEligible;
    private String year;
    private String administrativeDistrict;
    private String gnDivision;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;
}
