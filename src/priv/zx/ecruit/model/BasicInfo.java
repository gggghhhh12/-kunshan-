package priv.zx.ecruit.model;

import java.util.Date;

/**
 * 简历基本信息bean
 */
public class BasicInfo {

	private String username;
	private String name;
	private String sex;
	private String nation;
	private Date birthday;
	private String tel;
	private String email;
	private String liveaddr;
	private String residence;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLiveaddr() {
		return liveaddr;
	}
	public void setLiveaddr(String liveaddr) {
		this.liveaddr = liveaddr;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}	
}
