package com.security.DemoSecurity.Repo;

import com.security.DemoSecurity.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    public Optional<Person> findPersonByUsername (String username);
}
