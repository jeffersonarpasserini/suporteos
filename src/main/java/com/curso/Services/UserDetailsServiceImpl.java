package com.curso.Services;

import com.curso.Repositories.PersonRepository;
import com.curso.Repositories.UsersRepository;
import com.curso.domains.Person;
import com.curso.domains.Users;
import com.curso.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    //private final UsersRepository userRepository;
    private final PersonRepository personRepository;

    //public UserDetailsServiceImpl(UsersRepository userRepository) {
    public UserDetailsServiceImpl(PersonRepository personRepository) {
        //this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Optional<Users> user = userRepository.findByEmail(username);
        Optional<Person> user = personRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserSS(user.orElse(null));
    }
}
