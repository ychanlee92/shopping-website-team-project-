package model;

public class ReviewVO {
	private int reviewNum = 0;
	private String userId = null;
	private String pdCode = null;
	private String reviewCom = null;
	private double rate = 0.0;

	public ReviewVO() {
		super();
	}

	public ReviewVO(int reviewNum, String userId, String pdCode, String reviewCom, double rate) {
		super();
		this.reviewNum = reviewNum;
		this.userId = userId;
		this.pdCode = pdCode;
		this.reviewCom = reviewCom;
		this.rate = rate;
	}

	public ReviewVO(String userId, String pdCode, String reviewCom, double rate) {
		super();
		this.reviewNum = reviewNum;
		this.userId = userId;
		this.pdCode = pdCode;
		this.reviewCom = reviewCom;
		this.rate = rate;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public String getReviewCom() {
		return reviewCom;
	}

	public void setReviewCom(String reviewCom) {
		this.reviewCom = reviewCom;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "[" + reviewNum + "\t|" + userId + "\t|" + pdCode + "\t|"
				+ reviewCom + "\t|" + rate + "]";
	}

	
}
