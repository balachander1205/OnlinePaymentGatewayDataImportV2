package com.dhfl.OnlinePaymentGatewayDataDump.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhfl.OnlinePaymentGatewayDataDump.entity.DHFLCustomersEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.entity.FileUploadDetailsEntity;

@Repository
public interface FileUploadDetailsRepo extends CrudRepository<FileUploadDetailsEntity, String>{
	@Query("select uploadData from FileUploadDetailsEntity uploadData where uploadData.file_status = 'U'")
	FileUploadDetailsEntity getUploadedFile();
	
	@Transactional
	@Modifying
	@Query("update FileUploadDetailsEntity uploadData set file_status='P' where uploadData.file_ref_num =:file_ref_num")
	int updateFileStatusP(@Param("file_ref_num") String file_ref_num);
	
	@Transactional
	@Modifying
	@Query("update FileUploadDetailsEntity uploadData set file_status='C' where uploadData.file_ref_num =:file_ref_num")
	int updateFileStatusC(@Param("file_ref_num") String file_ref_num);
}
