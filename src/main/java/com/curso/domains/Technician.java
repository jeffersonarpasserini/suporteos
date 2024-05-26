package com.curso.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.curso.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "technician")
public class Technician extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "technician") // technician é o nome do atributo em ServiceOrder
    private List<ServiceOrder> serviceOrders = new ArrayList<>();

    public Technician(UUID id, String firstName, String lastName, String cpf, String email, String password) {
        super(id, firstName, lastName, cpf, email, password);
        addPersonType(PersonType.TECHNICIAN);
    }

    public Technician() {
        super();
        addPersonType(PersonType.TECHNICIAN);
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }    
}
