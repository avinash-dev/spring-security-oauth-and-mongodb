package uk.ac.ebi.pride.oauth.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
    @Bean
    public MongoClient createConnection() {
        //You should put your mongo ip here
        return new MongoClient("127.0.0.1:27017");
    }
}
