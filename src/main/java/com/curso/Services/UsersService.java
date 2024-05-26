package com.curso.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.Repositories.UsersRepository;
import com.curso.Services.exceptions.ObjectNotFoundException;
import com.curso.domains.Users;
import com.curso.domains.dtos.UsersDTO;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepo;

    public List<UsersDTO> findAll(){
        //retorna uma lista de UsersDTO
        return usersRepo.findAll().stream().map(obj -> new UsersDTO(obj)).collect(Collectors.toList());
    }

    public Users findbyId(UUID id){
        Optional<Users> obj = usersRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+id));
    }

    public Users findbyCpf(String cpf){
        Optional<Users> obj = usersRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF:"+cpf));
    }
    
}
