package priv.zx.ecruit.model;

import java.util.Date;

/**
 * 为显示最新发布的职位而设的bean
 * @author zx
 *
 */
public class LastestPostJob {

	private String epUsername;//公司用户名
	private String jobName;//职位名
	private String epName;//公司名
	private Date postDate;//发布日期
	
	public String getEpUsername() {
		return epUsername;
	}
	public void setEpUsername(String epUsername) {
		this.epUsername = epUsername;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
}
