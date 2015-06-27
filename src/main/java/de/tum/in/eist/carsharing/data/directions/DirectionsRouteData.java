package de.tum.in.eist.carsharing.data.directions;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionsRouteData {

		private String summary;
		private DirectionsBoundsData bounds;
		private DirectionsFareData fare;
		private List<DirectionsLegData> legs = new ArrayList<DirectionsLegData>();
		private DirectionsPolylineData overview;
		
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public DirectionsBoundsData getBounds() {
			return bounds;
		}
		public void setBounds(DirectionsBoundsData bounds) {
			this.bounds = bounds;
		}
		public DirectionsFareData getFare() {
			return fare;
		}
		public void setFare(DirectionsFareData fare) {
			this.fare = fare;
		}
		public List<DirectionsLegData> getLegs() {
			return legs;
		}
		public void setLegs(List<DirectionsLegData> legs) {
			this.legs = legs;
		}
		public DirectionsPolylineData getOverview() {
			return overview;
		}
		public void setOverview(DirectionsPolylineData overview) {
			this.overview = overview;
		}
		
		
}
