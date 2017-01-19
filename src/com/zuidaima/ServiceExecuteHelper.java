package com.zuidaima;

import org.apache.log4j.Logger;

import com.zuidaima.service.Service;

public class ServiceExecuteHelper {
	 private static final Logger logger= Logger.getLogger(ServiceExecuteHelper.class);

	 public static void execute(String servicename) {
	         try {
	             //验证服务是否存在
	            String servicClass=ServiceConfig.getService(servicename);
	            //如果服务存在就加载服务信息
	            if (servicClass !=null && !servicClass.equals("")) {
	                 Class classObject=Class.forName(servicClass);
	                 Service service=(Service) classObject.newInstance();
	                 service.execute();
	             } else {
	                 logger.info("服务["+servicename+"]未定义");
	             }
	           }catch(Exception e) {
	               logger.info("服务["+servicename+"]不存在!");
	           }
	        }
}
