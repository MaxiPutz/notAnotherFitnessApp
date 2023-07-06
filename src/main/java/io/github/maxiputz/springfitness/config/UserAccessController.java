package io.github.maxiputz.springfitness.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.OrPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.github.maxiputz.springfitness.database.Entity.User.UserService;

@Component
public class UserAccessController implements UserDetailsService {

    @Autowired
    private UserService userService;

    private final static List<UserDetails> USERS = Arrays.asList(
            new User("admin", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("admin1", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_User"))),
            new User("admin2", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_User"))),
            new User("admin3", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_User"))),
            new User("admin4", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_User"))),
            new User("admin5", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_User")))

    );

    public List<UserDetails> getUsers() {
        List<UserDetails> users = userService.getAllUser().stream()
                .map(ele -> (UserDetails) new User(ele.getUsername(), ele.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_User"))))
                .toList();

        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("try to load user");
        var filteredUsers = this.getUsers()
                .stream().filter(u -> u.getUsername().equals(username)).findAny(); // USERS.stream().filter(u ->
                                                                                   // u.getUsername().equals(username)).findAny();
        System.out.println(filteredUsers.isPresent());

        if (filteredUsers.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return filteredUsers.get();
    }

    public Optional<UserDetails> getUserFromUsername(String username) {
        return getUsers().stream().filter(ele -> ele.getUsername().equals(username)).findAny();
    }

    public Optional<io.github.maxiputz.springfitness.database.Entity.User.User> loadUserIfPasswordIsCorrect(
            io.github.maxiputz.springfitness.database.Entity.User.User user) {

        var u = loadUserByUsername(user.getUsername());

        return userService.getUserByUsername(u.getUsername());

    }

}