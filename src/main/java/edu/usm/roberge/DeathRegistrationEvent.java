package edu.usm.roberge;

/**
 * 
 */
public class DeathRegistrationEvent extends AbstractEvent {
	
	private String individualId;
	private String fieldWorkerId;
	private String visitId;
	
	public String getIndividualId() {
		return individualId;
	}
	public void setIndividualId(String individualId) {
		this.individualId = individualId;
	}
	public String getFieldWorkerId() {
		return fieldWorkerId;
	}
	public void setFieldWorkerId(String fieldWorkerId) {
		this.fieldWorkerId = fieldWorkerId;
	}
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
}
