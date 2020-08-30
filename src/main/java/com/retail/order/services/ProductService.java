package com.retail.order.services;

import com.retail.order.models.Product;

public interface ProductService {
	
	void scanProductId(String product);
	Product getProductInfo(int productId);
	void calculateBill();
	void printReceipt(int orderNumber);
}
