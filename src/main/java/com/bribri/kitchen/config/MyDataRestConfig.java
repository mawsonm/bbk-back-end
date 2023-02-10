package com.bribri.kitchen.config;

import com.bribri.kitchen.entity.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        cors.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Unit.class);
        config.exposeIdsFor(Ingredient.class);
        config.exposeIdsFor(Recipe.class);
        config.exposeIdsFor(Step.class);
    }
}
