package work;

import java.util.List;
import java.util.Map;

import net.spy.memcached.MemcachedClient;

import com.codebuilding.Constant;
import com.codebuilding.Memcaches;

public class InitData {
	private static MemcachedClient cache = Memcaches.getMem();

	public static void main(String[] args) {/*
//		Map<String, Integer> fmMap = FeeDbUtils.getSumPriceByFm();
		List<String> spNames = FeeDbUtils.getSpNameList();
		for (String name : spNames) {
			Integer allPrice = (Integer) cache.get(Constant.MONITOR_ALL_SUC +name);
			System.out.println(name+":"+allPrice);
		}
			if (fmMap.get(name) == null) {
			cache.set(Constant.MONITOR_ALL_SUC + name, 0, 0);
		} else {
			cache.set(Constant.MONITOR_ALL_SUC +name, 0,
					fmMap.get(name));
		}
	*/}
}
