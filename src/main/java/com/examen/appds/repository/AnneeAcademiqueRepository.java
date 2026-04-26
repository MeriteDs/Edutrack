package com.examen.appds.repository;

import com.examen.appds.entity.AnneeAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AnneeAcademiqueRepository extends JpaRepository<AnneeAcademique, Long> {
}