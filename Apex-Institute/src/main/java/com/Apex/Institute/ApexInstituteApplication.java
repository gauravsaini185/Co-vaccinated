package com.Apex.Institute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ApexInstituteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApexInstituteApplication.class, args);
	}

//	@Bean
//	public ViewResolver viewResolver() {
//	ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//	templateResolver.setTemplateMode("XHTML");
//	templateResolver.setPrefix("views/");
//	templateResolver.setSuffix(".html");
//
//	SpringTemplateEngine engine = new SpringTemplateEngine();
//	engine.addDialect(new LayoutDialect());
//	engine.addDialect(new SpringSecurityDialect());
//	engine.setTemplateResolver(templateResolver);
//
//	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//	viewResolver.setTemplateEngine(engine);
//	return viewResolver;
//
//	}
}
