package com.curso.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.Repositories.TechnicianRepository;
import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository techRepo;

    public List<TechnicianDTO> findAll(){
        //retorna uma lista de TechnicianDTO
        return techRepo.findAll().stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
    }

    public Technician findbyId(UUID id){
        Optional<Technician> obj = techRepo.findById(id);
        return obj.orElse(null);
    }

    public Technician findbyCpf(String cpf){
        Optional<Technician> obj = techRepo.findByCpf(cpf);
        return obj.orElse(null);
    }
}
