package com.thanh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WebsocketThanhApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WebsocketThanhApplication.class, args);
		String[] dsBean = context.getBeanDefinitionNames();
		int i=1;
		for(String bean:dsBean) {
			System.out.print("Bean thứ "+i+":");
			System.out.println(bean);
			i++;
		}

	}

}
