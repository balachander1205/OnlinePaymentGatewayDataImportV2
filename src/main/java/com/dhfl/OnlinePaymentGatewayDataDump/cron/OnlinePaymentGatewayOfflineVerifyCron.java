package com.dhfl.OnlinePaymentGatewayDataDump.cron;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dhfl.OnlinePaymentGatewayDataDump.config.ApplicationConfig;
import com.dhfl.OnlinePaymentGatewayDataDump.entity.DHFLCustomersEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.entity.FileUploadDetailsEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.repo.DHFLCustomersRepo;
import com.dhfl.OnlinePaymentGatewayDataDump.service.DHFLCustomersInter;
import com.dhfl.OnlinePaymentGatewayDataDump.service.FileUploadDetailsInter;
import com.dhfl.OnlinePaymentGatewayDataDump.test.ReadExcel;
import com.dhfl.OnlinePaymentGatewayDataDump.util.ReadExcelFile;
import com.ibm.db2.jcc.am.p;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@Component
public class OnlinePaymentGatewayOfflineVerifyCron {
	private static final Logger LOG = LoggerFactory.getLogger(OnlinePaymentGatewayOfflineVerifyCron.class);

	@Autowired
	ApplicationConfig config;
	
	@Autowired
	DHFLCustomersRepo respository;
	
	@Autowired
	DHFLCustomersInter dhflCustomersInter;
	
	@Autowired
	FileUploadDetailsInter fileUploadDetailsInter;
	
	ReadExcel readExcel = new ReadExcel();
	
	Logger logger = LoggerFactory.getLogger(OnlinePaymentGatewayOfflineVerifyCron.class);
	// @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
	@Scheduled(cron = "10 * * * * ?")
	public void publish() {
		int count = 0;
		try {			
			//readExcel.readXLSXFile();
			//FileUploadDetailsEntity fileUploadEntity = fileUploadDetailsInter.getUPloadedFile();
			List<FileUploadDetailsEntity> allUploadedFiles = fileUploadDetailsInter.getAllUploadedFilesU();
			if(allUploadedFiles.size()>0) {
				for(FileUploadDetailsEntity fileUploadEntity : allUploadedFiles) {
					System.out.println("File Ref Number="+fileUploadEntity.getFile_ref_num());
					System.out.println("File Upload Time="+fileUploadEntity.getFile_upload_time());
					System.out.println("File Upload Path="+fileUploadEntity.getFile_upload_path());
					//readExcel.readXLSXFile(fileUploadEntity.getFile_upload_path());
					String uploadFileName = fileUploadEntity.getFile_upload_path();
					String file_ref_num = fileUploadEntity.getFile_ref_num();
					fileUploadDetailsInter.updateFileStatusP(file_ref_num);			
					System.out.println("File Process Started.......(~|~)");
					final long start = System.currentTimeMillis();
					processUploadedFile(uploadFileName);
					System.out.println("File Process Completed ('-').......");
					System.out.println("Elapsed time: "+(System.currentTimeMillis() - start));
					fileUploadDetailsInter.updateFileStatusC(file_ref_num);
				}
			}
			System.out.println("Cron Started="+count);
		}catch (Exception e) {
			logger.debug("Exception@OnlinePaymentGatewayOfflineVerifyCron="+e);
			e.printStackTrace();
		}
		LOG.info("Average value is 0::--->>"+count++);
	}
	
	public void processUploadedFile(String uploadFileName) {
		int insertedRows = 0;
		int updatedRows = 0;
		String message = "";
		try {
			if (uploadFileName != null && (uploadFileName.contains(".xlsx") || uploadFileName.contains(".xls"))) {
				File initialFile = new File(uploadFileName);
				FileInputStream targetStream = new FileInputStream(initialFile);
				System.out.println("Input Stream=" + targetStream);
				// List<DHFLCustomersEntity> customers =
				// ExcelHelper.excelToTutorials(targetStream);
				//List<DHFLCustomersEntity> customers = ReadExcelFile.excelToTutorials(targetStream);
				List<DHFLCustomersEntity> customers = ReadExcelFile.parseExcelAndValidate(targetStream);
				int totalRows = customers != null || customers.size() > 0 ? customers.size() : 0;
				try {
					System.out.println("Customers Size====" + customers.size());
					if (customers.size() > 0) {
						System.out.println("Customers Size1====" + customers.size());
						List<String> brLoanCodes = respository.getAllBrLoanCodes();
						List<String> appNumbers = respository.getAllAppNumbers();
						//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>BrLoanCodes="+brLoanCodes);
						//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>appNumbers="+appNumbers);
						/*for(String loanCode : brLoanCodes) {
							System.out.println("Loan Code="+loanCode);
						}*/
						for (DHFLCustomersEntity entity : customers) {
							String applNo = entity.getApplno();
							String brLoanCode = entity.getBrloancode();
							System.out.println("ApplNumber----->>>>>" + applNo);
							// Validating data over list.contains
							if(brLoanCodes.size()>0 && appNumbers.size()>0) {
								if (!brLoanCodes.contains(brLoanCode) || !appNumbers.contains(applNo)) {
									System.out.println("New record inserting... AppNo="+applNo+" brLoanCode="+brLoanCode);
									//respository.save(entity);
									dhflCustomersInter.saveRecord(entity);
									insertedRows++;
								} else {
									// Update row
									System.out.println("Row already exists..Updating record..applNo="+applNo+" brLoanCode="+brLoanCode);
									dhflCustomersInter.updateCustomer(applNo, entity.getMinimumOverdueAmount(),
											entity.getTotalOverdueEMI(), entity.getTotalChargesAmount(),
											entity.getMinimumChargeAmount(), entity.getMobileno(), entity.getCustomername(),
											entity.getOverdueBlankField(), entity.getChargeBlankField());
									updatedRows++;
								}
							} else {
								DHFLCustomersEntity row = respository.searchByAppNoLoanCode(applNo, brLoanCode);
								if (row == null) {
									respository.save(entity);
									insertedRows++;
								} else {
									// Update row
									System.out.println("Row already exists..Updating record..");
									dhflCustomersInter.updateCustomer(applNo, entity.getMinimumOverdueAmount(),
											entity.getTotalOverdueEMI(), entity.getTotalChargesAmount(),
											entity.getMinimumChargeAmount(), entity.getMobileno(),
											entity.getCustomername(), entity.getOverdueBlankField(),
											entity.getChargeBlankField());
									updatedRows++;
								}
							}
						}
					}
				} catch (Exception e) {
					logger.debug("Exception@inserting customer data=" + e);				
				}
			} else {
				logger.debug("Invalid File format uploaded.");				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
