package com.curso.domains.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.curso.domains.ServiceOrder;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class ServiceOrderDTO {

    private UUID id;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate starDate = LocalDate.now();
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    @NotNull(message = "O campo Titulo é requerido")
    private String titleOS;
    @NotNull(message = "O campo descrição é requerido")
    private String description;
    @NotNull(message = "O campo Prioridade é requerido")
    private Integer orderPriority;
    @NotNull(message = "O campo Status é requerido")
    private Integer orderStatus;
    @NotNull(message = "O campo Técnico é requerido")
    private UUID technician;
    @NotNull(message = "O campo Usuário é requerido")
    private UUID user;
    private String nameTechnician;
    private String nameUser;
    
    public ServiceOrderDTO() { }

    public ServiceOrderDTO(ServiceOrder serviceOrder) {
        this.id = serviceOrder.getId();
        this.starDate = serviceOrder.getStarDate();
        this.endDate = serviceOrder.getEndDate();
        this.titleOS = serviceOrder.getTitleOS();
        this.description = serviceOrder.getDescription();
        this.orderPriority = serviceOrder.getOrderPriority().getId();
        this.orderStatus = serviceOrder.getOrderStatus().getId();
        this.technician = serviceOrder.getTechnician().getId();
        this.user = serviceOrder.getUser().getId();
        this.nameTechnician = serviceOrder.getTechnician().getFirstName() + " " + serviceOrder.getTechnician().getLastName();
        this.nameUser = serviceOrder.getUser().getFirstName() + " " + serviceOrder.getUser().getLastName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitleOS() {
        return titleOS;
    }

    public void setTitleOS(String titleOS) {
        this.titleOS = titleOS;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(Integer orderPriority) {
        this.orderPriority = orderPriority;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UUID getTechnician() {
        return technician;
    }

    public void setTechnician(UUID technician) {
        this.technician = technician;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getNameTechnician() {
        return nameTechnician;
    }

    public void setNameTechnician(String nameTechnician) {
        this.nameTechnician = nameTechnician;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

}
