package com.security.DemoSecurity.Services;

import com.security.DemoSecurity.Entity.Person;
import com.security.DemoSecurity.Repo.PersonRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PasswordEncoder passwordEncoder;
    private PersonRepo personRepo;
    @Autowired
    public PersonService(PersonRepo personRepo, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.personRepo = personRepo;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepo.save(person);
    }
}
