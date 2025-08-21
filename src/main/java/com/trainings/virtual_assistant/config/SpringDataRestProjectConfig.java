package com.trainings.virtual_assistant.config;

import com.trainings.virtual_assistant.users.UserEntity;
import com.trainings.virtual_assistant.users.UserViewProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class SpringDataRestProjectConfig implements RepositoryRestConfigurer {

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(UserEntity.class);
        config.getProjectionConfiguration().addProjection(UserViewProjection.class);
    }
}
