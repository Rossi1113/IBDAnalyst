package org.luoxi.ibd.config;

import org.luoxi.ibd.exception.QuoteException;
import org.luoxi.ibd.model.TimePeriod;
import org.luoxi.ibd.services.QuoteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBeanTest {

	public static void main(String[] args) throws QuoteException {
		/*
		//Old way to read config from xml file
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"SpringBeans.xml"); */

		// New way to read config from JavaConfig
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		QuoteService qs = (QuoteService) context.getBean("quoteService");

		System.out.println(qs.getHistoryQuotes("CRM", TimePeriod.ONE_MONTH));
	}

}
