package work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Demo5 {
	public static void main(String[] args) throws Exception {
		List<Contents> stepList = new ArrayList<>();
		Contents contents1 = new Contents();
		contents1.setStep("12");
		contents1.setCmd("data12");
		stepList.add(contents1);

		Contents contents2 = new Contents();
		contents2.setStep("5");
		contents2.setCmd("data5");
		stepList.add(contents2);
		Collections.sort(stepList);
		System.out.println(stepList);
	}
}
