package com.monitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class LTXWTask extends TimerTask {

	@Override
	public void run() {
		String sql_1 ="SELECT MAX(ctime) FROM reqcontent_db where ctime>CURRENT_DATE() and fm='LTXW'";
		String dbLastTime = Jdbc.doQuery(sql_1);
		System.out.println("dbLastTime:"+dbLastTime);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
//			System.out.println(df.parse(dbLastTime).getSeconds());
			int minutes = Long.valueOf(((new Date()).getTime()-df.parse(dbLastTime).getTime())/60000).intValue();
			System.out.println(minutes);
			if(minutes >= 2){

                DotimeEmail dotime = new DotimeEmail();
                dotime.sendMail("1028601764@qq.com,1292171995@qq.com,754049106@qq.com", "LTXW异常", "当前时间到-计费成功时间 "+minutes+"分钟", null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
