package com.imsi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CreateImsi_2 {
	public static void main(String[] args) {
		int day=1;
		 String[] carriers = {"CNC","CUC","CMCC"};
		 for (int i = 0; i < carriers.length; i++) {
			 String car ="";
			 switch (carriers[i]) {
			case "CMCC":
				car="M";
				break;
			case "CUC":
				car="U";
				break;
			case "CNC":
				car="T";
				break;
			}
			 for (int pro = 1; pro <= 31; pro++) {

				 Map<String, String> map = QueryImsi.getImsi(car, pro);
				 if(map.size()< 1000){
					 System.out.println(car+ "_" + pro+":"+map.size());
					 List<ImsiInfo> imsiInfos = GetImsi.getImsiBsp(carriers[i], pro,1000-map.size());
					 System.out.println(carriers[i]+"_"+pro +":"+imsiInfos.size());
					 List<ImsiInfo> imsis = new ArrayList<>();
					 int index=0;
					 for (int k = 0; k < 1000; k++) {
						 String imsi = map.get( pro + car  + k);
						 if(imsi == null ){
							 if(index < imsiInfos.size()){
								 ImsiInfo info =imsiInfos.get(index);
								 info.setId( pro + car  + k);
								 imsis.add(info);
								 index++;
							 }
						 }
					}
					 if(imsis.size()>0){
						 SaveImsi.sve(imsis);
					 }

				 }

			}
		}

	}
}
