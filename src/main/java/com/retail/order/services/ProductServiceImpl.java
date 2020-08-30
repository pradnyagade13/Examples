package com.retail.order.services;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.retail.order.models.Order;
import com.retail.order.models.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	private static Map<Integer,Product> map = new HashMap();
	private static Map<String,Float> taxMap = new HashMap();
	private static List<Product> purchasedProducts = new ArrayList();
	private static int orderNumber=1;
	
	@Autowired
	private Order order ;
	
	DecimalFormat df = new DecimalFormat("###.##");

	public ProductServiceImpl() {	}
	
	static {
		fillProductMap();
		fillTaxMap();
	}
	
	public static void fillProductMap() {
		map.put(47, new Product(47,"Parle-G",50.00,0,"C",2));
		map.put(41, new Product(41,"Nirma Soap",70.00,0,"B",1));
		map.put(42, new Product(42,"Wheat Atta",75.50,0,"A",3));
		map.put(43, new Product(43,"Maggie",66.00,0,"C",2));
		map.put(44, new Product(44,"Shampoo",80.00,0,"B",4));
		map.put(45, new Product(45,"Chips",20.00,0,"C",2));
		map.put(46, new Product(46,"Papad",88.00,0,"A",1));
	}
	
	public static void fillTaxMap() {
		taxMap.put("A", 10f);
		taxMap.put("B", 20f);
		taxMap.put("C", 0f);
	}
	
	@Override
	public void scanProductId(String productName) {
		
		InputStream barCodeInputStream;
		
		Result result=null;
		try {
			barCodeInputStream = new FileInputStream("C:\\Users\\Pradnya\\Pictures\\order\\order\\barcodes\\"+productName+".PNG");
			BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
			LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Reader reader = new MultiFormatReader();
			result = reader.decode(bitmap);
		}catch (Exception e) {
			// TODO: handle exception
		}

		int prodId = Integer.parseInt(result.getText());
		Product productInfo = getProductInfo(prodId);
		purchasedProducts.add(productInfo);
	}
	
	@Override
	public Product getProductInfo(int productId){
		Product productInfo = map.get(productId);
		return productInfo;
	}
	
	@Override
	public void calculateBill() {
	    float taxAmt=0f;
		double productprice=0;
		double orderAmount =0;
		double totalTax=0;
		double actualPrice;
		for(Product product : purchasedProducts) {
			taxAmt = taxMap.get(product.getCategory());
			product.setTax(taxAmt);
			actualPrice = product.getProductPrice()*product.getQuantity();
			productprice = actualPrice+(actualPrice*taxAmt/100);
			if(!product.getCategory().equals("C")) {
				totalTax += (productprice-product.getProductPrice());
			}
			product.setActualPrice(actualPrice);
			orderAmount = orderAmount+productprice;
		}
		order.setProduct(purchasedProducts);
		order.setOrderAmount(orderAmount);
		order.setOrderNumer(orderNumber);
		order.setTotalTax(totalTax);
		printReceipt(order.getOrderNumer());
		orderNumber++;
	}
	
	@Override
	public void printReceipt(int orderNumber) {
		System.out.println("Printing Receipt....");         
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Order No.  : "+orderNumber);
		System.out.println("Order Date : "+new Date());
	    System.out.printf("\n%10s %18s %12s %15s %12s %10s", "Product Name", "Product Price", "Quantity", "Actual Price" ,"Category", "Tax");
	    System.out.println();
	    System.out.println("---------------------------------------------------------------------------------------");
	    for(Product product: order.getProduct()){
	        System.out.format("%10s %15s %15d %15s %12s %12s",
	                product.getProductName(), product.getProductPrice(),product.getQuantity(),df.format(product.getActualPrice()), product.getCategory(), df.format(product.getTax()));
	        System.out.println();
	    }
	    System.out.println("---------------------------------------------------------------------------------------");
	    System.out.format("   Tax : %76s", df.format(order.getTotalTax()));
	    System.out.format("\n   Total Order Amout : %62s", df.format(order.getOrderAmount()));
	    System.out.println("\n---------------------------------------------------------------------------------------");
	}
	
}
