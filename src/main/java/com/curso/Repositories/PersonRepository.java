package com.curso.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.curso.domains.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    

}
