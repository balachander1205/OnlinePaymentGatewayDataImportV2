package com.dhfl.OnlinePaymentGatewayDataDump.util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Validator {
	static String[] HEADERs = { "BrLoan Code", "Appl No", "Customer Name", "Mobile", "Overdue EMI", "Total Overdue EMI",
			"Minimum Overdue Amount", "Overdue Blank Field", "Charges", "Total Charges Amount", "Minimum Charge Amount",
			"Charge Blank Field" };
	
	public String validateBRLoanCode(String brLoanCode) {
		String message = "";
		boolean specialChars = validateSpecialChars(brLoanCode);
		if (brLoanCode.length() < 11) {
			message = message + "| BR LoanCode is less than 11 digits/chars | ";
		}if (!specialChars) {
			if (isConsecutive(brLoanCode)) {
				message = message + "\n| BR LoanCode is having consecutive digits/chars | ";
			}
		}if (specialChars) {
			message = message + "\n| BR LoanCode has special chars | ";
		}
		return message;
	}

	public String validateAppNo(String appNo) {
		String message = "";
		boolean specialChars = validateSpecialChars(appNo);
		if (appNo.length() < 8) {
			message = message + "\n| Application number is less than 8 digits/chars | ";
		}if (!specialChars) {
			if (isConsecutive(appNo)) {
				message = message + "\n| Application number is having consecutive digits/chars | ";
			}
		}if (specialChars) {
			message = message + "\n| Application Number has special chars | ";
		}
		return message;
	}

	public String validateMobileNo(String mobileNo) {
		String message = "";
		boolean specialChars = validateSpecialChars(mobileNo);
		if (mobileNo.length() < 10) {
			message = message + "\n| Mobile number is less than 10 digits | ";
		}if (specialChars) {
			message = message + "\n| Mobile number is having special chars | ";
		}if (!specialChars) {
			if (isConsecutive(mobileNo)) {
				message = message + "\n| Mobile number is having consecutive digits | ";
			}
		}if(isMobileNoValid(mobileNo)) {
			message = message + "\n| Mobile number is Invalid | ";
		}
		return message;
	}

	public String validateOverDueEMIAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split("\\.");
			if(decAmount[1]!=null && decAmount[1].length()>=2) {
				message = message + "\n| OverDueEMIAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateTotalOverDueEMIAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split("\\.");
			System.out.println("validateTotalOverDueEMIAmount:Amount="+amount);
			System.out.println("validateTotalOverDueEMIAmount:Amount="+decAmount[0]+" decimal="+decAmount);
			if(decAmount[1]!=null && decAmount[1].length()>=2) {
				message = message + "\n| TotalOverDueEMIAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateMinOverDueEMIAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split("\\.");
			if(decAmount[1]!=null && decAmount[1].length()>=2) {
				message = message + "\n| MinOverDueEMIAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateOverDueBlankField(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split("\\.");
			if(decAmount[1]!=null && decAmount[1].length()>=2) {
				message = message + "\n| OverDueBlankField contains decimal value | ";
			}
		}
		return message;
	}

	public String validateCharges(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split("\\.");
			if(decAmount[1]!=null && decAmount[1].length()>=2) {
				message = message + "\n| Charges contains decimal value | ";
			}
		}
		return message;
	}

	public String validateTotalChargesAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split("\\.");
			if(decAmount[1]!=null && decAmount[1].length()>=2) {
				message = message + "\n| TotalChargesAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateMinChargesAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split("\\.");
			if(decAmount[1]!=null && decAmount[1].length()>=2) {
				message = message + "\n| MinChargesAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateChargesBlankField(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split("\\.");
			if(decAmount[1]!=null && decAmount[1].length()>=2) {
				message = message + "\n| ChargesBlankField contains decimal value | ";
			}
		}
		return message;
	}

	/*
	 * validateSpecialChars
	 * @param text
	 * @return 
	 * -- Method to validate text string for special characters.
	 */
	public static boolean validateSpecialChars(String text) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(text);
		if (!matcher.matches()) {
			return true;
		}
		return false;
	}

	/*
	 * isConsecutive
	 * @param text
	 * @return -- Method to check consecutiveness in text string.
	 */
	public static boolean isConsecutive(String text) {
		try {
			int[] digits = new int[text.length()];
			int[] differences = new int[text.length() - 1];
			int temp = 0;
			for (int i = 0; i < text.length(); i++) {
				digits[i] = Integer.parseInt(String.valueOf(text.charAt(i)));
			}
			for (int i = 0; i < digits.length - 1; i++) {
				differences[i] = Math.abs(digits[i] - digits[i + 1]);
			}
			if (differences.length != 0) {
				temp = differences[0];
				for (int i = 1; i < differences.length; i++)
					if (temp != differences[i])
						return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isMobileNoValid(String mobileNo) {
		Pattern pattern = Pattern.compile("^[6-9]\\d{9}$");
		Matcher matcher = pattern.matcher(mobileNo);
		if (!matcher.matches()) {
			return true;
		}
		return false;
	}
	
	// validate excel file
	public static String validateExcelFile(InputStream is) {
		String message = "";
		boolean HEADERS_FLAG = false;
		try {
			int rowNumber = 0;			
			int headerCount = 0;
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);
			String[] headersArr = new String[12];
			int totalRows = sheet.getLastRowNum();			
			if(totalRows>1) {
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
						System.out.println("Validator:File Headers="+java.util.Arrays.toString(HEADERs));
						System.out.println("Validator:Uploaded File Headers="+java.util.Arrays.toString(headersArr));
						HEADERS_FLAG = Arrays.equals(HEADERs, headersArr);
					}
					if (!HEADERS_FLAG) {
						message = message + "| Headres Miss Match. File upload failed |\n";
						break;
					}if(isRowEmpty(row)) {
						message = message + "| Rows are empty. |\n";
						break;
					}
				}
			}else {
				message = message + "| Rows are empty |";
			}
		} catch (Exception e) {
			if (!HEADERS_FLAG) {
				message = message + "| Headres Miss Match. File upload failed |\n";
			}
			message = message + " | Exception = "+e.getMessage() + " |";
			e.printStackTrace(System.out);
		}
		return message;
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
	
	public String validateCustomerName(String custName) {
		String message = "";
		try {
			if(custName==null || custName=="" || custName=="0" || custName.length()<=0) {
				message = "\n| Customer name is empty. |";
			}			
		}catch (Exception e) {
			message = "\n| Invalid Customer name. |";
		}
		return message;
	}
	
	public String validateParseError(String text, String type) {
		String message = "";
		try {
			Double mobileNo = new Double(text);
		}catch (Exception e) {
			message = "\n| Invalid "+type+" |";
			e.printStackTrace();
		}
		return message;
	}
	public static void main(String[] args) {
		String mobileNo = "111111";
		Validator validator = new Validator();
		System.out.println(validator.validateBRLoanCode(mobileNo));
		System.out.println(validator.validateSpecialChars("AB12233"));
		System.out.println(validator.validateMobileNo("1111111111"));
	}
}
