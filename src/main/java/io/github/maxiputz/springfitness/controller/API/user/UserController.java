package io.github.maxiputz.springfitness.controller.API.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.maxiputz.springfitness.config.JWTUtils;
import io.github.maxiputz.springfitness.config.UserAccessController;
import io.github.maxiputz.springfitness.controller.API.user.struct.LoginResponse;
import io.github.maxiputz.springfitness.controller.API.user.struct.UpdateUserStravaOauthRequest;
import io.github.maxiputz.springfitness.database.Entity.Metadata.MetadataService;
import io.github.maxiputz.springfitness.database.Entity.User.User;
import io.github.maxiputz.springfitness.database.Entity.User.UserService;
import jakarta.persistence.EntityExistsException;

@RestController
//@CrossOrigin(origins = "userdb1.cwrp9quarykg.eu-central-1.rds.amazonaws.com")
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class UserController {

    private JWTUtils jwtUtils;

    @Autowired
    public void jwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    private UserAccessController userAccessController; // used for the login stuff

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private UserService userService;

    @PostMapping("/userSetStravaData")
    ResponseEntity<String> userSetStravaData(@RequestBody UpdateUserStravaOauthRequest request,
            @RequestHeader("Authorization") String authorizationHeader) {

        System.out.println("/userSetStravaData");

        System.out.println(request.getClientID());
        System.out.println();
        System.out.println(request.getClientSecret());
        System.out.println();
        System.out.println();

        var user = this.getUserFromAuthHeader(authorizationHeader);

        userService.setStravaData(user, request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/userCreate")
    ResponseEntity<String> userCreate(@RequestBody User req) {
        System.out.println("/userCreate");
        System.out.println(req.getClientId());
        System.out.println(req.getClientSecret());
        // System.out.println(req.ge\);
        var user = userAccessController.getUserFromUsername(req.getUsername());
        if (user.isPresent()) {
            throw new EntityExistsException(user.get().getUsername());
        }

        userService.createUser(req);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(
            @RequestBody User req) {

        System.out.println("in the login");
        System.out.println(req.getPassword());
        System.out.println(req.getUsername());

        UserDetails _user = userAccessController.loadUserByUsername(req.getUsername());
        if (_user == null) {
            throw new UsernameNotFoundException(req.getUsername());
        }

        var user = userService.getUserByUsername(_user.getUsername());
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(req.getUsername());
        }

        if (!user.get().getPassword().equals(req.getPassword())) {
            System.out.println("password is not correct");
            throw new UsernameNotFoundException(req.getUsername());
        }

        System.out.println(user.get().getUsername());
        var token = jwtUtils.generateToken(_user);

        var metadatas = metadataService.getAllMetadataFromUserId(user.get()).stream()
                .map(ele -> ele.metadataWithoutCSV())
                .toList();
        System.out.println(metadatas.size());

        var res = new LoginResponse(token, metadatas, user.get());

        return ResponseEntity.ok().body(res);

    }

    @GetMapping("/userReload")
    ResponseEntity<LoginResponse> reload(@RequestHeader("Authorization") String authorizationHeader) {
        System.out.println("userReload beginn");
        System.out.println(authorizationHeader);
        var jwtToken = authorizationHeader.substring(7);
        System.out.println(jwtToken);
        var username = jwtUtils.extractUsername(jwtToken);
        System.out.println(username);
        var ud = userAccessController.loadUserByUsername(username);
        System.out.println(ud.getUsername());
        System.out.println(ud.getPassword());
        var isValid = jwtUtils.isTokenValid(jwtToken, ud);

        if (!isValid) {
            throw new UsernameNotFoundException(jwtToken);
        }

        var user = userService.getUserByUsername(username);

        if (user.isEmpty()) {
            System.out.println("user not found");
            throw new UsernameNotFoundException(jwtToken);
        }

        var metadatas = metadataService.getAllMetadataFromUserId(user.get()).stream()
                .map(ele -> ele.metadataWithoutCSV()).toList();


        var res = new LoginResponse(jwtToken, metadatas, user.get());

        return ResponseEntity.ok().body(res);
    }

    private User getUserFromAuthHeader(String authorizationHeader) {
        var jwtToke = authorizationHeader.substring(7);
        var username = jwtUtils.extractUsername(jwtToke);
        var u = userService.getUserByUsername(username);
        return u.get();
    }
}
