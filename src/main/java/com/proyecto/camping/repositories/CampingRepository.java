package com.proyecto.camping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.camping.entities.Camping;

@Repository
public interface CampingRepository extends JpaRepository<Camping, Long> {

}
