package org.luoxi.ibd.config;

import org.luoxi.ibd.services.QuoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration file for services package
 *
 * @author luoxi
 *
 */
@Configuration
public class ServicesConfig {

	@Bean(name="quoteService")
	public QuoteService quoteService() {
		return new QuoteService();
	}
}
