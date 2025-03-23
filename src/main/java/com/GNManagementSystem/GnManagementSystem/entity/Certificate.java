package com.GNManagementSystem.GnManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Certificate {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    private String url;
    private Boolean isBirthCertificate;
    private Boolean isNic;
    private Boolean isCharacterCertificate;
    private Boolean isIncomeCertificate;
    private Boolean isSignature;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "citizen_id")
    @ToString.Exclude
    @JsonBackReference
    private User citizen;

}
