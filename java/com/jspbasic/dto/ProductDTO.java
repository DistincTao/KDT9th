package com.jspbasic.dto;

public class ProductDTO {
	private String name;
	private String color;
	private int qty;
	private int price;
	private int totalPrice;
	
	public ProductDTO(String name, String color, int qty, int price) {
		super();
		this.name = name;
		this.color = color;
		this.qty = qty;
		this.price = price;
		this.totalPrice = qty * price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}

	public int getQty() {
		return qty;
	}

	public int getPrice() {
		return price;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		return "상품명 : " + name + ", 수량 : " + qty + ", 가격 : " + price + ", 색상 : " + color;
	}

	
}
