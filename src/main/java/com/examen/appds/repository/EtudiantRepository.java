package com.examen.appds.repository;

import com.examen.appds.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByMatricule(@Param("matricule") String matricule);
    List<Etudiant> findByNomContainingIgnoreCase(@Param("nom") String nom);
}