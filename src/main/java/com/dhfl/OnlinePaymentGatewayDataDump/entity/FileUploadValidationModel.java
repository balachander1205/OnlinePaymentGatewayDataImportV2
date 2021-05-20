package com.dhfl.OnlinePaymentGatewayDataDump.entity;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;

import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;

/*static String[] HEADERs = { "BrLoan Code", "Appl No", "Customer Name", "Mobile", "Overdue EMI", 
 * "Total Overdue EMI",
		"Minimum Overdue Amount", "Overdue Blank Field", 
		"Charges", "Total Charges Amount", "Minimum Charge Amount",
		"Charge Blank Fieldumn(Overdue Blank Field"" };*/
@Sheet
public class FileUploadValidationModel {
	@ExcelCell(0)
	@ExcelCellName("BrLoan Code")
	private String brloancode;
	@ExcelCell(1)
	@ExcelCellName("Appl No")
	private String applno;
	@ExcelCell(2)
	@ExcelCellName("Customer Name")
	private String customername;
	@ExcelCell(3)
	@ExcelCellName("Mobile")
	private String mobileno;
	@ExcelCell(4)
	@ExcelCellName("Overdue EMI")
	private String overdueEMI;
	@ExcelCell(5)
	@ExcelCellName("Total Overdue EMI")
	private String totalOverdueEMI;
	@ExcelCell(6)
	@ExcelCellName("Minimum Overdue Amount")
	private String minimumOverdueAmount;
	@ExcelCell(7)
	@ExcelCellName("Overdue Blank Field")
	private String overdueBlankField;
	@ExcelCell(8)
	@ExcelCellName("Charges")
	private String charges;
	@ExcelCell(9)
	@ExcelCellName("Total Charges Amount")
	private String totalChargesAmount;
	@ExcelCell(10)
	@ExcelCellName("Minimum Charge Amount")
	private String minimumChargeAmount;
	@ExcelCell(11)
	@ExcelCellName("Charge Blank Field")
	private String chargeBlankField;
	@ExcelCell(12)
	@ExcelCellName("Description")
	public String description;
	
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

	public String getOverdueEMI() {
		return overdueEMI;
	}

	public void setOverdueEMI(String overdueEMI) {
		this.overdueEMI = overdueEMI;
	}

	public String getTotalOverdueEMI() {
		return totalOverdueEMI;
	}

	public void setTotalOverdueEMI(String totalOverdueEMI) {
		this.totalOverdueEMI = totalOverdueEMI;
	}

	public String getMinimumOverdueAmount() {
		return minimumOverdueAmount;
	}

	public void setMinimumOverdueAmount(String minimumOverdueAmount) {
		this.minimumOverdueAmount = minimumOverdueAmount;
	}

	public String getOverdueBlankField() {
		return overdueBlankField;
	}

	public void setOverdueBlankField(String overdueBlankField) {
		this.overdueBlankField = overdueBlankField;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public String getTotalChargesAmount() {
		return totalChargesAmount;
	}

	public void setTotalChargesAmount(String totalChargesAmount) {
		this.totalChargesAmount = totalChargesAmount;
	}

	public String getMinimumChargeAmount() {
		return minimumChargeAmount;
	}

	public void setMinimumChargeAmount(String minimumChargeAmount) {
		this.minimumChargeAmount = minimumChargeAmount;
	}

	public String getChargeBlankField() {
		return chargeBlankField;
	}

	public void setChargeBlankField(String chargeBlankField) {
		this.chargeBlankField = chargeBlankField;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[ Name=" + this.getCustomername() + " LoanCode=" + this.getBrloancode() + " AppNo=" + this.getApplno()
				+ " Total OverDue=" + this.getTotalOverdueEMI() + " MinOverDue=" + this.getMinimumOverdueAmount()
				+ " TotalCharges=" + this.getTotalChargesAmount() + " MinCharges=" + this.getMinimumChargeAmount()
				+ " Mobile Number=" + this.getMobileno() + " Description="+this.getDescription();
	}
}
