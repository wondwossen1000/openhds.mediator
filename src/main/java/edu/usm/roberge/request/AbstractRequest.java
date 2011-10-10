package edu.usm.roberge.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.usm.roberge.event.AbstractEvent;
import edu.usm.roberge.util.RestUrlResolver;

/**
 * Abstract Request
 * A request will transmit an Event to be saved in the health application (HDS)
 * Typically, the event will have some validation to be performed it is sent
 * to the health application.
 * 
 * Implementors should override the {@link AbstractRequest#validateFieldsBeforeRequest()}
 * to perform any validations
 * 
 * The AbstractRequest also maintains a list of errors that may occur during validation
 */
public abstract class AbstractRequest {

	protected final List<String> errors;
	protected final RestUrlResolver urlResolver;

	public AbstractRequest(RestUrlResolver urlResolver) {
		this.urlResolver = urlResolver;
		this.errors = new ArrayList<String>();
	}

	public void sendRequest() {
		validateFieldsBeforeRequest();
	
		if (errors.size() == 0) {
			RestTemplate template = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "text/xml");
			HttpEntity<String> entity = new HttpEntity<String>(getEvent().getXml(), headers);
			template.postForEntity(getEventUrl(), entity, String.class);
		}
	}

	protected abstract String getEventUrl();

	protected abstract void validateFieldsBeforeRequest();
	
	public abstract AbstractEvent getEvent();

	protected void validateIndividualId(String individualId, String msg) {
		sendHttpGetRequest(urlResolver.resolveIndividualUrl(individualId), msg);
	}

	protected void validateFieldWorkerId(String fieldWorkerId) {
		sendHttpGetRequest(urlResolver.resolveFieldWorkerUrl(fieldWorkerId), "Field Worker Id could not be validated");
	}

	protected void validateVisitId(String visitId) {
		sendHttpGetRequest(urlResolver.resolveVisitUrl(visitId), "Visit Id could not be validated");
	}
	
	protected void validateHouseId(String houseId) {
		sendHttpGetRequest(urlResolver.resolveHouseUrl(houseId), "House Id could not be validated");
	}

	protected void validateHouseholdId(String householdId) {
		sendHttpGetRequest(urlResolver.resolveHouseholdUrl(householdId), "Household Id could not be validated");
	}

	private void sendHttpGetRequest(String url, String errorMsg) {
		RestTemplate template = new RestTemplate();
		try {
			ResponseEntity<String> resp = template.getForEntity(url, String.class);
			if (resp.getStatusCode() != HttpStatus.OK) {
				errors.add(errorMsg);
			}
		} catch(RestClientException e) {
			errors.add(errorMsg);
		}		
	}	
	
	public List<String> getErrors() {
		return errors;
	}
}