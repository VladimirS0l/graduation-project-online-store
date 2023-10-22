package ru.solarev.git.githubservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GithubServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubServiceApplication.class, args);
	}

}
