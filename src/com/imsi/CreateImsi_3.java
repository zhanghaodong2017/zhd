package com.imsi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateImsi_3 {
	public static void main(String[] args) {
		String car = "U";
		String carrier = "CUC";
		int pro = 25;
		Map<String, String> map = QueryImsi.getImsi(car, pro);
		if (map.size() < 1000) {
			System.out.println(car + "_" + pro + ":" + map.size());
			List<ImsiInfo> imsiInfos = GetImsi.getImsiBsp(carrier, pro,
					1000 - map.size());
			System.out.println(carrier + "_" + pro + ":" + imsiInfos.size());
			List<ImsiInfo> imsis = new ArrayList<>();
			int index = 0;
			for (int k = 0; k < 1000; k++) {
				String imsi = map.get(pro + car + k);
				if (imsi == null) {
					if (index < imsiInfos.size()) {
						ImsiInfo info = imsiInfos.get(index);
						info.setId(pro + car + k);
						imsis.add(info);
						index++;
					}
				}
			}
			if (imsis.size() > 0) {
				SaveImsi.sve(imsis);
			}

		}

	}
}
