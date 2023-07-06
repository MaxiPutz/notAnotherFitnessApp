package io.github.maxiputz.springfitness.controller.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.maxiputz.springfitness.database.Entity.User.User;
import io.github.maxiputz.springfitness.database.Entity.User.UserService;

@RestController
@RequestMapping("/api")
public class APIController {

      private UserService userService;
      
      @Autowired
      public void userService(UserService userService) {
        this.userService = userService;
      }

    @PostMapping("/create")
    ResponseEntity<User> create(@RequestBody User user) {
        var u = userService.createUser(user);
        return ResponseEntity.ok().body(u);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<User> getId(@PathVariable("id") Long id) {
        var us = userService.getUserById(id);
        if (us.isEmpty()) {
            return ResponseEntity.badRequest().body(new User());
        }
        return ResponseEntity.ok().body(us.get());
    }

    @PostMapping("setOauthInfos") 
    ResponseEntity<User> setOauthInfos(@RequestBody User user) {
        userService.setOauthInfo(user);
        return ResponseEntity.ok().body(user);
    }
}
