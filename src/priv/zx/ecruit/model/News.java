package priv.zx.ecruit.model;

import java.util.Date;

/**
 * ����bean
 * @author zx
 *
 */
public class News {

	private String news_id;//���ű��
	private String news_title;//���ű���
	private String news_content;//��������
	private Date news_time;//���ŷ���ʱ��
	
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public Date getNews_time() {
		return news_time;
	}
	public void setNews_time(Date news_time) {
		this.news_time = news_time;
	}
	
}
