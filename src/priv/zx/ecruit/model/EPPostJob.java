package priv.zx.ecruit.model;

import java.util.Date;

/**
 * ��˾����ְλҪ��bean
 */
public class EPPostJob {

	private String EPusername;//��˾�˻���������
	private String jobname;//ְλ����
	private String jobsalary;//���ʴ���
	private String jobdiploma;//ѧ��Ҫ��
	private String engrequest;//Ӣ��ȼ�Ҫ��
	private int reqnum;//��Ƹ������
	private Date postdate;//��������
	private String benefits;//��˾����
	private String jobdescribe;//ְλ����
	private String jobduty;//��λְ��
	private String techrequest;//����Ҫ��
	private String jobkind;//ְ�����
	private String jobaddr;//�ϰ��ַ
	private int status;//������Ϣ����״̬
	
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
