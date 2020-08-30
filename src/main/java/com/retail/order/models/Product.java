package com.retail.order.models;

import org.springframework.stereotype.Component;

@Component
public class Product {
	
	private int productId;
	private String productName;
	private int quantity;
	private double productPrice;
	private float tax; 
	private String category;
	private double actualPrice;
	
	public Product() {
		System.out.println("Product instance created");
	}
	
	public Product(int productId, String productName, double productPrice,float tax, String category,int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.tax = tax;
		this.category = category;
		this.quantity = quantity;
		System.out.println("Product instance created");
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}

	@Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", category=" + category +", quantity="+quantity+ ", actualPrice="+actualPrice+"]";
	}
	
	
	
}
