package com.autodrivers.motors.domain.repository;

import com.autodrivers.motors.domain.model.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioMantenimiento extends JpaRepository<Mantenimiento, Long> {
}
