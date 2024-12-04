package com.sustentabilidad.sust;

import com.sustentabilidad.sust.principal.Principal;
import com.sustentabilidad.sust.repository.CompuestosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class SustApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(SustApplication.class).headless(false).run(args);
		Principal principal = context.getBean(Principal.class);
	}
}
