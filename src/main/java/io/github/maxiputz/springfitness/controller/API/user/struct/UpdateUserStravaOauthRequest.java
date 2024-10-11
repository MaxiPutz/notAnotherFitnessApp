package io.github.maxiputz.springfitness.controller.API.user.struct;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateUserStravaOauthRequest {
    private String clientID;
    private String clientSecret;

    public UpdateUserStravaOauthRequest(){}
}
