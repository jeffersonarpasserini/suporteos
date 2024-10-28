package com.curso.Repositories;

import java.util.Optional;
import java.util.UUID;

import com.curso.domains.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.curso.domains.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByCpf(String cpf);
    Optional<Person> findByEmail(String email);
}
