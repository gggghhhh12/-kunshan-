package priv.zx.ecruit.model;

/**
 * ��ҵ�͸���ҵ������model
 * @author zx
 *
 */
public class TradeAndCount {

	private String trade;
	private int count;
	
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return trade + "," + count;
	}
	
}
