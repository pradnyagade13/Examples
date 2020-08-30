package com.retail.order.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Order {
	private int orderNumer;
	@Autowired
	private List<Product> product;
	private double totalTax;
	private double orderAmount;
	
	public Order() {
		System.out.println("Order instance created");
	}
	
	public Order(int orderNumer, List<Product> product, double totalTax, double orderAmount) {
		super();
		this.orderNumer = orderNumer;
		this.product = product;
		this.totalTax = totalTax;
		this.orderAmount = orderAmount;
	}
	public int getOrderNumer() {
		return orderNumer;
	}
	public void setOrderNumer(int orderNumer) {
		this.orderNumer = orderNumer;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Override
	public String toString() {
		return "BillInfo [orderNumer=" + orderNumer + ", product=" + product + 
				", totalTax=" + totalTax + ", orderAmount=" + orderAmount + "]";
	}
	
	
}
