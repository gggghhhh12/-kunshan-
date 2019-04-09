package priv.zx.ecruit.model;

import java.util.Date;

/**
 * 公司发布职位要求bean
 */
public class EPPostJob {

	private String EPusername;//公司账户名，主键
	private String jobname;//职位名称
	private String jobsalary;//工资待遇
	private String jobdiploma;//学历要求
	private String engrequest;//英语等级要求
	private int reqnum;//招聘的人数
	private Date postdate;//发布日期
	private String benefits;//公司福利
	private String jobdescribe;//职位描述
	private String jobduty;//岗位职责
	private String techrequest;//技术要求
	private String jobkind;//职能类别
	private String jobaddr;//上班地址
	private int status;//工作信息公开状态
	
	public String getEPusername() {
		return EPusername;
	}
	public void setEPusername(String ePusername) {
		EPusername = ePusername;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getJobsalary() {
		return jobsalary;
	}
	public void setJobsalary(String jobsalary) {
		this.jobsalary = jobsalary;
	}
	public String getJobdiploma() {
		return jobdiploma;
	}
	public void setJobdiploma(String jobdiploma) {
		this.jobdiploma = jobdiploma;
	}
	public String getEngrequest() {
		return engrequest;
	}
	public void setEngrequest(String engrequest) {
		this.engrequest = engrequest;
	}
	public int getReqnum() {
		return reqnum;
	}
	public void setReqnum(int reqnum) {
		this.reqnum = reqnum;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public String getJobdescribe() {
		return jobdescribe;
	}
	public void setJobdescribe(String jobdescribe) {
		this.jobdescribe = jobdescribe;
	}
	public String getJobduty() {
		return jobduty;
	}
	public void setJobduty(String jobduty) {
		this.jobduty = jobduty;
	}
	public String getTechrequest() {
		return techrequest;
	}
	public void setTechrequest(String techrequest) {
		this.techrequest = techrequest;
	}
	public String getJobkind() {
		return jobkind;
	}
	public void setJobkind(String jobkind) {
		this.jobkind = jobkind;
	}
	public String getJobaddr() {
		return jobaddr;
	}
	public void setJobaddr(String jobaddr) {
		this.jobaddr = jobaddr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
