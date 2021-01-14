package com.dhfl.OnlinePaymentGatewayDataDump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dhfl.OnlinePaymentGatewayDataDump.controller.DataDumpController;


@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableScheduling
//(basePackages ={"com.dhfl.OnlinePayment.repo"})
@ComponentScan(basePackageClasses = DataDumpController.class)
@ComponentScan(basePackages = { "com.dhfl.OnlinePaymentGatewayDataDump" })
public class OnlinePaymentGatewayDataDumpApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinePaymentGatewayDataDumpApplication.class, args);
	}
	
}
