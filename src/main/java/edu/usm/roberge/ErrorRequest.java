package edu.usm.roberge;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * Request to register any errors in OpenHDS
 */
public class ErrorRequest {

	private final DeathEventRequest req;

	public ErrorRequest(DeathEventRequest req) {
		this.req = req;
	}

	public void sendRequest() {
		String xml = generateXml();
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "text/xml");
		HttpEntity<String> entity = new HttpEntity<String>(xml, headers);
		temp.postForEntity("http://localhost:8090/openhds/api/rest/corewebservice/errors", entity, String.class);
	}

	private String generateXml() {
		StringBuffer buf = new StringBuffer("<errors>");
		buf.append("<messageId>" + req.getInstanceId() + "</messageId>");
		for(String error : req.getErrors()) {
			buf.append("<error><errorMessage>" + error + "</errorMessage></error>");
		}
		buf.append("</errors>");
		return buf.toString();
	}

}
