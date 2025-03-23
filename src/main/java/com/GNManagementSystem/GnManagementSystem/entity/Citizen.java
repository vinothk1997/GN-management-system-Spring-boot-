package com.GNManagementSystem.GnManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Citizen extends User {
    @Column(name = "is_father")
    private Boolean father;
    @Column(name = "is_mother")
    private Boolean mother;
    @Column(name = "is_child")
    private Boolean child;
    private String familyCardNo;
    @JsonManagedReference
    @OneToMany(mappedBy = "citizen",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Certificate> certificates;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<EducationalQualification> educationalQualifications;
    @OneToMany(mappedBy = "citizen",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Voting> votings;
    @ManyToOne
    @JoinColumn(name = "grama_niladhari_division_id", nullable = false)
    @JsonManagedReference
    private GramaNiladhariDivision gramaNiladhariDivision;

}
