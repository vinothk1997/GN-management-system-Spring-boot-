package com.GNManagementSystem.GnManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Job {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique=true, nullable=false,columnDefinition = "VARCHAR(36)")
    private String id;
    private String title;
    private String description;
}
