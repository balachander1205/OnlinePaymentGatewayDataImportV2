package com.dhfl.OnlinePaymentGatewayDataDump.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dhfl.OnlinePaymentGatewayDataDump.config.ApplicationConfig;
import com.dhfl.OnlinePaymentGatewayDataDump.test.ReadExcel;

@Component
public class OnlinePaymentGatewayOfflineVerifyCron {
	private static final Logger LOG = LoggerFactory.getLogger(OnlinePaymentGatewayOfflineVerifyCron.class);

	@Autowired
	ApplicationConfig config;
	
	ReadExcel readExcel = new ReadExcel();
	
	Logger logger = LoggerFactory.getLogger(OnlinePaymentGatewayOfflineVerifyCron.class);
	// @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
	@Scheduled(cron = "10 * * * * ?")
	public void publish() {
		int count = 0;
		try {			
			//readExcel.readXLSXFile();
			System.out.println("Cron Started="+count);
		}catch (Exception e) {
			logger.debug("Exception@OnlinePaymentGatewayOfflineVerifyCron="+e);
		}
		LOG.info("Average value is 0::--->>"+count++);
	}
}
