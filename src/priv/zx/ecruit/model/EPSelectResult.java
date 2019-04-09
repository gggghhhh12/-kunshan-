package priv.zx.ecruit.model;

/**
 * 企业查询符合条件的毕业生信息bean
 * @author zx
 *
 */
public class EPSelectResult {

	private String stuUsername;//毕业生用户名
	private String stuName;//毕业生姓名
	private String sex;//毕业生性别
	private String school;//毕业生学校
	private String major;//毕业生专业
	
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
	@Override
	public String toString() {
		return stuUsername + "," + stuName + "," + sex + "," + school + ","+ major;
	}
	
}
