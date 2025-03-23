package com.GNManagementSystem.GnManagementSystem.entity;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationalQualification> educationalQualifications;
    @OneToMany(mappedBy = "gramaNiladhari",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GramaNiladhariHistory> gramaNiladhariHistories;
    @OneToOne
    @JoinColumn(name="gn_division_id")
    private GramaNiladhariDivision gramaNiladhariDivision;
}
