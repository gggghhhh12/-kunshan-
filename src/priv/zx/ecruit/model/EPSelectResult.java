package priv.zx.ecruit.model;

/**
 * ��ҵ��ѯ���������ı�ҵ����Ϣbean
 * @author zx
 *
 */
public class EPSelectResult {

	private String stuUsername;//��ҵ���û���
	private String stuName;//��ҵ������
	private String sex;//��ҵ���Ա�
	private String school;//��ҵ��ѧУ
	private String major;//��ҵ��רҵ
	
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
