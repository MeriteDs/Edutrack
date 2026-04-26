package com.examen.appds.repository;

import com.examen.appds.entity.Palmares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface PalmaresRepository extends JpaRepository<Palmares, Long> {
    List<Palmares> findByEtudiantId(Long etudiantId);
}