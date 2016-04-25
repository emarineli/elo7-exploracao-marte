package br.com.elo7.exploracao.infraestrutura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * Classe que representa a aplicação de exploração em marte.
 * 
 * @author emarineli
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "br.com.elo7.exploracao")
@EnableSpringConfigured
@EnableAspectJAutoProxy
public class ExploracaoMarteApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ExploracaoMarteApplication.class, args);

	}
}
