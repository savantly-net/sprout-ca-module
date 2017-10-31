package net.savantly.ca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.savantly.ca.configuration.CaConfiguration;

@Configuration
public class CaModule {

	@Bean
	public CaConfiguration caConfiguration() {
		return new CaConfiguration();
	}
}
