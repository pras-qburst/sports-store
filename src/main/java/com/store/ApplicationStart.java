package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * This the main application class that is the starting point for Application
 * deployment.
 * 
 * @author prasanth
 *
 */
@SpringBootApplication
// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class ApplicationStart {

	/**
	 * Application start main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}

	/**
	 * Configuring Custom Error Pages on server Errors
	 * 
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {

				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED,
						"/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,
						"/404.html");
				ErrorPage error500Page = new ErrorPage(
						HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
				ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST,
						"/400.html");
				ErrorPage defaultError = new ErrorPage("/errorHeaven");

				container.addErrorPages(error401Page, error404Page,
						error500Page, error400Page, defaultError);
			}
		};
	}
}
