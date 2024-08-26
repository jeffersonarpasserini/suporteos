package com.curso.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.curso.domains.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByCpf(String cpf);
    Optional<Users> findByEmail(String email);
}
