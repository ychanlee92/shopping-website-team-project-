package model;

public class CalculateVO {
	private int no;
	private String quantity;
	private int sales_amount;
	private String payment_date;
	
	
	public CalculateVO() {
		super();
	}

	public CalculateVO(int no, String quantity, int sales_amount, String payment_date) {
		super();
		this.no = no;
		this.quantity = quantity;
		this.sales_amount = sales_amount;
		this.payment_date = payment_date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getSales_amount() {
		return sales_amount;
	}

	public void setSales_amount(int sales_amount) {
		this.sales_amount = sales_amount;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	
	
}
