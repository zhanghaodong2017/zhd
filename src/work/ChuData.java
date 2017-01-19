package work;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.monitor.ReqcontentDb;

public class ChuData {
	public static void main(String[] args) {
		List<String> ids = FeeDbUtils.getBackByappid();
		List<AfterPayLog> afterPayLogs = FeeDbUtils.getAfterPayLog(null);
		System.out.println(afterPayLogs.size());
		List<ReqcontentDb> reqcontentDbs = new ArrayList<>();
		int index=0;
		for (String id : ids) {
			ReqcontentDb db = new ReqcontentDb();
			db.setId(id);
			AfterPayLog afterPayLog = afterPayLogs.get(index);
			db.setChannel(afterPayLog.getChannelid());
			db.setAppid(afterPayLog.getAppid());
			index++;
			reqcontentDbs.add(db);
		}
		FeeDbUtils.updateReqByDB(reqcontentDbs);
		System.out.println("success");

	}
}
