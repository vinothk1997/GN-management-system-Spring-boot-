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
public class EducationalQualification {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique=true, nullable=false,columnDefinition = "VARCHAR(36)")
    private String id;
    private String qualification;
    private String institution;
    private Integer startingYear;
    private Integer completionYear;
    @JsonBackReference
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
