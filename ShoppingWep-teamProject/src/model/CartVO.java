package model;

import java.sql.Timestamp;
import java.util.Date;

public class CartVO {
	private int orderNo;
	private String pdCode;
	private String userId;
	private int quantity;
	private int salesAmount;
	private int isPayment;
	private Timestamp paymentDate;

	public CartVO() {
		super();
	}
	public CartVO(int orderNo, String pdCode, String userId, int quantity, int salesAmount, int isPayment,
			Timestamp paymentDate) {
		super();
		this.orderNo = orderNo;
		this.pdCode = pdCode;
		this.userId = userId;
		this.quantity = quantity;
		this.salesAmount = salesAmount;
		this.isPayment = isPayment;
		this.paymentDate = paymentDate;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getPdCode() {
		return pdCode;
	}
	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(int isPayment) {
		this.isPayment = isPayment;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Override
	public String toString() {
		return "[" + orderNo + ", \t" + pdCode + ", \t" + userId + ", \t" + quantity
				+ ", \t" + salesAmount + ", \t" + isPayment + ", \t" + paymentDate + "]";
	}
	
	
	

}
