package com.curso.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.Services.TechnicianService;
import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianResource {

    @Autowired
    private TechnicianService techService;

    @GetMapping //exemplo http://localhost:8080/technician
    public ResponseEntity<List<TechnicianDTO>> findAll(){
        return ResponseEntity.ok().body(techService.findAll());
    }

    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/technician/1
    public ResponseEntity<TechnicianDTO> findById(@PathVariable UUID id){
        Technician obj = this.techService.findbyId(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
    }

}
