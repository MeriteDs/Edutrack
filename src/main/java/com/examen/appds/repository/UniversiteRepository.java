package com.examen.appds.repository;

import com.examen.appds.entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
}