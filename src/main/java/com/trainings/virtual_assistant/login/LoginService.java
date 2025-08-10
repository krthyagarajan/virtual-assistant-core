package com.trainings.virtual_assistant.login;

import com.trainings.virtual_assistant.login.exception.JwtSigningException;
import com.trainings.virtual_assistant.services.JsonTokenService;
import com.trainings.virtual_assistant.users.UserDto;
import com.trainings.virtual_assistant.users.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class LoginService {

    private UserService userService;
    private JsonTokenService jsonTokenService;

    public String login(String username, String password) {

        Optional<UserDto> user = userService.validateActiveUser(username, password);

        String accessToken = user.map(jsonTokenService::generateAccessToken)
                                .orElseThrow( () -> new JwtSigningException("Jwt cannot be signed when access token is created"));
        log.info("Access token generated successfully : {}", accessToken);

        return accessToken;
    }
}
