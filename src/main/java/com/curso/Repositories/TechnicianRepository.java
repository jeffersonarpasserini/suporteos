package com.curso.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.curso.domains.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, UUID> {

}
