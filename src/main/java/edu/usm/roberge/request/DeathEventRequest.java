package edu.usm.roberge.request;

import edu.usm.roberge.event.AbstractEvent;
import edu.usm.roberge.event.DeathRegistrationEvent;
import edu.usm.roberge.util.RestUrlResolver;

public class DeathEventRequest extends AbstractRequest {

	final DeathRegistrationEvent event;
	
	public DeathEventRequest(DeathRegistrationEvent event, RestUrlResolver urlResolver) {
		super(urlResolver);
		this.event = event;
	}
	
	@Override
	public AbstractEvent getEvent() {
		return event;
	}
	
	@Override
	protected void validateFieldsBeforeRequest() {
		validateIndividualId(event.getIndividualId(), "Individual Id could not be validated");
		validateFieldWorkerId(event.getFieldWorkerId());
		validateVisitId(event.getVisitId());		
	}

	@Override
	protected String getEventUrl() {
		return urlResolver.resolveDeathEventUrl();
	}
}
