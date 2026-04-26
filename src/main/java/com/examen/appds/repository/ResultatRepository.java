package com.examen.appds.repository;

import com.examen.appds.entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
}