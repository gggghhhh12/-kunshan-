package priv.zx.ecruit.model;

import java.util.Date;

/**
 * ��ҵ���û��˺���Ϣbean
 */
public class User {
	
	private String username;//����
	private String password;//����
	private Date userDate;//ע������/*���������Աһ��������Ȩ�޹���*/
	
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
