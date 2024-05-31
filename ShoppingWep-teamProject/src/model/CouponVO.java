package model;

public class CouponVO {
	private int couponNum = 0;
	private String userId = null;
	private String welcomeC = null;
	private String vipC = null;
	private String nikeC = null;

	public CouponVO() {
		super();
	}

	public CouponVO(String userId, String welcomeC, String vipC, String nikeC) {
		super();
		this.userId = userId;
		this.welcomeC = welcomeC;
		this.vipC = vipC;
		this.nikeC = nikeC;
	}

	public CouponVO(int couponNum, String userId, String welcomeC, String vipC, String nikeC) {
		super();
		this.couponNum = couponNum;
		this.userId = userId;
		this.welcomeC = welcomeC;
		this.vipC = vipC;
		this.nikeC = nikeC;
	}

	public int getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(int couponNum) {
		this.couponNum = couponNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWelcomeC() {
		return welcomeC;
	}

	public void setWelcomeC(String welcomeC) {
		this.welcomeC = welcomeC;
	}

	public String getVipC() {
		return vipC;
	}

	public void setVipC(String vipC) {
		this.vipC = vipC;
	}

	public String getNikeC() {
		return nikeC;
	}

	public void setNikeC(String nikeC) {
		this.nikeC = nikeC;
	}

	@Override
	public String toString() {
		return "[" + couponNum + "\t|" + userId + "\t|" + welcomeC + "\t|" + vipC + "\t|" + nikeC + "]";
	}

}
