package priv.zx.ecruit.model;

/**
 * 管理员界面需要显示的毕业生简历信息
 * @author zx
 *
 */
public class AdminResume {

	private String stuUsername;
	private String stuName;
	private String sex;
	private String school;
	private String major;
	private String tel;
	private String email;
	
	public String getStuUsername() {
		return stuUsername;
	}
	public void setStuUsername(String stuUsername) {
		this.stuUsername = stuUsername;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
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
	
}
