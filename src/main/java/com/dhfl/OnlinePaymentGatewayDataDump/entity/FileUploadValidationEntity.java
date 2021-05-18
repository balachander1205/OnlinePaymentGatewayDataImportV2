package com.dhfl.OnlinePaymentGatewayDataDump.entity;

public class FileUploadValidationEntity {
	public String description;
	
	private String brloancode;

	private String applno;

	private String customername;

	private String mobileno;

	private Long TotalOverdueEMI;

	private Long MinimumOverdueAmount;

	private Long OverdueBlankField;

	private Long TotalChargesAmount;

	private Long MinimumChargeAmount;
	
	private Long ChargeBlankField;

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
		return TotalOverdueEMI;
	}

	public void setTotalOverdueEMI(Long totalOverdueEMI) {
		TotalOverdueEMI = totalOverdueEMI;
	}

	public Long getMinimumOverdueAmount() {
		return MinimumOverdueAmount;
	}

	public void setMinimumOverdueAmount(Long minimumOverdueAmount) {
		MinimumOverdueAmount = minimumOverdueAmount;
	}

	public Long getOverdueBlankField() {
		return OverdueBlankField;
	}

	public void setOverdueBlankField(Long overdueBlankField) {
		OverdueBlankField = overdueBlankField;
	}

	public Long getTotalChargesAmount() {
		return TotalChargesAmount;
	}

	public void setTotalChargesAmount(Long totalChargesAmount) {
		TotalChargesAmount = totalChargesAmount;
	}

	public Long getMinimumChargeAmount() {
		return MinimumChargeAmount;
	}

	public void setMinimumChargeAmount(Long minimumChargeAmount) {
		MinimumChargeAmount = minimumChargeAmount;
	}

	public Long getChargeBlankField() {
		return ChargeBlankField;
	}

	public void setChargeBlankField(Long chargeBlankField) {
		ChargeBlankField = chargeBlankField;
	}

	@Override
	public String toString() {
		return "[ Name=" + this.getCustomername() + " LoanCode=" + this.getBrloancode() + " AppNo=" + this.getApplno()
				+ " Total OverDue=" + this.getTotalOverdueEMI() + " MinOverDue=" + this.getMinimumOverdueAmount()
				+ " TotalCharges=" + this.getTotalChargesAmount() + " MinCharges=" + this.getMinimumChargeAmount()
				+ " Mobile Number=" + this.getMobileno() + " Description="+this.getDescription();
	}
}
