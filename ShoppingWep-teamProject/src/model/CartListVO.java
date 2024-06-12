package model;

import java.text.NumberFormat;
import java.util.Locale;

public class CartListVO {
	private int order_num;
	private String pdCode;
	private String pdName;
	private String brand;
	private String category;
	private int quantity;
	private int salesAmount;
	
	public CartListVO() {
		super();
	}
	public CartListVO(int order_num, String pdCode, String pdName, String brand, String category, int quantity,
			int salesAmount) {
		super();
		this.order_num = order_num;
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.brand = brand;
		this.category = category;
		this.quantity = quantity;
		this.salesAmount = salesAmount;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getPdCode() {
		return pdCode;
	}
	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}
	private String formatPrice(int price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(price);
    }
	private String truncate(String value, int length) {
        if (value.length() > length) {
            return value.substring(0, length - 3) + "...";
        } else {
            return value;
        }
    }
		
	public String toString() {
        return String.format("%-5d %-10s %-15s %-15s %-15s %10d %15s",
                order_num, pdCode,
                truncate(pdName, 15),
                truncate(brand, 15),
                truncate(category, 15),
                quantity,
                formatPrice(salesAmount));
    }

}
