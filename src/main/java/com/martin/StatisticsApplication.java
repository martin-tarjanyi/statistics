package com.martin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories(basePackages = {"com.martin.dataaccess"})
@SpringBootApplication
public class StatisticsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
