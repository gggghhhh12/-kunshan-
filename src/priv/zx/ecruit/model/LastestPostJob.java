package priv.zx.ecruit.model;

import java.util.Date;

/**
 * Ϊ��ʾ���·�����ְλ�����bean
 * @author zx
 *
 */
public class LastestPostJob {

	private String epUsername;//��˾�û���
	private String jobName;//ְλ��
	private String epName;//��˾��
	private Date postDate;//��������
	
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
