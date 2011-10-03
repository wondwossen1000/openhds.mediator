package edu.usm.roberge.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.usm.roberge.AbstractEvent;
import edu.usm.roberge.RestUrlResolver;

public abstract class AbstractRequest {

	protected final List<String> errors;
	protected final RestUrlResolver urlResolver;

	public AbstractRequest(RestUrlResolver urlResolver) {
		this.urlResolver = urlResolver;
		this.errors = new ArrayList<String>();
	}

	public void sendRequest() {
		onBeforeSendRequest();
	
		if (errors.size() == 0) {
			RestTemplate template = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "text/xml");
			HttpEntity<String> entity = new HttpEntity<String>(getEvent().getXml(), headers);
			template.postForEntity(getEventUrl(), entity, String.class);
		}
	}

	protected abstract String getEventUrl();

	protected abstract void onBeforeSendRequest();
	
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
		// TODO
	}

	protected void validateHouseholdId(String householdId) {
		// TODO
	}

	private void sendHttpGetRequest(String url, String errorMsg) {
		RestTemplate template = new RestTemplate();
		try {
			template.getForEntity(url, String.class);			
		} catch(RestClientException e) {
			errors.add(errorMsg);
		}		
	}	
	
	public List<String> getErrors() {
		return errors;
	}
}