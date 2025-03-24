package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.dto.EmploymentResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment, String> {
    @Query("SELECT new com.GNManagementSystem.GnManagementSystem.dto.EmploymentResponseDto(e.id,j.title,e.startDate, e.endDate) " +
            "FROM Employment e " +
            "JOIN e.job j " +
            "JOIN e.user u " +
            "WHERE u.id = :userId")
    List<EmploymentResponseDto> getAllEmploymentByUserId(@Param("userId") String userId);
}
