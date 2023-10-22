package ru.service.user.usersservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.service.user.usersservice.exceptions.ResourceNotFoundException;
import ru.service.user.usersservice.model.Role;
import ru.service.user.usersservice.model.User;
import ru.service.user.usersservice.repository.UserRepository;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "UserService::getById", key="#p0", condition="#p0!=null")
    public User getById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "UserService::getByUsername", key="#p0", condition="#p0!=null")
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
    }

    @Override
    @Transactional
    @Caching(put = {
            @CachePut(value = "UserService::getById", key="#p0", condition="#p0!=null"),
            @CachePut(value = "UserService::getByUsername", key="#p0", condition="#p0!=null")
    })
    public User update(Long id, User user) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException("User not found."));
        updateUser.setName(user.getName());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(updateUser);
        return user;
    }

    @Override
    @Transactional
    @Caching(cacheable = {
            @Cacheable(value = "UserService::getById", key="#p0", condition="#p0!=null"),
            @Cacheable(value = "UserService::getByUsername", key="#p0", condition="#p0!=null")
    })
    public User create(User user) {
        System.out.println(user);
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists.");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalStateException(
                    "Password and password confirmation do not match."
            );
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = Set.of(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    @CacheEvict(value = "UserService::getById", key="#p0", condition="#p0!=null")
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

}
