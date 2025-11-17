package com.acm.jacksonlibraryexamples;

import com.acm.jacksonlibraryexamples.decoradores.DecoradoresUsage;
import com.acm.jacksonlibraryexamples.deserializacion.DeserializacionMain;
import com.acm.jacksonlibraryexamples.serializacion.SerializacionMain;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class JacksonlibraryexamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JacksonlibraryexamplesApplication.class, args);
	}

	@Bean
	@Order(1)
	ApplicationRunner runner(SerializacionMain serializacionMain){
		return args -> {
			serializacionMain.main();
		};
	}

	@Bean
	@Order(2)
	ApplicationRunner runner2(DeserializacionMain deserializacionMain){
		return args -> {
			deserializacionMain.main();
		};
	}

	@Bean
	@Order(3)
	ApplicationRunner runner3(DecoradoresUsage jsonPropertyUsage){
		return args -> {
			jsonPropertyUsage.main();
		};

	}
}
