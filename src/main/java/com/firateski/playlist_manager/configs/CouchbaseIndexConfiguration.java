package com.firateski.playlist_manager.configs;

import com.couchbase.client.core.error.IndexExistsException;
import com.couchbase.client.java.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster) {
        this.couchbaseCluster = couchbaseCluster;
    }

    @Bean
    public void createIndexes() {
        try {
            couchbaseCluster.query("CREATE INDEX adv_userId_id ON `playlist`(`userId`)");
        }catch (IndexExistsException ex){
            System.out.println("Index already exists!");
        }
    }
}
