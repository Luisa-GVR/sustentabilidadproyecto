package com.sustentabilidad.sust.repository;

import com.sustentabilidad.sust.model.Compuestos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompuestosRepository extends JpaRepository<Compuestos, Long> {
}
