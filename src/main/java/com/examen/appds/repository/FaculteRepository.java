package com.examen.appds.repository;

import com.examen.appds.entity.Faculte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FaculteRepository extends JpaRepository<Faculte, Long> {
}