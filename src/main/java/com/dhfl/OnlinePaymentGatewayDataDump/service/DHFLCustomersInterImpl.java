package com.dhfl.OnlinePaymentGatewayDataDump.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dhfl.OnlinePaymentGatewayDataDump.entity.DHFLCustomersEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.repo.DHFLCustomersRepo;

@Service
public class DHFLCustomersInterImpl implements DHFLCustomersInter{
	@Autowired
	DHFLCustomersRepo dhflCustomersRepo;
	
	@Async
	public CompletableFuture<DHFLCustomersEntity> saveRecord(DHFLCustomersEntity entity) {
		return CompletableFuture.completedFuture(dhflCustomersRepo.save(entity));
	}

	@Override
	public DHFLCustomersEntity searchByBrLoanCode(String brLoanCode) {
		return dhflCustomersRepo.searchByBrLoanCode(brLoanCode);
	}

	@Override
	public DHFLCustomersEntity searchByAppNo(String appNo) {
		return dhflCustomersRepo.searchByAppNo(appNo);
	}

	@Override
	public DHFLCustomersEntity searchByMobileNo(String mobileNo) {
		return dhflCustomersRepo.searchByMobileNo(mobileNo);
	}

	@Override
	public int updateCustomer(String appNo, Long MinimumOverdueAmount, Long TotalOverdueEMI, Long TotalChargesAmount,
			Long MinimumChargeAmount, String mobileno, String customerName, Long overdueBlankField, Long chanrgesBlankField) {
		int count = dhflCustomersRepo.updateCustomer(appNo, MinimumOverdueAmount, TotalOverdueEMI, TotalChargesAmount,
				MinimumChargeAmount, mobileno, customerName, overdueBlankField, chanrgesBlankField);
		return count;
	}
	
	public DHFLCustomersEntity searchByAppNoLoanCode(String applno, String brloancode){
		DHFLCustomersEntity customersEntity = dhflCustomersRepo.searchByAppNoLoanCode(applno, brloancode);
		return customersEntity;
	}

	@Override
	public void deleteCustomers() {
		dhflCustomersRepo.deleteAll();
	}
}
