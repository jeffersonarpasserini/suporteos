package com.curso.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.Repositories.UsersRepository;
import com.curso.Services.exceptions.DataIntegrityViolationException;
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

    public Users findbyEmail(String email){
        Optional<Users> obj = usersRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email:"+email));
    }

    public Users create(UsersDTO objDto){
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        Users newObj = new Users(objDto);
        return usersRepo.save(newObj);
    }

    public Users update(UUID id, UsersDTO objDto){
        objDto.setId(id);
        Users oldObj = findbyId(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Users(objDto);
        return usersRepo.save(oldObj);
    }

    public void delete(UUID id){
        Users obj = findbyId(id);
        if (obj.getServiceOrders().size()>0){
            throw new DataIntegrityViolationException("Usuário não pode ser deletado pois possui ordens de serviço!");
        }
        usersRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(UsersDTO objDto) {
       Optional<Users> obj = usersRepo.findByCpf(objDto.getCpf());
       if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
       }

       obj = usersRepo.findByEmail(objDto.getEmail());
       if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
       }

    }
    
}
