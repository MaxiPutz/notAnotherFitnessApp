package io.github.maxiputz.springfitness.database.Entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.maxiputz.springfitness.controller.API.user.struct.UpdateUserStravaOauthRequest;

@Service
public interface UserService {
    public List<User> getAllUser();
    public Optional<User> getUserById(Long id);
    public Optional<User> getUserFromauthorizationHeader(String authorizationHeader);

    public Optional<User> getUserByUsername(String username);

    public User createUser(User user);

    public User myGet();

    public User setOauthInfo(User user);

    public void setStravaData(User user, UpdateUserStravaOauthRequest request);
    public void deleteUser(User user);
}
