package com.curso.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.curso.domains.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, UUID> {

}
