package ru.service.user.usersservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.service.user.usersservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

}
