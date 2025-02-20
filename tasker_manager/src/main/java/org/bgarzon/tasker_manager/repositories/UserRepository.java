package org.bgarzon.tasker_manager.repositories;

import org.bgarzon.tasker_manager.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
