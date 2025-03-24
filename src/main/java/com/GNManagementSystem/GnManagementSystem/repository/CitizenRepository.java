package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.dto.CitizenDto;
import com.GNManagementSystem.GnManagementSystem.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, String> {
    List<Citizen> findByDateOfBirth(LocalDate dateOfBirth);

    @Query("SELECT new com.GNManagementSystem.GnManagementSystem.dto.CitizenDto(" +
            "c.username, c.firstName, c.lastName, c.gender, c.age, c.dateOfBirth, c.familyCardNo,c.email) " +
            "FROM Citizen c " +
            "JOIN c.gramaNiladhariDivision gnd " +
            "JOIN GramaNiladhari gn ON gn.gramaNiladhariDivision = gnd " +
            "WHERE (:nic IS NULL OR c.nic = :nic) " +
            "AND (:gnId IS NULL OR gn.id = :gnId) " +
            "AND (:firstname IS NULL OR c.firstName LIKE CONCAT('%', :firstname, '%')) " +
            "AND (:familyCardNo IS NULL OR c.familyCardNo = :familyCardNo) " +
            "AND (:ageFrom IS NULL OR c.age >= :ageFrom) " +
            "AND (:ageTo IS NULL OR c.age <= :ageTo)")
    List<CitizenDto> getCitizensByFilter(@Param("nic") String nic,
                                         @Param("firstname") String firstname,
                                         @Param("familyCardNo") String familyCardNo,
                                         @Param("ageFrom") Integer ageFrom,
                                         @Param("ageTo") Integer ageTo,
                                         @Param("gnId") String gnId);


    Boolean existsByEmail(String email);

    Boolean existsByNic(String nic);
}
