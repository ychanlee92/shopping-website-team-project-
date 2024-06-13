package model;

public class UserVO {
	private String userId = null;
	private String userPass = null;
	private String userName = null;
	private String phone = null;
	private String address = null;
	private int accAmount = 0;

	public UserVO() {
		super();
	}

	public UserVO(String userId, String userPass, String userName, String phone, String address, int accAmount) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.accAmount = accAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAccAmount() {
		return accAmount;
	}

	public void setAccAmount(int accAmount) {
		this.accAmount = accAmount;
	}

	@Override
	public String toString() {
		return "[" + userId + "\t|" + userPass + "\t|" + userName + "\t|" + phone + "\t|" + address + "\t|" + accAmount
				+ "]";
	}
}
