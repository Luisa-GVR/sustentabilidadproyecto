package com.sustentabilidad.sust.repository;

import com.sustentabilidad.sust.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
}
