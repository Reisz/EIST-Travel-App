package de.tum.in.eist.data.directions;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionsRouteData {
		private String summary;
		private DirectionsBoundsData bounds;
		private DirectionsFareData fare;
		private List<DirectionsLegData> legs;
		private DirectionsPolylineData overview_polyline;
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
		public DirectionsPolylineData getOverview_polyline() {
			return overview_polyline;
		}
		public void setOverview_polyline(DirectionsPolylineData overview_polyline) {
			this.overview_polyline = overview_polyline;
		}
}
