package com.dhfl.OnlinePaymentGatewayDataDump.service;

import java.util.List;

import com.dhfl.OnlinePaymentGatewayDataDump.entity.FileUploadDetailsEntity;

public interface FileUploadDetailsInter {
	FileUploadDetailsEntity getUPloadedFile();
	int updateFileStatusP(String file_ref_num);
	int updateFileStatusC(String file_ref_num);
	int updateFileStatusE(String file_ref_num);
	List<FileUploadDetailsEntity> getAllUploadedFilesU();
	List<FileUploadDetailsEntity> getAllUploadedFilesP();
	List<FileUploadDetailsEntity> getAllUploadedFilesC();
	int updateFileStatusError(String file_ref_num, String error);
}
