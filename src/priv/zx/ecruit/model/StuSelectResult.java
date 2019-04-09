package priv.zx.ecruit.model;

/**
 * 搜索职位重新组装bean
 * @author zx
 *
 */
public class StuSelectResult {

	private String EPusername;
	private String jobname;
	private String EPname;
	private String jobaddr;
	private String salary;
	
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
	public String getEPname() {
		return EPname;
	}
	public void setEPname(String ePname) {
		EPname = ePname;
	}
	public String getJobaddr() {
		return jobaddr;
	}
	public void setJobaddr(String jobaddr) {
		this.jobaddr = jobaddr;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return EPusername + "," + jobname + "," + salary + "," + EPname + "," + jobaddr;
	}
}
