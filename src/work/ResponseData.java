package work;

import java.util.List;


public class ResponseData {
	private String			orderId;
	private String			state;
	private List<Contents>	content;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Contents> getContent() {
		return content;
	}
	public void setContent(List<Contents> content) {
		this.content = content;
	}

}
