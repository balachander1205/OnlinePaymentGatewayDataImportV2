package com.dhfl.OnlinePaymentGatewayDataDump.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_file_upload_details", schema="dhfllive")
public class FileUploadDetailsEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "file_ref_num")
	private String file_ref_num;
	
	@Column(name = "file_upload_path")
	private String file_upload_path;
	
	@Column(name = "file_report_path")
	private String file_report_path;
	
	@Column(name = "file_upload_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date file_upload_time;
	
	@Column(name = "email_id")
	private String email_id;
	
	@Column(name = "file_status")
	private String file_status;
	
	@Column(name = "ext_col0")
	private String ext_col0;
	
	@Column(name = "ext_col1")
	private String ext_col1; 
	
	@Column(name = "ext_col2")
	private String ext_col2;
	
	@Column(name = "ext_col3")
	private String ext_col3;
	
	@Column(name = "ext_col4")
	private String ext_col4;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFile_ref_num() {
		return file_ref_num;
	}

	public void setFile_ref_num(String file_ref_num) {
		this.file_ref_num = file_ref_num;
	}

	public String getFile_upload_path() {
		return file_upload_path;
	}

	public void setFile_upload_path(String file_upload_path) {
		this.file_upload_path = file_upload_path;
	}

	public String getFile_report_path() {
		return file_report_path;
	}

	public void setFile_report_path(String file_report_path) {
		this.file_report_path = file_report_path;
	}

	public Date getFile_upload_time() {
		return file_upload_time;
	}

	public void setFile_upload_time(Date file_upload_time) {
		this.file_upload_time = file_upload_time;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getFile_status() {
		return file_status;
	}

	public void setFile_status(String file_status) {
		this.file_status = file_status;
	}

	public String getExt_col0() {
		return ext_col0;
	}

	public void setExt_col0(String ext_col0) {
		this.ext_col0 = ext_col0;
	}

	public String getExt_col1() {
		return ext_col1;
	}

	public void setExt_col1(String ext_col1) {
		this.ext_col1 = ext_col1;
	}

	public String getExt_col2() {
		return ext_col2;
	}

	public void setExt_col2(String ext_col2) {
		this.ext_col2 = ext_col2;
	}

	public String getExt_col3() {
		return ext_col3;
	}

	public void setExt_col3(String ext_col3) {
		this.ext_col3 = ext_col3;
	}

	public String getExt_col4() {
		return ext_col4;
	}

	public void setExt_col4(String ext_col4) {
		this.ext_col4 = ext_col4;
	}
}
