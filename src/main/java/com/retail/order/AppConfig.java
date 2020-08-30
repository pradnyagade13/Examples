package com.retail.order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.retail.order.models.Order;
import com.retail.order.models.Product;
import com.retail.order.services.ProductService;
import com.retail.order.services.ProductServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.retail.order")
public class AppConfig {

//	@Bean
//	public ProductService getService() {
//		return new ProductServiceImpl();
//	}
//	
//	@Bean
//	public Order getOrder() {
//		return new Order();
//	}
//	
//	@Bean
//	public Product getProduct() {
//		return new Product();
//	}
	
}
