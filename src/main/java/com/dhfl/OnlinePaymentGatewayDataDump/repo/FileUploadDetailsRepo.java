package com.dhfl.OnlinePaymentGatewayDataDump.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dhfl.OnlinePaymentGatewayDataDump.entity.FileUploadDetailsEntity;

@Repository
public interface FileUploadDetailsRepo extends CrudRepository<FileUploadDetailsEntity, String>{
	
	@Query("select uploadData from FileUploadDetailsEntity uploadData where uploadData.file_status = 'U'")
	List<FileUploadDetailsEntity> getAllUploadedFilesU();
	
	@Query("select uploadData from FileUploadDetailsEntity uploadData where uploadData.file_status = 'P'")
	List<FileUploadDetailsEntity> getAllUploadedFilesP();
	
	@Query("select uploadData from FileUploadDetailsEntity uploadData where uploadData.file_status = 'U'")
	FileUploadDetailsEntity getUploadedFile();
	
	@Query("select uploadData from FileUploadDetailsEntity uploadData where uploadData.file_status = 'C'")
	List<FileUploadDetailsEntity> getAllUploadedFilesC();
	
	@Transactional
	@Modifying
	@Query("update FileUploadDetailsEntity uploadData set file_status='P' where uploadData.file_ref_num =:file_ref_num")
	int updateFileStatusP(@Param("file_ref_num") String file_ref_num);
	
	@Transactional
	@Modifying
	@Query("update FileUploadDetailsEntity uploadData set file_status='C' where uploadData.file_ref_num =:file_ref_num")
	int updateFileStatusC(@Param("file_ref_num") String file_ref_num);
	
	@Transactional
	@Modifying
	@Query("update FileUploadDetailsEntity uploadData set file_status='E' where uploadData.file_ref_num =:file_ref_num")
	int updateFileStatusE(@Param("file_ref_num") String file_ref_num);
	
	@Transactional
	@Modifying
	@Query("update FileUploadDetailsEntity uploadData set uploadData.ext_col0=:error where uploadData.file_ref_num =:file_ref_num")
	int updateFileStatusError(@Param("file_ref_num") String file_ref_num, @Param("error") String error);
}
