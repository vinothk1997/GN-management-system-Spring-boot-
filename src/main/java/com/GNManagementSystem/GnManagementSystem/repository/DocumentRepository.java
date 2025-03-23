package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentRepository extends JpaRepository<Certificate, Integer> {

    @Query("SELECT d.url FROM Certificate d WHERE d.citizen = :userId " +
            "AND (:isCharacterCertificate IS NULL OR d.isCharacterCertificate = :isCharacterCertificate) " +
            "AND (:isBirthCertificate IS NULL OR d.isBirthCertificate = :isBirthCertificate) " +
            "AND (:isNic IS NULL OR d.isNic = :isNic) " +
            "AND (:isSignature IS NULL OR d.isSignature = :isSignature)")
    String getUrlDocument(@Param("userId") String userId,
                          @Param("isCharacterCertificate") Boolean isCharacterCertificate,
                          @Param("isBirthCertificate") Boolean isBirthCertificate,
                          @Param("isNic") Boolean isNic,
                          @Param("isSignature") Boolean isSignature);

}
