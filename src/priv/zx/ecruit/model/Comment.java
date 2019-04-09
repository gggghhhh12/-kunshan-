package priv.zx.ecruit.model;

import java.util.Date;

/**
 * ÆÀÂÛmodel
 * @author zx
 *
 */
public class Comment {

	private String stuUsername;
	private String epUsername;
	private String relation;
	private String content;
	private Date date;
	
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
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
