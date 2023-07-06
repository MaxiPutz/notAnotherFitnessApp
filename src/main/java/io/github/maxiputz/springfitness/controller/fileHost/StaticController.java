package io.github.maxiputz.springfitness.controller.fileHost;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.github.maxiputz.springfitness.CSVManager.CSVManager;
import io.github.maxiputz.springfitness.controller.fileHost.structs.CSVForReact;
import io.github.maxiputz.springfitness.controller.fileHost.structs.Esport;
import io.github.maxiputz.springfitness.database.Entity.Metadata.Metadata;
import io.github.maxiputz.springfitness.database.Entity.Metadata.MetadataService;
import io.github.maxiputz.springfitness.database.Entity.User.UserService;

@CrossOrigin(origins = "http://localhost:8081/")
@RestController
public class StaticController {
    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    MetadataService metadataService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String loginPage() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:static/vue.html");

        String html = new String(resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8);

        return html;
    }

    @GetMapping("/OAuthFlow*")
    public String OAuthFlow() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:static/vue.html");

        String html = new String(resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8);

        return html;
    }

    @GetMapping("/api/runningApp")
    public ResponseEntity<CSVForReact> getRunningAppData(
            @RequestHeader("Authorization") String authString) {
        var u = userService.getUserFromauthorizationHeader(authString);

        List<Metadata> metadatas = metadataService.getAllMetadataFromUserId(u.get()).stream()
                .filter(e -> e.getSport().equals(Esport.Run.name()))
                .filter(ele -> ele.getCount() > 0).toList();

        List<String> workoutdatas = metadatas.stream().map(ele -> ele.getWorkoutDataCSV()).toList();
        String w = String.join("\n", workoutdatas);

        metadatas = metadatas.stream().map(ele -> ele.metadataWithoutCSV()).toList();
        var m = CSVManager.fromMetadataArr("id,lat,long,timerTime,power,speed,heartRate", metadatas);
        return ResponseEntity.ok().body(new CSVForReact(w, m));
    }

    @GetMapping("/api/RideApp")
    public ResponseEntity<CSVForReact> getRideAppData(
            @RequestHeader("Authorization") String authString) {
        var u = userService.getUserFromauthorizationHeader(authString);

        List<Metadata> metadatas = metadataService.getAllMetadataFromUserId(u.get()).stream()
                .filter(e -> e.getSport().equals(Esport.Ride.name()))
                .filter(ele -> ele.getCount() > 0).toList();

        List<String> workoutdatas = metadatas.stream().map(ele -> ele.getWorkoutDataCSV()).toList();
        String w = String.join("\n", workoutdatas);

        metadatas = metadatas.stream().map(ele -> ele.metadataWithoutCSV()).toList();
        var m = CSVManager.fromMetadataArr("id,lat,long,timerTime,power,speed,heartRate", metadatas);
        return ResponseEntity.ok().body(new CSVForReact(w, m));
    }
}
