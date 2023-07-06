package io.github.maxiputz.springfitness.controller.API.user.struct;

import java.util.ArrayList;
import java.util.List;

import io.github.maxiputz.springfitness.database.Entity.Metadata.Metadata;
import io.github.maxiputz.springfitness.database.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String myLoginToken;

    private List<Metadata> metadatas;

    private String username;
    private String clientId;
    private String clientSecret;

    public LoginResponse(String myLoginToken, String username, String clientId, String clientSecret) {
        this.myLoginToken = myLoginToken;
        this.username = username;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.metadatas = new ArrayList<>();
    }

    public LoginResponse(String myLoginToken, User user) {
        this.myLoginToken = myLoginToken;
        this.username = user.getUsername();
        this.clientId = user.getClientId();
        this.clientSecret = user.getClientSecret();
        this.metadatas = new ArrayList<>();
    }

    public LoginResponse(String myLoginToken,  List<Metadata> metadata, User user) {
        this.myLoginToken = myLoginToken;
        this.username = user.getUsername();
        this.clientId = user.getClientId();
        this.clientSecret = user.getClientSecret();
        this.metadatas = metadata;
    }

    public LoginResponse() {}
}
