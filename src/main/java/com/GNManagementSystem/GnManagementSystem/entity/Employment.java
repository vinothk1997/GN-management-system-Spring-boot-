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
public class Employment {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique=true, nullable=false,columnDefinition = "VARCHAR(36)")
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
}
