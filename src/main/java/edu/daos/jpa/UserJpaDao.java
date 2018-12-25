package edu.daos.jpa;

import edu.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaDao extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
}
