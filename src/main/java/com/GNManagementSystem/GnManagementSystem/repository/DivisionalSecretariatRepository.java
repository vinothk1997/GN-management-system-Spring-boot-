package com.GNManagementSystem.GnManagementSystem.repository;

import com.GNManagementSystem.GnManagementSystem.entity.DivisionalSecretariat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionalSecretariatRepository extends JpaRepository<DivisionalSecretariat, String> {
}
