package com.GNManagementSystem.GnManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class GramaNiladhariHistory {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique = true, nullable = false,columnDefinition = "VARCHAR(36)")
    private String id;
    private String designation;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    @ManyToOne
    @JoinColumn(name = "grama_niladhari_id", nullable = false)
    private GramaNiladhari gramaNiladhari;

}
