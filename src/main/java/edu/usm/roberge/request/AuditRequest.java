package edu.usm.roberge.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import edu.usm.roberge.RestUrlResolver;

/**
 * Request to register any errors in OpenHDS
 */
public class AuditRequest {

	private final AbstractRequest req;
	private final RestUrlResolver urlResolver;

	public AuditRequest(AbstractRequest req, RestUrlResolver urlResolver) {
		this.req = req;
		this.urlResolver = urlResolver;
	}

	public void sendRequest() {
		String xml = generateXml();
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "text/xml");
		HttpEntity<String> entity = new HttpEntity<String>(xml, headers);
		temp.postForEntity(urlResolver.resolveAuditUrl(), entity, String.class);
	}

	private String generateXml() {
		StringBuffer buf = new StringBuffer("<errors>");
		buf.append("<messageId>" + req.getEvent().getInstanceId() + "</messageId>");
		for(String error : req.getErrors()) {
			buf.append("<error><errorMessage>" + error + "</errorMessage></error>");
		}
		buf.append("</errors>");
		return buf.toString();
	}

}
