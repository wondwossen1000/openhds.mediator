package edu.usm.roberge.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.usm.roberge.DeathRegistrationEvent;
import edu.usm.roberge.RestUrlResolver;
import edu.usm.roberge.converters.DeathConverter;
import edu.usm.roberge.request.AbstractRequest;
import edu.usm.roberge.request.AuditRequest;
import edu.usm.roberge.request.DeathEventRequest;

/**
 * Receives the JSON payload from ODK Aggregate
 */
@Controller
@RequestMapping("/json")
public class JsonReceiver {

	private JsonTransformer transformer = new JsonTransformer();
	private final RestUrlResolver resolver = new RestUrlResolver();

	@RequestMapping(value="/death", method=RequestMethod.POST)
	public @ResponseBody String registerDeathEvent(@RequestBody String jsonPayload) {
		List<Map<String, String>> jsonObjs = transformer.transform(jsonPayload);

		// now convert the json objects to valid XML
		DeathConverter converter = new DeathConverter();
		List<DeathRegistrationEvent> deathEvents = new ArrayList<DeathRegistrationEvent>();
		
		for(Map<String, String> obj : jsonObjs) {
			deathEvents.add(converter.convertToXml(obj));
		}
		
		// now send the request
		for(DeathRegistrationEvent event : deathEvents) {
			AbstractRequest req = new DeathEventRequest(event, resolver);
			req.sendRequest();
			AuditRequest auditReq = new AuditRequest(req, resolver);
			auditReq.sendRequest();
		}
		
		return "";
	}
	
	@RequestMapping(value="/birth", method=RequestMethod.POST)
	public @ResponseBody String registerBirthEvent(@RequestBody String jsonPayload) {
		return null;
	}
}
