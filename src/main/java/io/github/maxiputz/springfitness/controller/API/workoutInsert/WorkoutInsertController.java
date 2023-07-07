package io.github.maxiputz.springfitness.controller.API.workoutInsert;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.maxiputz.springfitness.CSVManager.CSVManager;
import io.github.maxiputz.springfitness.controller.API.workoutInsert.struct.MetadataCreateRequest;
import io.github.maxiputz.springfitness.controller.API.workoutInsert.struct.WorkoutCreateRequest;
import io.github.maxiputz.springfitness.database.Entity.Metadata.MetadataService;
import io.github.maxiputz.springfitness.database.Entity.User.UserService;
import io.github.maxiputz.springfitness.database.Entity.UsersMetadatas.UsersMetadataService;

@CrossOrigin(origins = "userdb1.cwrp9quarykg.eu-central-1.rds.amazonaws.com")
@RequestMapping("/api")
@RestController
public class WorkoutInsertController {

    @Autowired
    UserService userService;

    @Autowired
    MetadataService metadataService;

    @Autowired
    UsersMetadataService usersMetadataService;

    @PostMapping("/metadataInsert")
    public ResponseEntity<String> metadataInser(
            @RequestHeader("Authorization") String authString,
            @RequestBody MetadataCreateRequest req) {

        System.out.println("/metadataInsert");
        var u = userService.getUserFromauthorizationHeader(authString);

        if (u.isEmpty()) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("not found");
        }

        System.out.println("try to parse csv");
        var metadatas = CSVManager.toMetadataArr(req.getHeader(), req.getCsv());
        System.out.println("parse success");

        metadatas = metadataService.saveAll(metadatas);

        var tmp = usersMetadataService.saveAll(u.get(), metadatas);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/workoutDataInsert")
    public ResponseEntity<String> workoutDataInsert(
            @RequestHeader("Authorization") String authString,
            @RequestBody WorkoutCreateRequest request) {
        var u = userService.getUserFromauthorizationHeader(authString);

        System.out.println(request.getCsv());

        if (u.isEmpty()) {
            System.out.println("user notfound");
            throw new UsernameNotFoundException("not found");
        }

        var res = metadataService.setWorkoutdata(u.get(), request);

        if (res.isEmpty()) {
            System.out.println("metadata not found");
            throw new UsernameNotFoundException(authString);
        }

        return ResponseEntity.ok().build();
    }

}
