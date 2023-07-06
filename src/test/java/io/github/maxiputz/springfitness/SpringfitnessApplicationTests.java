package io.github.maxiputz.springfitness;

import static org.mockito.Mockito.timeout;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.maxiputz.springfitness.client.LoginClient;
import io.github.maxiputz.springfitness.client.ReqUser;
import io.github.maxiputz.springfitness.controller.API.user.struct.UpdateUserStravaOauthRequest;

@SpringBootTest
class SpringfitnessApplicationTests {

	String url = "http://localhost:8080";

	@Test
	void login() {

		LoginClient client = new LoginClient(url);

		client.createUser(new ReqUser("admin", "admin"));

		var res1 = client.userLogin(new ReqUser("admin", "admin"));

		client.userReload(res1.getBody().getMyLoginToken());

		var res2 = client.setOauth(res1.getBody().getMyLoginToken(), new UpdateUserStravaOauthRequest("foo", "bar"));

		System.out.println(res1.getBody().getMyLoginToken());
	}

}
