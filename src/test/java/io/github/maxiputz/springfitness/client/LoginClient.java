package io.github.maxiputz.springfitness.client;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.github.maxiputz.springfitness.controller.API.user.struct.LoginResponse;
import io.github.maxiputz.springfitness.controller.API.user.struct.UpdateUserStravaOauthRequest;

public class LoginClient {
    private String url;
    private RestTemplate restTemplate;

    public LoginClient(String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
    }



    public ResponseEntity<String> createUser(ReqUser user) {
        String api = url + "/api/userCreate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String reqBody = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", user.getUsername(),
                user.getPassword());

        System.out.println(reqBody);
        HttpEntity<String> req = new HttpEntity<>(reqBody, headers);

        return restTemplate.postForEntity(api, req, String.class);
    }

    public ResponseEntity<LoginResponse> userLogin(ReqUser user) {
        String api = url + "/api/login";

        System.out.println(api);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String reqBody = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", user.getUsername(),
                user.getPassword());

        System.out.println(reqBody);
        HttpEntity<String> req = new HttpEntity<>(reqBody, headers);

        return restTemplate.postForEntity(api, req, LoginResponse.class);
    }

    public ResponseEntity<String> setOauth(String jwString, UpdateUserStravaOauthRequest req) {
        String api = url + "/api/userSetStravaData";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwString);

        String reqBody = String.format("{\"clientId\":\"%s\",\"clientSecret\":\"%s\"}", req.getClientID(),
                req.getClientSecret());

        System.out.println(reqBody);
        HttpEntity<String> request = new HttpEntity<>(reqBody, headers);

        return restTemplate.postForEntity(api, request, String.class);

    }

    public ResponseEntity<LoginResponse> userReload(String jwt) {
        String api = url + "/api/userReload";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(jwt);

        HttpEntity<String> req = new HttpEntity<>(headers);

        return restTemplate.exchange(api, HttpMethod.GET, req, LoginResponse.class);
    }

}
