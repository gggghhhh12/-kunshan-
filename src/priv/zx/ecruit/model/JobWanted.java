package priv.zx.ecruit.model;

/**
 * ��ְ��bean����ѧ���û����͹�˾�û���
 * @author zx
 *
 */
public class JobWanted {

	private String stuUsername;//ѧ���û���
	private String epUsername;//��˾�û���
	private String epJobname;
	
	public String getEpJobname() {
		return epJobname;
	}
	public void setEpJobname(String epJobname) {
		this.epJobname = epJobname;
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
