package com.dhfl.OnlinePaymentGatewayDataDump.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
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
			String decAmount[] = amount.split(".");
			if(decAmount[1]!=null && Integer.parseInt(decAmount[1])>0) {
				message = message + "\n| OverDueEMIAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateTotalOverDueEMIAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split(".");
			System.out.println("validateTotalOverDueEMIAmount:Amount="+amount);
			System.out.println("validateTotalOverDueEMIAmount:Amount="+decAmount[0]+" decimal="+decAmount);
			if(decAmount[1]!=null && Integer.parseInt(decAmount[1])>0) {
				message = message + "\n| TotalOverDueEMIAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateMinOverDueEMIAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split(".");
			if(decAmount[1]!=null && Integer.parseInt(decAmount[1])>0) {
				message = message + "\n| MinOverDueEMIAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateOverDueBlankField(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split(".");
			if(decAmount[1]!=null && Integer.parseInt(decAmount[1])>0) {
				message = message + "\n| OverDueBlankField contains decimal value | ";
			}
		}
		return message;
	}

	public String validateCharges(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split(".");
			if(decAmount[1]!=null && Integer.parseInt(decAmount[1])>0) {
				message = message + "\n| Charges contains decimal value | ";
			}
		}
		return message;
	}

	public String validateTotalChargesAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split(".");
			if(decAmount[1]!=null && Integer.parseInt(decAmount[1])>0) {
				message = message + "\n| TotalChargesAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateMinChargesAmount(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split(".");
			if(decAmount[1]!=null && Integer.parseInt(decAmount[1])>0) {
				message = message + "\n| MinChargesAmount contains decimal value | ";
			}
		}
		return message;
	}

	public String validateChargesBlankField(String amount) {
		String message = "";
		if(amount!=null && amount.contains(".")) {
			String decAmount[] = amount.split(".");
			if(decAmount[1]!=null && Integer.parseInt(decAmount[1])>0) {
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
			return true;
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

	public static void main(String[] args) {
		String mobileNo = "111111";
		Validator validator = new Validator();
		System.out.println(validator.validateBRLoanCode(mobileNo));
		System.out.println(validator.validateSpecialChars("AB12233"));
		System.out.println(validator.validateMobileNo("1111111111"));
	}
}
