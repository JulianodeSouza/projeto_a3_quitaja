package com.quitaja.projeto_a3_quitaja.controller;

import com.quitaja.projeto_a3_quitaja.entity.FinancialInstitution;
import com.quitaja.projeto_a3_quitaja.repository.FinancialInstitutionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/financial-institutions")
public class FinancialInstitutionController {

    private final FinancialInstitutionRepository repository;

    public FinancialInstitutionController(FinancialInstitutionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<FinancialInstitution> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialInstitution> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FinancialInstitution create(@RequestBody FinancialInstitution financialInstitution) {
        return repository.save(financialInstitution);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialInstitution> update(@PathVariable Long id,
                                                       @RequestBody FinancialInstitution financialInstitution) {
        return repository.findById(id).map(existing -> {
            existing.setName(financialInstitution.getName());
            existing.setCode(financialInstitution.getCode());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
