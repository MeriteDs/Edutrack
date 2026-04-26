package com.examen.appds.repository;

import com.examen.appds.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DepartementRepository extends JpaRepository<Departement, Long> {
}