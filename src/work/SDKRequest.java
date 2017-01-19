package work;

import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SDKRequest {
	public static void main(String[] args) throws Exception {
		String url = "http://118.178.116.121:9999/SdkServer/wfsdk/getcmd?price=20&imei=862018039978613&iccid=89860316045778238456&appid=000813&imsi=460036681702146&extparams=76zCgJ3WB&ip=115.221.238.128";
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		String response = IOUtils.toString(client.execute(get).getEntity().getContent(),"utf-8");

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		ResponseData data = gson.fromJson(response, ResponseData.class);

		if("0".equals(data.getState())){
			String orderId = data.getOrderId();
			List<Contents> stepList = data.getContent();
			//按照step从小到大顺序排序
			Collections.sort(stepList);
			for (Contents contents : stepList) {
			/*	String type = contents.getType();
			    1  直接发送(sendTextMessage)
				2  二进制发送(sendDataMessage)
				3  base64解码后直接发送(sendTextMessage)
				4  base64解码后二进制发送(sendDataMessage)
				5  等待获取验证码
				6携带验证码访问url(按下例进行参数替换)
				7  携带验证码进行二次回复
				8  直接访问url(按下例进行参数替换)*/


			}
		}


	}
}
