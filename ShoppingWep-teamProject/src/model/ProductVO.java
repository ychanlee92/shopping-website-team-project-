package model;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductVO {
	private String pdCode;
	private String pdName;
	private String brand;
	private String category;
	private int price;
	
	
	public ProductVO() {
		super();
	}

	public ProductVO(String pdCode, String pdName, String brand, String category, int price) {
		super();
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.brand = brand;
		this.category = category;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	private String truncate(String value, int length) {
        if (value.length() > length) {
            return value.substring(0, length - 3) + "...";
        } else {
            return value;
        }
    }
	
	private String formatPrice(int price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(price);
    }
	
	@Override
    public String toString() {
        return String.format("%-5s %-15s %-15s %-15s %10s",
                             pdCode,
                             truncate(pdName, 9),
                             truncate(brand, 7),
                             truncate(category, 10),
                             formatPrice(price));
    }
}
