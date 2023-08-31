package com.security.DemoSecurity.Services;

import com.security.DemoSecurity.Entity.Person;
import com.security.DemoSecurity.Repo.PersonRepo;
import com.security.DemoSecurity.Security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private PersonRepo personRepo;

    @Autowired
    public PersonDetailsService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepo.findPersonByUsername(username);

        if (person.isEmpty())
            throw new UsernameNotFoundException("Пользователь не найден!");

        return new PersonDetails(person.get());
    }

    public Optional<Person> checkByUsername(String username) {
        return personRepo.findPersonByUsername(username);
    }
}
