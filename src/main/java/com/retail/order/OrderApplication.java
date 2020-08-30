package com.retail.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.retail.order.services.ProductService;
import com.retail.order.services.ProductServiceImpl;

@SpringBootApplication
public class OrderApplication {
	
	
	public static void main(String[] args) throws Exception {

		//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		ProductService service = (ProductServiceImpl) factory.getBean(ProductServiceImpl.class);
		System.out.println("Scanning Items.... ");
		
		service.scanProductId("Item_1");
		service.scanProductId("Item_2");
		service.scanProductId("Item_3");
		service.scanProductId("Item_4");
		service.scanProductId("Item_5");
		service.scanProductId("Item_6");
		service.scanProductId("Item_7");
		
		System.out.println("Calculating order amount....");
		service.calculateBill();

		//SpringApplication.run(OrderApplication.class, args);
	}

}
