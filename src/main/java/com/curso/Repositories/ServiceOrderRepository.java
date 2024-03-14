package com.curso.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.curso.domains.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, UUID> {

}
