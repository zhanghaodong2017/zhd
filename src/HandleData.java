import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;


public class HandleData {
	public void handle(){
		StringBuilder builder = new  StringBuilder("abc");
		for (int i = 0; i < 100; i++) {
			builder.append(String.valueOf(i));
			try {
				Thread.sleep(RandomUtils.nextInt(100)+100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(builder.toString());
	}
}
