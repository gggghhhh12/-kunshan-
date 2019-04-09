package priv.zx.ecruit.model;

/**
 * 毕业生收藏公司类
 * @author zx
 *
 */
public class StuStore {

	private String stuUsername;//学生用户名
	private String epUsername;//公司用户名
	private String jobname;//职位名
	
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
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