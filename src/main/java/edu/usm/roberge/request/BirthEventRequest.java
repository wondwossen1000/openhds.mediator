package edu.usm.roberge.request;

import edu.usm.roberge.event.AbstractEvent;
import edu.usm.roberge.event.BirthRegistrationEvent;
import edu.usm.roberge.util.RestUrlResolver;

public class BirthEventRequest extends AbstractRequest {

	private final BirthRegistrationEvent event;

	public BirthEventRequest(BirthRegistrationEvent event, RestUrlResolver urlResolver) {
		super(urlResolver);
		this.event = event;
	}

	@Override
	public AbstractEvent getEvent() {
		return event;
	}

	@Override
	protected void validateFieldsBeforeRequest() {
		validateIndividualId(event.getMotherId(), "Mother Id could not be validated");
		validateIndividualId(event.getFatherId(), "Father Id could not be validated");
		validateVisitId(event.getVisitId());
		validateHouseId(event.getHouseId());
		validateHouseholdId(event.getHouseholdId());
	}

	@Override
	protected String getEventUrl() {
		return urlResolver.resolveBirthEventUrl();
	}

}
