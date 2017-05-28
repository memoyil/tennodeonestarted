package com.example.hazelcast.config;

import com.hazelcast.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mehyil on 27.05.2017.
 */
@Configuration
public class HazelcastConfiguration {
    @Bean
    public Config config() {
        return new Config();
    }
}