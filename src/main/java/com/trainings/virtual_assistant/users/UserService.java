package com.trainings.virtual_assistant.users;

import com.trainings.virtual_assistant.users.exception.DuplicateUserException;
import com.trainings.virtual_assistant.users.exception.UserValidationException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUser(UUID userId){

        UserEntity user = userRepository.findById(userId)
                          .orElseThrow(() -> new RuntimeException("User Id not found"));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto createUser( UserDto userDto){
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        try{
            userEntity.setActive(true);
            userEntity = userRepository.save(userEntity);
        } catch(DataIntegrityViolationException e){
            throw new DuplicateUserException(String.format("User name [%s] is already is used.  Please provide new username.", userDto.getUsername()));
        }
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserDto updateUser(UUID userId, Map<String, Object> requestMap) {

        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            final UserEntity entity = userEntity.get();
            requestMap.forEach((key, value)->{
                Field field = ReflectionUtils.findFieldIgnoreCase(UserEntity.class, key);
                if(field != null){
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, entity, value);
                }
            });
            userRepository.save(entity);

        } else {
            throw new UserValidationException("Not able to find the user");
        }
        return modelMapper.map(userEntity.get(), UserDto.class);
    }


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
