package com.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.Services.TechnicianService;
import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/technician")
@Tag(name = "Technician", description = "API para gerenciamento de técnicos")
public class TechnicianResource {

    @Autowired
    private TechnicianService techService;

    @Operation(summary = "Listar todos os tecnicos", description = "Retorna uma lista com todos os tecnicos.")
    @GetMapping //exemplo http://localhost:8080/technician
    public ResponseEntity<List<TechnicianDTO>> findAll(){
        return ResponseEntity.ok().body(techService.findAll());
    }

    @Operation(summary = "Buscar tecnico por ID", description = "Retorna um tecnico com base no ID fornecido.")
    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/technician/1
    public ResponseEntity<TechnicianDTO> findById(@PathVariable UUID id){
        Technician obj = this.techService.findbyId(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Buscar tecnico por CPF", description = "Retorna um tecnico com base no CPF fornecido.")
    @GetMapping(value = "/cpf/{cpf}") //exemplo http://localhost:8080/technician/cpf/11122233344
    public ResponseEntity<TechnicianDTO> findByCpf(@PathVariable String cpf){
        Technician obj = this.techService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Buscar tecnico por e-mail", description = "Retorna um tecnico com base no e-mail fornecido.")
    @GetMapping(value = "/email/{email}") //exemplo http://localhost:8080/technician/email/email@email.com
    public ResponseEntity<TechnicianDTO> findByEmail(@PathVariable String email){
        Technician obj = this.techService.findbyEmail(email);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

    @Operation(summary = "Criar um novo tecnico", description = "Cria um novo tecnico com base nos dados fornecidos.")
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO objDto){
        Technician newObj = techService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar tecnico", description = "Atualiza os dados de um tecnico existente com base no ID fornecido.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable UUID id, @Valid @RequestBody TechnicianDTO objDto){
        Technician Obj = techService.update(id, objDto);
        return ResponseEntity.ok().body(new TechnicianDTO(Obj));
    }

    @Operation(summary = "Deletar tecnico", description = "Remove um tecnico existente com base no ID fornecido.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable UUID id){
        techService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
