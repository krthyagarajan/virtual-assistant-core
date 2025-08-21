package com.trainings.virtual_assistant.users;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public Optional<UserDto> validateActiveUser(String username, String password) {

        Optional<UserEntity> userEntity = userRepository.findByUsernameAndPasswordAndActive(username, password, true);

        UserDto userDto;

        if (userEntity.isPresent()) {
            userDto = modelMapper.map(userEntity.get(), UserDto.class);
        } else {
            throw new UserValidationException("Username/password is incorrect or user not found.");
        }
        return Optional.of(userDto);
    }
}
