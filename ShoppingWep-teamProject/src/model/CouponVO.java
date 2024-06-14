package model;

public class CouponVO {
	private int coupon_num = 0;
	private String userId = null;
	private int coupon_w = 0;
	private int coupon_m = 0;
	private int coupon_d = 0;

	public CouponVO() {
		super();
	}

	public CouponVO(String userId, int coupon_w, int coupon_m, int coupon_d) {
		super();
		this.userId = userId;
		this.coupon_w = coupon_w;
		this.coupon_m = coupon_m;
		this.coupon_d = coupon_d;
	}

	public CouponVO(int coupon_num, String userId, int coupon_w, int coupon_m, int coupon_d) {
		super();
		this.coupon_num = coupon_num;
		this.userId = userId;
		this.coupon_w = coupon_w;
		this.coupon_m = coupon_m;
		this.coupon_d = coupon_d;
	}

	public int getCoupon_num() {
		return coupon_num;
	}

	public void setCoupon_num(int coupon_num) {
		this.coupon_num = coupon_num;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCoupon_w() {
		return coupon_w;
	}

	public void setCoupon_w(int coupon_w) {
		this.coupon_w = coupon_w;
	}

	public int getCoupon_m() {
		return coupon_m;
	}

	public void setCoupon_m(int coupon_m) {
		this.coupon_m = coupon_m;
	}

	public int getCoupon_d() {
		return coupon_d;
	}

	public void setCoupon_d(int coupon_d) {
		this.coupon_d = coupon_d;
	}

	@Override
	public String toString() {
		return "[" + coupon_num + "\t|" + userId + "\t|" + coupon_w + "\t|" + coupon_m + "\t|" + coupon_d + "]";
	}

}
