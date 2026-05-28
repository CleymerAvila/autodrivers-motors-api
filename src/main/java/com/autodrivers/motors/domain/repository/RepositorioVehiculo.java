package com.autodrivers.motors.domain.repository;

import com.autodrivers.motors.domain.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioVehiculo extends JpaRepository<Vehiculo, Long> {
}
