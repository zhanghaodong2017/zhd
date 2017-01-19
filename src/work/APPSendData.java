package work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.client.ClientProtocolException;

import com.htest.HttpUtil;
import com.monitor.ReqcontentDb;
import com.yace.FeeUtils;

public class APPSendData {
	public static void main(String[] args) throws Exception {
		dayUpdateApp();

	}

	private static void fun5() throws Exception {

		Map<String, AppInfo> appMap = FeeDbUtils.getAppList();
		List<ReqcontentDb> dbList = FeeDbUtils.getReqDB10();

		for (ReqcontentDb reqcontentDb : dbList) {
			String appid = reqcontentDb.getAppid();
			if (appid != null && !"".equals(appid)) {
				AppInfo appInfo = appMap.get(appid);
				String url = appInfo.getUrl();
				String realUrl = url + "?linkid=" + reqcontentDb.getId()
						+ "&channel=" + reqcontentDb.getChannel()
						+ "&price="+reqcontentDb.getPrice()+"&result=0";
				try {
					System.out.println(appid + ">" + realUrl);
					String back = HttpUtil.doGet(realUrl);
					System.out.println(back);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(dbList.size());
		System.out.println("success");


	}

	private static void dayUpdateApp() {
		List<ReqcontentDb> reqcontentDbs = new ArrayList<>();
		TwoTuple<Map<String,List<String>>, Map<String,Integer>> twoTuple = FeeDbUtils.getAppIsNull();
		Map<String,List<String>> fmIdMap = twoTuple.first;
		Map<String,Integer> priceMap = twoTuple.second;
		Map<String, List<TwoTuple<String, String>>> sendSucMap = FeeDbUtils.getSendSuc();
		for (String fm : fmIdMap.keySet()) {
			List<String> idList = fmIdMap.get(fm);
			List<TwoTuple<String, String>> sendList = sendSucMap.get(fm);
			if (sendList != null && sendList.size() > 0) {
				for (int i = 0; i < idList.size(); i++) {
					TwoTuple<String, String> tuple = sendList.get(i%sendList.size());

					ReqcontentDb db = new ReqcontentDb();
					db.setId(idList.get(i));
					db.setChannel(tuple.second);
					db.setAppid(tuple.first);
					reqcontentDbs.add(db);
				}
			}
		}
		System.out.println(reqcontentDbs.size());
		FeeDbUtils.updateReqByDB(reqcontentDbs);
		System.out.println("success");

		Map<String, AppInfo> appMap = FeeDbUtils.getAppList();
		List<String> ud2 = new ArrayList<>();
		System.out.println(reqcontentDbs.size());
		for (ReqcontentDb reqcontentDb : reqcontentDbs) {
			String appid = reqcontentDb.getAppid();
			if (appid != null && !"".equals(appid)) {
				AppInfo appInfo = appMap.get(appid);
				int bili = appInfo.getBili();
				String url = appInfo.getUrl();
				if (RandomUtils.nextInt(100) >= bili) {
					String realUrl = url + "?linkid=" + reqcontentDb.getId()
							+ "&channel=" + reqcontentDb.getChannel()
							+ "&price="+priceMap.get(reqcontentDb.getId())+"&result=0";
					System.out.println(appid + ">" + realUrl);
					ud2.add(reqcontentDb.getId());
					try {
						String back = HttpUtil.doGet(realUrl);
						System.out.println(back);
					} catch (Exception e) {
					}
				} else {
					System.out.println("kouliangle");
				}
			}
		}

		FeeDbUtils.updateReqByappid(ud2);
		System.out.println("success2:"+ud2.size());


	}
	private static void dayUpdateYunPan() {
		List<ReqcontentDb> reqcontentDbs = new ArrayList<>();
		Map<String, Integer> priceMap = FeeDbUtils.getAppIsYunPan();
		List<String> sendSucList = FeeDbUtils.getSendYunpanChaennlSuc();
		int index=0;
		for (String id : priceMap.keySet()) {
			ReqcontentDb db = new ReqcontentDb();
			db.setId(id);
			db.setChannel(sendSucList.get(index++));
			db.setAppid("000870");
			reqcontentDbs.add(db);
		}
		System.out.println(reqcontentDbs.size());
		FeeDbUtils.updateReqByDB(reqcontentDbs);
		System.out.println("success");

		Map<String, AppInfo> appMap = FeeDbUtils.getAppList();
		List<String> ud2 = new ArrayList<>();
		System.out.println(reqcontentDbs.size());
		for (ReqcontentDb reqcontentDb : reqcontentDbs) {
			String appid = reqcontentDb.getAppid();
			if (appid != null && !"".equals(appid)) {
				AppInfo appInfo = appMap.get(appid);
				int bili = appInfo.getBili();
				String url = appInfo.getUrl();
				/*if (RandomUtils.nextInt(100) >= bili) {
					String realUrl = url + "?linkid=" + reqcontentDb.getId()
							+ "&channel=" + reqcontentDb.getChannel()
							+ "&price="+priceMap.get(reqcontentDb.getId())+"&result=0";
					System.out.println(appid + ">" + realUrl);
					ud2.add(reqcontentDb.getId());
					try {
						String back = HttpUtil.doGet(realUrl);
						System.out.println(back);
					} catch (Exception e) {
					}
				} else {
					System.out.println("kouliangle");
				}*/
			}
		}

		FeeDbUtils.updateReqByappid(ud2);
		System.out.println("success2:"+ud2.size());


	}
	private static void fun3() {
		List<String> YPdistro = FeeDbUtils.getYpdistro();
		int size = YPdistro.size();
		String fm = "SCBHFMZD";
		List<String> idList = FeeDbUtils.getReqcontentDb(fm);
		System.out.println(idList.size());
		int index = 0;
		List<ReqcontentDb> reqcontentDbs = new ArrayList<>();
		for (String id : idList) {
			ReqcontentDb db = new ReqcontentDb();
			db.setId(id);
			db.setChannel(YPdistro.get(index % size));
			db.setAppid("000870");
			index++;
			reqcontentDbs.add(db);
		}
		FeeDbUtils.updateReqByDB(reqcontentDbs);
		System.out.println("success");
	}

	private static void fun2() {
		Map<String, AppInfo> appMap = FeeDbUtils.getAppList();
		String fm = "MQYDBY";
		List<String> ud2 = new ArrayList<>();
		List<ReqcontentDb> dbList = FeeDbUtils.getReqDB12(fm);
		System.out.println(dbList.size());
		for (ReqcontentDb reqcontentDb : dbList) {
			String appid = reqcontentDb.getAppid();
			if (appid != null && !"".equals(appid)) {
				AppInfo appInfo = appMap.get(appid);
				int bili = appInfo.getBili();
				String url = appInfo.getUrl();
				if (RandomUtils.nextInt(100) >= bili) {
					String realUrl = url + "?linkid=" + reqcontentDb.getId()
							+ "&channel=" + reqcontentDb.getChannel()
							+ "&price=8&result=0";
					System.out.println(appid + ">" + realUrl);
					ud2.add(reqcontentDb.getId());
					try {
						String back = HttpUtil.doGet(realUrl);
						System.out.println(back);
					} catch (Exception e) {
					}
				} else {
					System.out.println("kouliangle");
				}
			}
		}

		FeeDbUtils.updateReqByappid(ud2);
		System.out.println("success");
	}

	private static void fun1() {
		String fm = "DZWTU";
		List<String> idList = FeeDbUtils.getReqcontentDb(fm);
		System.out.println(idList.size());
		List<ReqcontentDb> reqcontentDbs = new ArrayList<>();
		int index = 0;
		List<AfterPayLog> payLogs = FeeDbUtils.getAfterPayLog(fm);
		System.out.println(payLogs.size());
		for (int i = 0; i < payLogs.size(); i++) {
			AfterPayLog afterPayLog = payLogs.get(i);
			int num = afterPayLog.getId();
			int shang = num / 3;
			int yushu = num % 3;
			if (yushu > 1) {
				shang++;
			}
			for (int j = 0; j < shang; j++) {
				if (index >= idList.size()) {
					break;
				}
				ReqcontentDb db = new ReqcontentDb();
				db.setId(idList.get(index));
				db.setChannel(afterPayLog.getChannelid());
				db.setAppid(afterPayLog.getAppid());
				index++;
				reqcontentDbs.add(db);
			}
		}
		System.out.println(reqcontentDbs.size());
		FeeDbUtils.updateReqByDB(reqcontentDbs);
		System.out.println("success");
	}
}
