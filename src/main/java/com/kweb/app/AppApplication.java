package com.kweb.app;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@SpringBootApplication
@EnableMetrics(proxyTargetClass = true)
public class AppApplication extends MetricsConfigurerAdapter {

	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder().requestFactory(SimpleClientHttpRequestFactory.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}