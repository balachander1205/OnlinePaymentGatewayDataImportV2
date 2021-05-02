package com.dhfl.OnlinePaymentGatewayDataDump.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhfl.OnlinePaymentGatewayDataDump.entity.FileUploadDetailsEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.repo.FileUploadDetailsRepo;

@Service
public class FileUploadDetailsInterImpl implements FileUploadDetailsInter{
	@Autowired 
	FileUploadDetailsRepo fileUploadServiceRepo;

	@Override
	public FileUploadDetailsEntity getUPloadedFile() {
		return fileUploadServiceRepo.getUploadedFile();
	}

	@Override
	public int updateFileStatusP(String file_ref_num) {
		return fileUploadServiceRepo.updateFileStatusP(file_ref_num);
	}

	@Override
	public int updateFileStatusC(String file_ref_num) {
		return fileUploadServiceRepo.updateFileStatusC(file_ref_num);
	}

	@Override
	public List<FileUploadDetailsEntity> getAllUploadedFile() {
		return fileUploadServiceRepo.getAllUploadedFile();
	}
	
}
