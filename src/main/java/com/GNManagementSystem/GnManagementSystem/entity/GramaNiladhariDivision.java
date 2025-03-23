package com.GNManagementSystem.GnManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GramaNiladhariDivision {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column( columnDefinition = "VARCHAR(36)", unique = true,nullable = false)
    private String id;
    private String name;
    private String code;
    private String address;
    private String phone;
    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "gramaNiladhariDivision", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Citizen> citizens;
}
