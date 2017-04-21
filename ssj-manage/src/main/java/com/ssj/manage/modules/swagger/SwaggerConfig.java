package com.ssj.manage.modules.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.ssj.manage.modules.system.SystemPropertyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan("com.ssj.manage.biz.**.controller")
public class SwaggerConfig {

	@Autowired
    private SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {

        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
	}
	
	private ApiInfo apiInfo() {
    	String title = SystemPropertyHandler.getProperty("fbs.swagger.title");
    	String description = SystemPropertyHandler.getProperty("fbs.swagger.description");
    	String serviceUrl = SystemPropertyHandler.getProperty("fbs.swagger.serviceUrl");
    	String contact = SystemPropertyHandler.getProperty("fbs.swagger.contact");
    	String license = SystemPropertyHandler.getProperty("fbs.swagger.fbs.swagger.license");
    	String licenseUrl =SystemPropertyHandler.getProperty("fbs.swagger.licenseUrl");
        ApiInfo apiInfo = new ApiInfo(title, description, serviceUrl,
        		contact, license, licenseUrl);
		return apiInfo;
	}
}
