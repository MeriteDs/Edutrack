package com.examen.appds.repository;

import com.examen.appds.entity.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
}