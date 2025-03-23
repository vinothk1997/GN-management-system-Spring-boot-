package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.dto.IncomeResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.Income;
import com.GNManagementSystem.GnManagementSystem.enums.IncomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, String> {

    @Query("SELECT i.incomeType FROM Income i WHERE i.citizen.id = :citizenId")
    List<IncomeType> findIncomeTypeByCitizenId(@Param("citizenId") String citizenId);

    @Query("SELECT new com.GNManagementSystem.GnManagementSystem.dto.IncomeResponseDto(i.id,i.incomeType,i.incomeSource,i.incomeValue,i.citizen.id) FROM Income i WHERE i.citizen.id = :citizenId")
    List<IncomeResponseDto> findAllByCitizenId(@Param("citizenId") String citizenId);
}
