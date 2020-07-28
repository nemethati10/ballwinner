package com.promotion.ballwinner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BallWinnerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BallWinnerApplication.class, args);
    }

}

