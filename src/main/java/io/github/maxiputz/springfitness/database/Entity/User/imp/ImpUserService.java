package io.github.maxiputz.springfitness.database.Entity.User.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.maxiputz.springfitness.config.JWTUtils;
import io.github.maxiputz.springfitness.controller.API.user.struct.UpdateUserStravaOauthRequest;
import io.github.maxiputz.springfitness.database.Entity.User.User;
import io.github.maxiputz.springfitness.database.Entity.User.UserRepository;
import io.github.maxiputz.springfitness.database.Entity.User.UserService;

@Service
public class ImpUserService implements UserService {
    private UserRepository repository;
    private JWTUtils jwtUtils;

    @Autowired
    void jwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    void repository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User createUser(User user) {

        return repository.save(user);
    }

    @Override
    public User myGet() {
        return repository.getAllUser().stream().findFirst().get();
    }

    @Override
    public User setOauthInfo(User user) {

        repository.setOauthInfo(user.getId(), user.getClientId(), user.getClientSecret());

        return user;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return repository.getUserByUsername(username).stream().findAny();
    }

    @Override
    public void setStravaData(User user, UpdateUserStravaOauthRequest request) {
        System.out.println(user.getUsername());
        System.out.println(user.getId());
        System.out.println(request.getClientID());
        System.out.println(request.getClientSecret());
        repository.setOauthInfo(user.getId(), request.getClientID(), request.getClientSecret());
    }

    @Override
    public void deleteUser(User user) {
        repository.delete(user);
    }

    @Override
    public Optional<User> getUserFromauthorizationHeader(String authorizationHeader) {
        var jwtToke = authorizationHeader.substring(7);
        var username = jwtUtils.extractUsername(jwtToke);
        return getUserByUsername(username);
    }

}
