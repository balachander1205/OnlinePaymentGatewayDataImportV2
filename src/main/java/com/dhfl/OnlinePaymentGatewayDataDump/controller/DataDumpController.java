package com.dhfl.OnlinePaymentGatewayDataDump.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.dhfl.OnlinePaymentGatewayDataDump.config.ApplicationConfig;
import com.dhfl.OnlinePaymentGatewayDataDump.entity.DHFLCustomersEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.entity.FileUploadDetailsEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.repo.DHFLCustomersRepo;
import com.dhfl.OnlinePaymentGatewayDataDump.repo.FileUploadDetailsRepo;
import com.dhfl.OnlinePaymentGatewayDataDump.service.DHFLCustomersInter;
import com.dhfl.OnlinePaymentGatewayDataDump.util.ExcelHelper;
import com.dhfl.OnlinePaymentGatewayDataDump.util.ReadExcelFile;
import com.ibm.db2.jcc.am.br;

@Controller
@RequestMapping("/data")
public class DataDumpController {
	Logger logger = LoggerFactory.getLogger(DataDumpController.class);

	@Autowired
	DHFLCustomersRepo respository;
	
	@Autowired
	DHFLCustomersInter dhflCustomersInter;
	
	//@Autowired
	//FileUploadDetailsEntity fileUploadDetailsEntity;
	
	@Autowired
	FileUploadDetailsRepo fileUploadDetailsRepo;
	
	@Autowired
	ApplicationConfig applicationConfig;

	@GetMapping("/fileupload")
	public String index(ModelMap model) {
		String message = null;
		String disable = "false";
		List<FileUploadDetailsEntity> listOfFilesU = fileUploadDetailsRepo.getAllUploadedFilesU();
		List<FileUploadDetailsEntity> listOfFilesP = fileUploadDetailsRepo.getAllUploadedFilesP();
		System.out.println("P type files = "+listOfFilesP.size() +" U type files = "+listOfFilesU.size());
		if(listOfFilesU.size() > 0 || listOfFilesP.size() > 0) {
			System.out.println("P type files1 = "+listOfFilesP.size() +" U type files = "+listOfFilesU.size());
			message = "Another file upload is in progress. Please refresh after sometime for new upload.";
			disable = "true";
		}
		model.put("message", message);
		model.put("disable", disable);
		return "upload";
	}

	@PostMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("emailID") String email,
			RedirectAttributes redirectAttributes, HttpSession httpSession) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		int insertedRows = 0;
		int updatedRows = 0;
		String message = "";
		try {
			// Save the uploaded file to this folder
			String UPLOADED_FOLDER = applicationConfig.getDataFileUploadLocation();
			byte[] bytes = file.getBytes();
			long CURR_TIME_MILES = System.currentTimeMillis();
			String FNAME = String.valueOf(CURR_TIME_MILES);
			Path path = Paths.get(UPLOADED_FOLDER + FNAME+"_"+file.getOriginalFilename());
			Files.write(path, bytes);
			// Get the file and save it somewhere
			System.out.println("UPLOADED EMAILID="+email);
			System.out.println("File Name==>>"+UPLOADED_FOLDER + FNAME+"_"+file.getOriginalFilename());
			String file_report_path = UPLOADED_FOLDER + FNAME+"_"+file.getOriginalFilename();
			redirectAttributes.addFlashAttribute("message", "File Upload is successful, data is being processed. Report will be mailed to "+email+".");
			redirectAttributes.addFlashAttribute("uploadStatus", null);
			
			Date date = new Date(CURR_TIME_MILES);
			FileUploadDetailsEntity fileUploadDetailsEntity = new FileUploadDetailsEntity();
			fileUploadDetailsEntity.setEmail_id(email);
			fileUploadDetailsEntity.setFile_ref_num(FNAME);
			fileUploadDetailsEntity.setFile_upload_path(file_report_path);
			fileUploadDetailsEntity.setFile_report_path(file_report_path);
			fileUploadDetailsEntity.setFile_status("U");
			fileUploadDetailsEntity.setFile_upload_time(date);
			fileUploadDetailsRepo.save(fileUploadDetailsEntity);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message",
					"File upload is not successful '" + file.getOriginalFilename() + "'");
			redirectAttributes.addFlashAttribute("uploadStatus", "true");
			redirectAttributes.addFlashAttribute("updatedRows", updatedRows);
			redirectAttributes.addFlashAttribute("insertedRows", insertedRows);
			return "redirect:/data/uploadStatus";
		}
		return "redirect:/data/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
	
	public String doRedirect(String uploadStatus, int updatedRows, int insertedRows, String message,
			RedirectAttributes redirectAttributes, HttpSession httpSession) {
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("uploadStatus", uploadStatus);
		redirectAttributes.addFlashAttribute("updatedRows", updatedRows);
		redirectAttributes.addFlashAttribute("insertedRows", insertedRows);
		return "redirect:/data/uploadStatus";
	}
	
	@GetMapping("/download")
    public ResponseEntity downloadFile1(
			@RequestParam("fileName") String fileName) throws Exception {

		System.out.println("fileName: " + fileName);
		File file = new File("E:\\DHFL/processed/" + fileName+".xlsx");
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
				// Content-Disposition
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				// Content-Type
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				// Contet-Length
				.contentLength(file.length()) //
				.body(resource);
	}
	
	/*
	 *	deleteCustomers
	 *	@param  redirectAttributes
	 *	@param httpSession
	 *	@return
	 *	Method to delete customer from DB.
	 * */
	@GetMapping("/purge")
	public String deleteCustomers(RedirectAttributes redirectAttributes, HttpSession httpSession) {
		dhflCustomersInter.deleteCustomers();
		redirectAttributes.addFlashAttribute("message", "Data deletion is successful.");
		redirectAttributes.addFlashAttribute("uploadStatus", null);
		return "redirect:/data/uploadStatus";
	}
}
