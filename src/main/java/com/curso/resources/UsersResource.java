package com.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.Services.UsersService;
import com.curso.domains.Users;
import com.curso.domains.dtos.UsersDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Users", description = "API para gerenciamento de usuários")
public class UsersResource {

    @Autowired
    private UsersService usersService;

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários.")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TECHNICIAN')")
    @GetMapping //exemplo http://localhost:8080/users
    public ResponseEntity<List<UsersDTO>> findAll(){
        return ResponseEntity.ok().body(usersService.findAll());
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TECHNICIAN') or hasRole('USERS')")
    @PostAuthorize(value = "hasRole('ADMIN') or hasRole('TECHNICIAN') or returnObject.body.email == authentication.name")
    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/users/1
    public ResponseEntity<UsersDTO> findById(@PathVariable UUID id){
        Users obj = this.usersService.findbyId(id);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @Operation(summary = "Buscar usuário por CPF", description = "Retorna um usuário com base no CPF fornecido.")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TECHNICIAN') or hasRole('USERS')")
    @PostAuthorize(value = "hasRole('ADMIN') or hasRole('TECHNICIAN') or returnObject.body.email == authentication.name")
    @GetMapping(value = "/cpf/{cpf}") //exemplo http://localhost:8080/users/cpf/11122233344
    public ResponseEntity<UsersDTO> findByCpf(@PathVariable String cpf){
        Users obj = this.usersService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @Operation(summary = "Buscar usuário por e-mail", description = "Retorna um usuário com base no e-mail fornecido.")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TECHNICIAN') or hasRole('USERS')")
    @PostAuthorize(value = "hasRole('ADMIN') or hasRole('TECHNICIAN') or returnObject.body.email == authentication.name")
    @GetMapping(value = "/email/{email}") //exemplo http://localhost:8080/technician/email/email@email.com
    public ResponseEntity<UsersDTO> findByEmail(@PathVariable String email){
        Users obj = this.usersService.findbyEmail(email);
        return ResponseEntity.ok().body(new UsersDTO(obj));
    }

    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário com base nos dados fornecidos.")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TECHNICIAN')")
    @PostMapping
    public ResponseEntity<UsersDTO> create(@Valid @RequestBody UsersDTO objDto){
        Users newObj = usersService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TECHNICIAN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> update(@PathVariable UUID id, @Valid @RequestBody UsersDTO objDto){
        Users Obj = usersService.update(id, objDto);
        return ResponseEntity.ok().body(new UsersDTO(Obj));
    }

    @Operation(summary = "Deletar usuário", description = "Remove um usuário existente com base no ID fornecido.")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TECHNICIAN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> delete(@PathVariable UUID id){
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
