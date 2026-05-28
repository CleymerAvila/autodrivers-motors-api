package com.autodrivers.motors.domain.repository;

import com.autodrivers.motors.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCliente extends JpaRepository<Cliente, Long> {
}
