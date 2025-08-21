package com.trainings.virtual_assistant.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource(path="user", excerptProjection = UserViewProjection.class)
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsernameAndPasswordAndActive(String username, String password, Boolean active);
}
