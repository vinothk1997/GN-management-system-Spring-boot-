package com.GNManagementSystem.GnManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@SuperBuilder
public class GramaNiladhari extends User{
    private String jobCardNo;
    private String serviceGrade;
    @Column(name = "is_permanent")
    private Boolean permanent;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<EducationalQualification> educationalQualifications;
    @OneToOne
    @JoinColumn(name="gn_division_id")
    private GramaNiladhariDivision gramaNiladhariDivision;
}
