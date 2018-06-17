package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@SpringBootApplication
public class DemoApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

//	@Bean
//	public ViewResolver internalResourceViewResolver() {
//		InternalResourceViewResolver bean = new InternalResourceViewResolver();
//		bean.setViewClass(JstlView.class);
//		bean.setPrefix("/WEB-INF/");
//		bean.setSuffix(".jsp");
//		return bean;
//	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}
}
