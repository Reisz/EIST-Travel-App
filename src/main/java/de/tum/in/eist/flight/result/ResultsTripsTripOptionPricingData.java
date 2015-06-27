package de.tum.in.eist.flight.result;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsTripsTripOptionPricingData {

	private String saleTotal;
	private boolean refundable;
	
	public String getSaleTotal() {
		return saleTotal;
	}
	public void setSaleTotal(String saleTotal) {
		this.saleTotal = saleTotal;
	}
	public boolean isRefundable() {
		return refundable;
	}
	public void setRefundable(boolean refundable) {
		this.refundable = refundable;
	}
	
}
