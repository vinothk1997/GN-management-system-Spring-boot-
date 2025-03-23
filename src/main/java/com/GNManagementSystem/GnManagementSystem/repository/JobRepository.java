package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.dto.JobDetailDto;
import com.GNManagementSystem.GnManagementSystem.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> findByTitle(String title);

    @Query("SELECT new com.GNManagementSystem.GnManagementSystem.dto.JobDetailDto(j.id, j.title, j.description) " +
            "FROM Job j WHERE j.title = :title")
    JobDetailDto getJobDetailByTitle(@Param("title") String title);
}
