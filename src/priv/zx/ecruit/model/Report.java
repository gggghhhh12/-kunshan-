package priv.zx.ecruit.model;

/**
 * 举报信息model
 * @author zx
 *
 */
public class Report {
	private String stuUsername;//举报人
	private String epUsername;//被举报企业
	
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
