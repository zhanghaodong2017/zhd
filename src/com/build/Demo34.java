package com.build;

import java.util.List;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang.StringUtils;

import com.codebuilding.Constant;
import com.codebuilding.Memcaches;


public class Demo34 {

	private MemcachedClient	memcache	= Memcaches.getMem();
	public static String SPNAME_CATEGORY ="spname-category-";

	private boolean time(String url, String userid)
		{
			boolean flag = true;
			try {
				//判断spName 是否归类
				List<String> cateNames = (List<String>) memcache.get(SPNAME_CATEGORY + url);
				if(cateNames == null || cateNames.size() == 0){
					String time = (String) memcache.get(Constant.SP_INTERVAL_TIME+ url);
					flag = timeOther(url, time, userid);
				}else{
					for (String categoryName : cateNames)
					{
						String time = (String) memcache.get(Constant.SP_INTERVAL_TIME+ categoryName);
						flag = timeOther(categoryName, time, userid);
						if(!flag){
							return false;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		private boolean timeOther(String url, String time, String userid){
			String t = (String) memcache.get(Constant.TIME_LIMIT+ userid+ "-" + url);
			if (t == null)
			{
				if (!StringUtils.isNotBlank(time) || "0".equals(time) )
				{
					time = "30";
				}
				memcache.set(
						Constant.TIME_LIMIT + userid + "-" + url,
						Integer.valueOf(time), time);
				return true;
			}
			else
			{
				return false;
			}

		}

		public static void main(String[] args) {
			Demo34 demo34 = new Demo34();
			boolean flag = demo34.time("YJGSSP", "12134564");
			System.out.println(flag);
		}
}

