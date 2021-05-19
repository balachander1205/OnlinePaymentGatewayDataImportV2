package com.dhfl.OnlinePaymentGatewayDataDump.entity;

import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;

/*static String[] HEADERs = { "BrLoan Code", "Appl No", "Customer Name", "Mobile", "Overdue EMI", 
 * "Total Overdue EMI",
		"Minimum Overdue Amount", "Overdue Blank Field", 
		"Charges", "Total Charges Amount", "Minimum Charge Amount",
		"Charge Blank Fieldumn(Overdue Blank Field"" };*/
@Sheet
public class FileUploadValidationEntity {	
	@SheetColumn("BrLoan Code")
	private String brloancode;
	@SheetColumn("Appl No")
	private String applno;
	@SheetColumn("Customer Name")
	private String customername;
	@SheetColumn("Mobile")
	private String mobileno;
	@SheetColumn("Overdue EMI")
	private Long overdueEMI;
	@SheetColumn("Total Overdue EMI")
	private Long totalOverdueEMI;
	@SheetColumn("Minimum Overdue Amount")
	private Long minimumOverdueAmount;
	@SheetColumn("Overdue Blank Field")
	private Long overdueBlankField;
	@SheetColumn("Charges")
	private Long charges;
	@SheetColumn("Total Charges Amount")
	private Long totalChargesAmount;
	@SheetColumn("Minimum Charge Amount")
	private Long minimumChargeAmount;
	@SheetColumn("Charge Blank Field")
	private Long chargeBlankField;
	@SheetColumn("Description")
	public String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrloancode() {
		return brloancode;
	}

	public void setBrloancode(String brloancode) {
		this.brloancode = brloancode;
	}

	public String getApplno() {
		return applno;
	}

	public void setApplno(String applno) {
		this.applno = applno;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public Long getTotalOverdueEMI() {
		return totalOverdueEMI;
	}

	public void setTotalOverdueEMI(Long totalOverdueEMI) {
		totalOverdueEMI = totalOverdueEMI;
	}

	public Long getMinimumOverdueAmount() {
		return minimumOverdueAmount;
	}

	public void setMinimumOverdueAmount(Long minimumOverdueAmount) {
		minimumOverdueAmount = minimumOverdueAmount;
	}

	public Long getOverdueBlankField() {
		return overdueBlankField;
	}

	public void setOverdueBlankField(Long overdueBlankField) {
		overdueBlankField = overdueBlankField;
	}

	public Long getTotalChargesAmount() {
		return totalChargesAmount;
	}

	public void setTotalChargesAmount(Long totalChargesAmount) {
		totalChargesAmount = totalChargesAmount;
	}

	public Long getMinimumChargeAmount() {
		return minimumChargeAmount;
	}

	public void setMinimumChargeAmount(Long minimumChargeAmount) {
		minimumChargeAmount = minimumChargeAmount;
	}

	public Long getChargeBlankField() {
		return chargeBlankField;
	}

	public void setChargeBlankField(Long chargeBlankField) {
		chargeBlankField = chargeBlankField;
	}

	@Override
	public String toString() {
		return "[ Name=" + this.getCustomername() + " LoanCode=" + this.getBrloancode() + " AppNo=" + this.getApplno()
				+ " Total OverDue=" + this.getTotalOverdueEMI() + " MinOverDue=" + this.getMinimumOverdueAmount()
				+ " TotalCharges=" + this.getTotalChargesAmount() + " MinCharges=" + this.getMinimumChargeAmount()
				+ " Mobile Number=" + this.getMobileno() + " Description="+this.getDescription();
	}
}
