package com.curso.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.curso.domains.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, UUID> {

    Optional<Technician> findByCpf(String cpf);
    Optional<Technician> findByEmail(String email);

}
