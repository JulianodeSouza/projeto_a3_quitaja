package com.quitaja.projeto_a3_quitaja.repository;

import com.quitaja.projeto_a3_quitaja.entity.FinancialInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialInstitutionRepository extends JpaRepository<FinancialInstitution, Long> {
}
