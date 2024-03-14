package com.curso.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.curso.domains.Users;

public interface UsersRepository extends JpaRepository<Users, UUID> {

}
