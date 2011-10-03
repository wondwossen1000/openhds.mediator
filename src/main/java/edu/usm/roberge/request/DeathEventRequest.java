package edu.usm.roberge.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import edu.usm.roberge.DeathRegistration;

public class DeathEventRequest {

	private final DeathRegistration event;
	private final static String URL = "http://localhost:8090/openhds/api/rest/corewebservice/death";
	private ResponseEntity<String> response;
	private final List<String> errors;
	
	public DeathEventRequest(DeathRegistration event2) {
		this.event = event2;
		this.errors = new ArrayList<String>();
	}

	public boolean didSucceed() {
		return false;
	}

	public void sendRequest() {
		validateIndividualId();
		validateFieldWorkerId();
		validateVisitId();

		if (errors.size() == 0) {
			RestTemplate template = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "text/xml");
			HttpEntity<String> entity = new HttpEntity<String>(event.getXml(), headers);
			response = template.postForEntity(URL, entity, String.class);
		}
	}

	private void validateIndividualId() {
		RestTemplate template = new RestTemplate();
		try {
			template.getForEntity("http://localhost:8090/openhds/api/rest/corewebservice/individual/" + event.getIndividualId(), String.class);
		} catch(RestClientException e) {
			errors.add("Individual Id could not be validated");
		}
	}

	private void validateFieldWorkerId() {
		RestTemplate template = new RestTemplate();
		try {
			template.getForEntity("http://localhost:8090/openhds/api/rest/corewebservice/fieldWorker/" + event.getFieldWorkerId(), String.class);			
		} catch(RestClientException e) {
			errors.add("Field Worker Id could not be validated");
		}
	}
	
	private void validateVisitId() {
		RestTemplate template = new RestTemplate();
		try {
			template.getForEntity("http://localhost:8090/openhds/api/rest/corewebservice/visit/" + event.getVisitId(), String.class);			
		} catch(RestClientException e) {
			errors.add("Visit Id could not be validated");
		}

	}

	public String getInstanceId() {
		return event.getInstanceId();
	}

	public List<String> getErrors() {
		return errors;
	}
}
