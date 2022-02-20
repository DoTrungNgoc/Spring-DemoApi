package com.example.hellospringboot.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hellospringboot.model.Product;
import com.example.hellospringboot.repositories.ProductRespository;

//Now connect with mysql using JPA
/*
docker run -d --rm --name mysql-spring-boot-tutorial \
-e MYSQL_ROOT_PASSWORD=123456 \
-e MYSQL_USER=root \
-e MYSQL_PASSWORD=123456 \
-e MYSQL_DATABASE=hellospring \
-p 3309:3306 \
--volume mysql-spring-boot-tutorial-volume:/var/lib/mysql \
mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u root -p

docker run -d --rm --name mysql-spring-boot-tutorial -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_USER=root -e MYSQL_PASSWORD=123456 -e MYSQL_DATABASE=hellospring -p 3309:3306 --volume mysql-spring-boot-tutorial-volume:/var/lib/mysql mysql:latest

* */

@Configuration
public class Database {
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	@Bean
	CommandLineRunner initDatabase(ProductRespository productRespository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
//				Product productA = new Product("MacBook Pro", 2020, 2400.0, "");
//				Product productB = new Product("IPad", 2020, 2500.0, "");
//				logger.info("insert data: "+productRespository.save(productA));
//				logger.info("insert data: "+productRespository.save(productB));
			}
		};
	}
}
