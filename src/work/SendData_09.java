package work;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SendData_09 {
	public static void main(String[] args) {/*
		List<String> orderids = WfsdkDbUtils.getOrderidsList();
		System.out.println(orderids.size());

		List<String> sucOrderIds = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			int n = i * 2;
			if (n > 44) {
				n = n - 9;
			}
			String order_id = orderids.get(n);
			sucOrderIds.add(order_id);
		}

		Map<String, String> map = WfsdkDbUtils.getLogsByorderid(sucOrderIds);
		System.out.println(map);
		List<String> idList = FeeDbUtils.getLqdtaByFm();
		Set<String> keys = map.keySet();
		int k = 0;
		for (String orderid : keys) {
			String extparams = map.get(orderid);
			String id = idList.get(k);
			System.out.println(id+","+extparams+","+orderid);
			k++;
			FeeDbUtils.updateLqByid(id, orderid, extparams);
		}
	*/}
}
