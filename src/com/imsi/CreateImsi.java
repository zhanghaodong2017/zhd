package com.imsi;

import java.util.List;

public class CreateImsi {
	public static void main(String[] args) {

		 String[] carriers = {"M","U","T"};
		 for (int i = 0; i < carriers.length; i++) {
			 for (int j = 1; j <= 31; j++) {
				 List<ImsiInfo> imsiInfos= GetImsi.getImsi(carriers[i],j);
				 System.out.println(carriers[i]+"_"+j +":"+imsiInfos.size());
				 int ran =0;
				 for (ImsiInfo imsiInfo : imsiInfos) {
					 imsiInfo.setId(imsiInfo.getPro()+imsiInfo.getCarrier()+ran);
					 ran++;
				}
				 System.out.println(imsiInfos.get(0).getId());
				 SaveImsi.sve(imsiInfos);
			}
		}

	}
}
