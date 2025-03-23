package com.GNManagementSystem.GnManagementSystem.entity;

import com.GNManagementSystem.GnManagementSystem.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class UserRole {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(unique = true, nullable = false,columnDefinition = "VARCHAR(36)")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @ToString.Exclude
    private User user;
    @Column(nullable = false,length = 50)
    @Enumerated(EnumType.STRING)
    private Role role;

}
