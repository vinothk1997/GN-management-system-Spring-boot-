package com.GNManagementSystem.GnManagementSystem.entity;

import com.GNManagementSystem.GnManagementSystem.enums.RequestStatus;
import com.GNManagementSystem.GnManagementSystem.enums.TypeOfCertificate;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class CertificateRequest {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique = true, nullable = false,columnDefinition = "VARCHAR(36)")
    private String id;
    @Enumerated(EnumType.STRING)
    private TypeOfCertificate typeOfCertificate;
    private String reason;
    private String requestedOrganization;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private String rejectionReason;
    private LocalDate requestedDate;
    private LocalDate requestStatusUpdateDate;
    private String requestStatusUpdatedBy;
    private String verificationNumber;
    @ManyToOne
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;
}

