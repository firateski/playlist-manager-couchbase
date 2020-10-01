package com.firateski.playlist_manager.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "couchbase")
public class CouchbaseProperties {
    private String host;
    private String userName;
    private String password;
    private String bucketName;
}
