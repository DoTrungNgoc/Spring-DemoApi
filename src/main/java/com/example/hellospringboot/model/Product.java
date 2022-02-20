package com.example.hellospringboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblProduct")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique = true, length = 300)
	private String productName;
	private int year;
	private Double price;
	private String url;
	
	public Product() {
		super();
	}

	public Product(String productName, int year, Double price, String url) {
		this.productName = productName;
		this.year = year;
		this.price = price;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", year=" + year + ", price=" + price + ", url="
				+ url + "]";
	}
	
	
}
