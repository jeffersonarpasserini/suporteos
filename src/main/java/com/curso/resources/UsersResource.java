package com.curso.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.Services.UsersService;
import com.curso.domains.Users;
import com.curso.domains.dtos.UsersDTO;

@RestController
@RequestMapping(value = "/users")
public class UsersResource {

    @Autowired
    private UsersService usersService;

    @GetMapping //exemplo http://localhost:8080/users
    public ResponseEntity<List<UsersDTO>> findAll(){
        return ResponseEntity.ok().body(usersService.findAll());
    }

    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/users/1
    public ResponseEntity<UsersDTO> findById(@PathVariable UUID id){
        Users obj = this.usersService.findbyId(id);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}") //exemplo http://localhost:8080/users/cpf/11122233344
    public ResponseEntity<UsersDTO> findByCpf(@PathVariable String cpf){
        Users obj = this.usersService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

}
