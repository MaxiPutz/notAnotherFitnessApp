package io.github.maxiputz.springfitness.controller.API.user.struct;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateUserStravaOauthRequest {
    private String clientId;
    private String clientSecret;

    public UpdateUserStravaOauthRequest(){}
}
