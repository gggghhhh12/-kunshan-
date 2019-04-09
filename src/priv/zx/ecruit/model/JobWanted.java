package priv.zx.ecruit.model;

/**
 * 求职表bean，存学生用户名和公司用户名
 * @author zx
 *
 */
public class JobWanted {

	private String stuUsername;//学生用户名
	private String epUsername;//公司用户名
	private String epJobname;
	
	public String getEpJobname() {
		return epJobname;
	}
	public void setEpJobname(String epJobname) {
		this.epJobname = epJobname;
	}
	public String getStuUsername() {
		return stuUsername;
	}
	public void setStuUsername(String stuUsername) {
		this.stuUsername = stuUsername;
	}
	public String getEpUsername() {
		return epUsername;
	}
	public void setEpUsername(String epUsername) {
		this.epUsername = epUsername;
	}
	
}
