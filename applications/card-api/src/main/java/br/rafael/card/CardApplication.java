package br.rafael.card;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}

}
