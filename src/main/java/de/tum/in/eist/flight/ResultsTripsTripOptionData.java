package de.tum.in.eist.flight;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsTripsTripOptionData {

	private List<ResultsTripsTripOptionSliceData> slice;
	private List<ResultsTripsTripOptionPricingData> pricing;
	
	public List<ResultsTripsTripOptionSliceData> getSlice() {
		return slice;
	}
	public void setSlice(List<ResultsTripsTripOptionSliceData> slice) {
		this.slice = slice;
	}
	public List<ResultsTripsTripOptionPricingData> getPricing() {
		return pricing;
	}
	public void setPricing(List<ResultsTripsTripOptionPricingData> pricing) {
		this.pricing = pricing;
	}
	
	
}
