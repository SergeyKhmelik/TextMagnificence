package config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

/**
 * @author Koloturka
 * @version 28.08.2015 11:40
 */

@EnableSwagger
public class SpringMVCSwaggerConfig {

	private static final List<String> URL_PATTERNS = Arrays.asList("/stories", "/stories/.*");

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo())
				.includePatterns("/.*");
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
