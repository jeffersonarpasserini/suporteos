package com.curso.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Technician extends Person {

    private List<ServiceOrder> serviceOrders = new ArrayList<>();

    public Technician(UUID id, String firstName, String lastName, String cpf, String email, String password) {
        super(id, firstName, lastName, cpf, email, password);
    }

    public Technician() {
        super();
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    
}
