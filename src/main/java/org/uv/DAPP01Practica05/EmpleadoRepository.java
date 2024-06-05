package org.uv.DAPP01Practica05;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

    public Optional<Empleado> findById(String id);
    
}
