package com.example.demo;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.AbstractScmEnvironmentRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}

@RestController
class Eww {

	@Autowired
	AbstractScmEnvironmentRepository scmRepo;

	@Autowired
	ResourceLoader resourceLoader;

	@RequestMapping("/ew")
	public String ew() throws IOException {
		Resource resource = this.resourceLoader.getResource(scmRepo.getUri());
		System.out.println(Paths.get(resource.getURI()));
		return String.valueOf(resource instanceof FileSystemResource);
	}
}
