package com.autodrivers.motors.domain.repository;

import com.autodrivers.motors.domain.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioVenta extends JpaRepository<Venta, Long> {
}
