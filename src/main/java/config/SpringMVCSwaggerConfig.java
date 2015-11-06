package config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@EnableSwagger
public class SpringMVCSwaggerConfig {

	private static final String URL_PATTERNS = "/.*";

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo())
				.includePatterns(URL_PATTERNS);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Text Magnificence project documentation",
				"Text Magnificence backend RESTful project API documentation, using Swagger.",
				"Terms of service",
				"koloturkaaa@gmail.com",
				"Licence type",
				"Licence url"
		);
	}

}
