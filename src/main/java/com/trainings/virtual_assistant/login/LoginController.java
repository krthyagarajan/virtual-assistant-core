package com.trainings.virtual_assistant.login;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MultiValueMap<String, String> loginRequest) {

        String username = loginRequest.getFirst("username");
        String password = loginRequest.getFirst("password");

        String accessToken = loginService.login(username, password);

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }

}
