package model;

public class CouponVO {
	private int coupon_Num = 0;
	private String userId = null;
	private String coupon_w = null;
	private String coupon_m = null;
	private String coupon_d = null;

	public CouponVO() {
		super();
	}

	public CouponVO(String userId, String coupon_w, String coupon_m, String coupon_d) {
		super();
		this.userId = userId;
		this.coupon_w = coupon_w;
		this.coupon_m = coupon_m;
		this.coupon_d = coupon_d;
	}

	public CouponVO(int coupon_Num, String userId, String coupon_w, String coupon_m, String coupon_d) {
		super();
		this.coupon_Num = coupon_Num;
		this.userId = userId;
		this.coupon_w = coupon_w;
		this.coupon_m = coupon_m;
		this.coupon_d = coupon_d;
	}

	public int getCoupon_Num() {
		return coupon_Num;
	}

	public void setCouponNum(int couponNum) {
		this.coupon_Num = couponNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCoupon_w() {
		return coupon_w;
	}

	public void setCoupon_w(String coupon_w) {
		this.coupon_w = coupon_w;
	}

	public String getCoupon_m() {
		return coupon_m;
	}

	public void setCoupon_m(String coupon_m) {
		this.coupon_m = coupon_m;
	}

	public String getCoupon_d() {
		return coupon_d;
	}

	public void setCoupon_d(String coupon_d) {
		this.coupon_d = coupon_d;
	}

	@Override
	public String toString() {
		return "[" + coupon_Num + "\t|" + userId + "\t|" + coupon_w + "\t|" + coupon_m + "\t|" + coupon_d + "]";
	}

}
