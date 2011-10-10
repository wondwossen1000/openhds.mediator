package edu.usm.roberge.event;

public class BirthRegistrationEvent extends AbstractEvent {
	private String motherId;
	private String fatherId;
	private String houseId;
	private String householdId;
	private String visitId;
	
	public String getMotherId() {
		return motherId;
	}
	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getHouseholdId() {
		return householdId;
	}
	public void setHouseholdId(String householdId) {
		this.householdId = householdId;
	}
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

}
