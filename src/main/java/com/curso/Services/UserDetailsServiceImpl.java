package com.curso.Services;

import com.curso.Repositories.PersonRepository;
import com.curso.Repositories.UsersRepository;
import com.curso.domains.Users;
import com.curso.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepo.findByEmail(username);
        if(user.isPresent()) {
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getPassword(), user.get().getPersonType());
        }
        throw new UsernameNotFoundException(username);
    }

}
