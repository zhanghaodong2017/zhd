package com.zuidiao;

import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.lang.math.RandomUtils;

public class DZCNWTask extends TimerTask {

	@Override
	public void run() {

		Calendar calendar4 =Calendar.getInstance();
		calendar4.set(2015, 7, 31, 0, 0, 0);
		Calendar calendar5 =Calendar.getInstance();
		calendar5.set(2015, 8, 1, 0, 0, 0);

		List<CNSParam> cnsParams =null;//BspDbUtils.getTelByTime(calendar4.getTime(), calendar5.getTime());

		/*List<CNSParam> cnsParams = FeeDbUtils.getTelByTime();
		System.out.println(cnsParams.size());*/

		int index =0;
		for (CNSParam cnsParam : cnsParams) {
			index++;
			DZCNW.getCmd(cnsParam.getTel(), cnsParam.getImsi(), cnsParam.getImei());
			try {
				Thread.sleep(60000);
				if(!TimeUtils.isOk()){
					Thread.sleep(3600000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(index);
			if(index >= 9000){
				break;
			}
		}

	}

}
