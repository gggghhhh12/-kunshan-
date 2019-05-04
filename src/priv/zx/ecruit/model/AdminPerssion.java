package priv.zx.ecruit.model;

import java.util.Date;
//关于审核的开放与否
public class AdminPerssion {
	private Date start_time;//审核开始时间
	private Date end_time;//审核结束时间
    private Date daytime;
	public Date getStartTime() {
		return start_time;
	}
	public void setStartTime(Date start_time) {
		this.start_time =start_time;
	}
	public Date getEndTime() {
		return end_time;
	}
	public void setEndTime(Date end_time) {
		this.end_time =end_time;
	}
	public Date getdaytime() {
		return daytime;
	}
	public void setdaytime(Date daytime) {
		this.daytime =daytime;
	}		

}
