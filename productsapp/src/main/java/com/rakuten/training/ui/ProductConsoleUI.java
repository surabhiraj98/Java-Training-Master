package com.rakuten.training.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;


@Component
public class ProductConsoleUI {
	
	ProductService service; // = new ProductServiceImpl(); 
	
	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}
	
	public void createProductWithUI() {
		try(Scanner sc = new Scanner(System.in)){
			System.out.print("Product Name : ");
			String name = sc.nextLine();
			System.out.print("Product Price : ");
			float price = Float.parseFloat(sc.nextLine());
			System.out.print("Product QOH : ");
			int qoh = Integer.parseInt(sc.nextLine());
			
			Product toBeCreated = new Product(name,price,qoh);
			int id = service.createNewProduct(toBeCreated);
			System.out.println("Created Product with ID : "+id);
			System.out.println(toBeCreated);
		}
	}

}
