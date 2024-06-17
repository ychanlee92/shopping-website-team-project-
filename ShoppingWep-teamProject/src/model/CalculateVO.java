package model;

import java.sql.Timestamp;

public class CalculateVO {
	private int calculate_num;
	private String pdcode;
	private int quantity;
	private int salesAmount;
	private String paymentDate;

	public String getPdcode() {
		return pdcode;
	}

	public void setPdcode(String pdcode) {
		this.pdcode = pdcode;
	}

	public int getCalculate_num() {
		return calculate_num;
	}

	public void setCalculate_num(int calculate_num) {
		this.calculate_num = calculate_num;
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

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void printDateList() {
		System.out
				.print(String.format("%11s %11d %15s", paymentDate, quantity, String.format("%,d", salesAmount) + "원"));
	}

	public void printProducteList() {
//		
		System.out.print(String.format("%11s %11d %15s", pdcode, quantity, String.format("%,d", salesAmount) + "원"));
	}

}