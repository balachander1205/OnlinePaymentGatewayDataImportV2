package com.dhfl.OnlinePaymentGatewayDataDump.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.dhfl.OnlinePaymentGatewayDataDump.entity.DHFLCustomersEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.entity.FileUploadValidationEntity;
import com.dhfl.OnlinePaymentGatewayDataDump.service.FileUploadDetailsInter;

public class ReadExcelFile_Bkp11062021 {
	
	@Autowired
	FileUploadDetailsInter fileUploadDetailsInter;
	
	private static final Logger LOG = LoggerFactory.getLogger(ReadExcelFile_Bkp11062021.class);
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "BrLoan Code", "Appl No", "Customer Name", "Mobile", "Overdue EMI", "Total Overdue EMI",
			"Minimum Overdue Amount", "Overdue Blank Field", "Charges", "Total Charges Amount", "Minimum Charge Amount",
			"Charge Blank Field" };
	static String SHEET = "Tutorials";
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	/*public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(
				new File("F:\\Freelance\\DHFL\\docs\\Click_To_Pay_Fields_15_06_2020-edited.xlsx"));
		// creating workbook instance that refers to .xls file
		List<DHFLCustomersEntity> customers = ReadExcelFile.parseExcelAndValidate(fis, "validationsFile");
		for (DHFLCustomersEntity customer : customers) {
			System.out.println("Name=" + customer.getCustomername() + " LoanCode=" + customer.getBrloancode()
					+ " AppNo=" + customer.getApplno() + " Total OverDue=" + customer.getTotalOverdueEMI()
					+ " MinOverDue=" + customer.getMinimumOverdueAmount() + " TotalCharges="
					+ customer.getTotalChargesAmount() + " MinCharges=" + customer.getMinimumChargeAmount()
					+ " Mobile Number=" + customer.getMobileno());
		}
	}*/

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<DHFLCustomersEntity> excelToTutorials(InputStream is) {
		try {
			// creating workbook instance that refers to .xls file
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);
			List<DHFLCustomersEntity> customers = new ArrayList<DHFLCustomersEntity>();
			int rowNumber = 0;
			System.out.println("Last Row Number " + sheet.getLastRowNum());
			int headerCount = 0;
			String[] headersArr = new String[12];
			boolean HEADERS_FLAG = false;
			for (Row row : sheet) {
				Row currentRow = row;
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					Iterator<Cell> headers = currentRow.iterator();
					while (headers.hasNext()) {
						Cell headerCell = headers.next();
						String headerValue = Validator.getCellValueByType(headerCell);
						// String headerTrimValue = trimTrailingBlanks(headerValue);
						headersArr[headerCount] = Validator.trimAdvanced(headerValue);
						headerCount++;
					}
					System.out.println("File Headers="+java.util.Arrays.toString(HEADERs));
					System.out.println("Uploaded File Headers="+java.util.Arrays.toString(headersArr));
					HEADERS_FLAG = Arrays.equals(HEADERs, headersArr);
					continue;
				}
				if (HEADERS_FLAG && !Validator.isRowEmpty(row)) {
					List<Cell> cells = new ArrayList<Cell>();
					int lastColumn = Math.max(row.getLastCellNum(), 5);
					for (int cn = 0; cn < lastColumn; cn++) {
						//Cell c = row.getCell(cn, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS//_NULL);
						Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
						cells.add(c);
					}
					Iterator<Cell> cellsInRow = cells.iterator();
							//currentRow.iterator();
					DHFLCustomersEntity tutorial = new DHFLCustomersEntity();
					int cellIdx = 0;
					while (cellsInRow.hasNext()) {
						Cell currentCell = cellsInRow.next();
						switch (cellIdx) {
						case 0:
							// tutorial.setId((long) currentCell.getNumericCellValue());
							Double bd = new Double(getCellValueByType(currentCell));
							// tutorial.setBrloancode(String.valueOf(bd.longValue()));
							String loanCode = "";
							if (getCellValueByType(currentCell).contains("E")) {
								bd = new Double(getCellValueByType(currentCell));
								loanCode = String.valueOf(bd.longValue());
							} else {
								loanCode = getCellValueByType(currentCell);
							}
							tutorial.setBrloancode(loanCode);
							break;
						case 1:
							tutorial.setApplno(getCellValueByType(currentCell));
							break;
						case 2:
							tutorial.setCustomername(getCellValueByType(currentCell));
							break;
						case 3:
							Double mobileNo = new Double(getCellValueByType(currentCell));
							tutorial.setMobileno(String.valueOf(mobileNo.longValue()));
							break;
						case 4:
							break;
						case 5:
							tutorial.setTotalOverdueEMI((long) Double.parseDouble(getCellValueByType(currentCell)));
							break;
						case 6:
							tutorial.setMinimumOverdueAmount(
									(long) Double.parseDouble(getCellValueByType(currentCell)));
							break;
						case 7:
							String overdueBlankField = getCellValueByType(currentCell) != null
							|| getCellValueByType(currentCell) != "" ? getCellValueByType(currentCell) : "0";
							tutorial.setOverdueBlankField((long) Double.parseDouble(overdueBlankField));
							//tutorial.setTotalChargesAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
							break;
						case 8:
							//tutorial.setTotalChargesAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
							//tutorial.setMinimumChargeAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
							break;
						case 9:
							tutorial.setTotalChargesAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
							//tutorial.setMinimumChargeAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
							//tutorial.setOverdueBlankField(Long.valueOf("0"));
							break;
						case 10:
							tutorial.setMinimumChargeAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
							/*String chargeBlankField = getCellValueByType(currentCell) != null
									|| getCellValueByType(currentCell) != "" ? getCellValueByType(currentCell) : "0";
							tutorial.setChargeBlankField((long) Double.parseDouble(chargeBlankField));*/
							//tutorial.setTotalChargesAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
							break;
						case 11:
							String chargeBlankField = getCellValueByType(currentCell) != null
							|| getCellValueByType(currentCell) != "" ? getCellValueByType(currentCell) : "0";
							tutorial.setChargeBlankField((long) Double.parseDouble(chargeBlankField));
							//tutorial.setMinimumChargeAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
							break;
						default:
							break;
						}
						cellIdx++;
					}
					//System.out.println("-------------------------");
					customers.add(tutorial);
				}
			}
			System.out.println("Same Headers="+Arrays.equals(HEADERs, headersArr));
			// workbook.close();
			return customers;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
	/*
	 * parse excel file with validations - 02/05/2021
	 * 
	 * */
	public List<DHFLCustomersEntity> parseExcelAndValidate(InputStream is, String file_ref_num) {
		String error = "";
		try {
			// creating workbook instance that refers to .xls file
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);
			List<DHFLCustomersEntity> customers = new ArrayList<DHFLCustomersEntity>();
			List<FileUploadValidationEntity> validations = new ArrayList<>();
			int rowNumber = 0;
			System.out.println("Last Row Number " + sheet.getLastRowNum());
			int headerCount = 0;
			String[] headersArr = new String[12];
			boolean HEADERS_FLAG = false;
			Validator validator = new Validator();
			for (Row row : sheet) {
				String validationDesc = "";
				Row currentRow = row;
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					Iterator<Cell> headers = currentRow.iterator();
					while (headers.hasNext()) {
						Cell headerCell = headers.next();
						String headerValue = getCellValueByType(headerCell);
						// String headerTrimValue = trimTrailingBlanks(headerValue);
						headersArr[headerCount] = trimAdvanced(headerValue);
						headerCount++;
					}
					System.out.println("File Headers="+java.util.Arrays.toString(HEADERs));
					System.out.println("Uploaded File Headers="+java.util.Arrays.toString(headersArr));
					HEADERS_FLAG = Arrays.equals(HEADERs, headersArr);
					continue;
				}
				FileUploadValidationEntity validation = new FileUploadValidationEntity();
				if (HEADERS_FLAG && !isRowEmpty(row)) {					
					try {
						List<Cell> cells = new ArrayList<Cell>();
						int lastColumn = Math.max(row.getLastCellNum(), 5);
						for (int cn = 0; cn < lastColumn; cn++) {
							//Cell c = row.getCell(cn, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
							Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
							cells.add(c);
						}
						Iterator<Cell> cellsInRow = cells.iterator();
								//currentRow.iterator();
						DHFLCustomersEntity tutorial = new DHFLCustomersEntity();
						DataFormatter formatter = new DataFormatter();
						int cellIdx = 0;
						while (cellsInRow.hasNext()) {
							Cell currentCell = cellsInRow.next();
							switch (cellIdx) {
							case 0:
								String loanCode = formatter.formatCellValue(currentCell);
								System.out.println("Case0:BrLoanCode:currentCell:val="+loanCode);
								// tutorial.setId((long) currentCell.getNumericCellValue());
								/*Double bd = new Double(getCellValueByType(currentCell));
								// tutorial.setBrloancode(String.valueOf(bd.longValue()));
								String loanCode = "";
								System.out.println("Case0:BrLoanCode:currentCell="+currentCell);
								System.out.println("Case0:BrLoanCode="+bd);								
								if (getCellValueByType(currentCell).contains("E")) {
									bd = new Double(getCellValueByType(currentCell));
									loanCode = String.valueOf(bd.longValue());
								} else {
									loanCode = getCellValueByType(currentCell);
								}*/
								validationDesc = validationDesc + validator.validateBRLoanCode(loanCode);
								tutorial.setBrloancode(loanCode);
								validation.setBrloancode(loanCode);
								break;
							case 1:
								String appNo = formatter.formatCellValue(currentCell);
								//String appNo = getCellValueByType(currentCell);
								validationDesc = validationDesc + validator.validateAppNo(appNo);
								tutorial.setApplno(appNo);
								validation.setApplno(appNo);
								System.out.println("Case0:appNo======="+appNo);
								break;
							case 2:
								String custName = getCellValueByType(currentCell);
								tutorial.setCustomername(custName);
								validation.setCustomername(custName);
								break;
							case 3:
								Double mobileNo = new Double(getCellValueByType(currentCell));
								validationDesc = validationDesc + validator.validateMobileNo(String.valueOf(mobileNo.longValue()));
								tutorial.setMobileno(String.valueOf(mobileNo.longValue()));
								validation.setMobileno(String.valueOf(mobileNo.longValue()));
								break;
							case 4:
								break;
							case 5:
								String totalOverDueEMI = getCellValueByType(currentCell);
								validationDesc = validationDesc + validator.validateTotalOverDueEMIAmount(totalOverDueEMI);
								tutorial.setTotalOverdueEMI((long) Double.parseDouble(totalOverDueEMI));
								validation.setTotalOverdueEMI(String.valueOf(Double.parseDouble(totalOverDueEMI)));
								break;
							case 6:
								String minimumOverdueAmount = getCellValueByType(currentCell);
								validationDesc = validationDesc + validator.validateMinOverDueEMIAmount(minimumOverdueAmount);
								tutorial.setMinimumOverdueAmount((long) Double.parseDouble(minimumOverdueAmount));
								validation.setMinimumOverdueAmount(minimumOverdueAmount);
								break;
							case 7:
								String overdueBlankField = getCellValueByType(currentCell) != null
								&& getCellValueByType(currentCell) != "" ? getCellValueByType(currentCell) : "0";
								validationDesc = validationDesc + validator.validateOverDueBlankField(overdueBlankField);
								tutorial.setOverdueBlankField((long) Double.parseDouble(overdueBlankField));
								validation.setOverdueBlankField(overdueBlankField);
								//tutorial.setTotalChargesAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
								break;
							case 8:
								break;
							case 9:
								String totalChargesAmount = getCellValueByType(currentCell);
								validationDesc = validationDesc + validator.validateTotalChargesAmount(totalChargesAmount);
								tutorial.setTotalChargesAmount((long) Double.parseDouble(totalChargesAmount));
								validation.setTotalChargesAmount(totalChargesAmount);
								break;
							case 10:
								String minimumChargeAmount = getCellValueByType(currentCell);
								validationDesc = validationDesc + validator.validateMinChargesAmount(minimumChargeAmount);
								tutorial.setMinimumChargeAmount((long) Double.parseDouble(minimumChargeAmount));
								validation.setMinimumChargeAmount(minimumChargeAmount);
								break;
							case 11:
								String chargeBlankField = getCellValueByType(currentCell) != null
								&& getCellValueByType(currentCell) != "" ? getCellValueByType(currentCell) : "0";
								validationDesc = validationDesc + validator.validateChargesBlankField(chargeBlankField);
								tutorial.setChargeBlankField((long) Double.parseDouble(chargeBlankField));
								validation.setChargeBlankField(chargeBlankField);
								//tutorial.setMinimumChargeAmount((long) Double.parseDouble(getCellValueByType(currentCell)));
								break;
							default:
								break;
							}
							cellIdx++;
						}
						validation.setDescription(validationDesc);
						System.out.println("-------------------------");
						System.out.println(validation.toString());
						//System.out.println("Validation Desc="+validationDesc+"\n");
						validations.add(validation);
						System.out.println("======validationDesc="+validationDesc+" size="+validationDesc.length());
						//if(validationDesc==null || validationDesc=="") {
						if(validationDesc.length()==0) {
							System.out.println("====validationDesc is false");
							validation.setDescription("Success");
							customers.add(tutorial);
						}
					}catch (Exception e) {
						validationDesc = validationDesc+" Error occured while parsing excel file=" + e.getMessage();
						validation.setDescription(validationDesc);
						validations.add(validation);
						LOG.debug("Xception:="+file_ref_num+"="+error);
					}
				}else {
					validationDesc = validationDesc + "File headers miss matched.";
					validation.setDescription(validationDesc);
					validations.add(validation);
				}
			}
			WriteToExcelFile excelFile = new WriteToExcelFile();
			excelFile.writeExcelValidationToFile(validations, file_ref_num);
			System.out.println("Same Headers="+Arrays.equals(HEADERs, headersArr));
			// workbook.close();
			return customers;
		} catch (Exception e) {
			error = error+" Error occured while parsing excel file=" + e.getMessage();
			LOG.debug("Xception1:"+file_ref_num+"="+error);
			System.out.println("Xception="+error);
			//fileUploadDetailsInter.updateFileStatusError(file_ref_num, error);
			LOG.debug("=====Updated error status to DB..---");
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
	public static String getCellValueByType(Cell cell) {
		String value = "0";
		if(cell!=null) {
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				value = String.valueOf(cell.getNumericCellValue()) != null
						|| String.valueOf(cell.getNumericCellValue()) != "" ? String.valueOf(cell.getNumericCellValue())
								: "0";
			}else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				value = String.valueOf(cell.getStringCellValue()) != null || String.valueOf(cell.getStringCellValue()) != ""
						? String.valueOf(cell.getStringCellValue())
						: "0";
			}else {
				value = "0";
			}
		}else {
			value = "0";
		}
		//System.out.println("Value----->>>" + value);
		return value;
	}

	public static boolean isRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		if (row.getLastCellNum() <= 0) {
			return true;
		}
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	public static String trimAdvanced(String value) {
		Objects.requireNonNull(value);
		int strLength = value.length();
		int len = value.length();
		int st = 0;
		char[] val = value.toCharArray();
		if (strLength == 0) {
			return "";
		}
		while ((st < len) && (val[st] <= ' ') || (val[st] == '\u00A0')) {
			st++;
			if (st == strLength) {
				break;
			}
		}
		while ((st < len) && (val[len - 1] <= ' ') || (val[len - 1] == '\u00A0')) {
			len--;
			if (len == 0) {
				break;
			}
		}
		return (st > len) ? "" : ((st > 0) || (len < strLength)) ? value.substring(st, len) : value;
	}
	
	public static String format2fDouble(String value) {
		return String.format("%.2f", value);
	}
}
