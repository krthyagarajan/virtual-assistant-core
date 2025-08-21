package com.trainings.virtual_assistant.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.UUID;

@Projection(name = "userResponseProjection", types = UserEntity.class)
public interface UserViewProjection {
    UUID getId();
    String getUsername();
    String getDisplayName();
    LocalDate getDateOfBirth();
    String getEmail();
    String getMobile();
    Boolean getActive();
}
