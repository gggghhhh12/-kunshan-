package priv.zx.ecruit.model;

import java.util.Date;

/**
 * 毕业生用户账号信息bean
 */
public class User {
	
	private String username;//姓名
	private String password;//密码
	private Date userDate;//注册日期/*用来与管理员一起进行审核权限公开*/
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getUserDate() {
		return userDate;
	}
	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}
	
	
	
}
