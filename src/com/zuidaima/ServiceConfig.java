package com.zuidaima;

import java.util.HashMap;

public class ServiceConfig {
	public static HashMap serviceMap= new HashMap();

    public static String getService(String serviceName) {
       String serviceClass = ServiceConfig.serviceMap.get(serviceName).toString();
       return serviceClass;
    }
}
