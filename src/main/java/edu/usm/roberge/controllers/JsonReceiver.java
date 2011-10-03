package edu.usm.roberge.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.usm.roberge.DeathRegistration;
import edu.usm.roberge.converters.DeathConverter;
import edu.usm.roberge.request.AuditRequest;
import edu.usm.roberge.request.DeathEventRequest;

/**
 * Receives the JSON payload from ODK Aggregate
 */
@Controller
@RequestMapping("/json")
public class JsonReceiver {

	@RequestMapping(value="/death", method=RequestMethod.POST)
	public @ResponseBody String registerDeathEvent(@RequestBody String jsonPayload) {
		// transform into valid JSON objects
		JsonTransformer transformer = new JsonTransformer();
		List<Map<String, String>> jsonObjs = transformer.transform(jsonPayload);

		// now convert the json objects to valid XML
		DeathConverter converter = new DeathConverter();
		List<DeathRegistration> deathEvents = new ArrayList<DeathRegistration>();
		
		for(Map<String, String> obj : jsonObjs) {
			deathEvents.add(converter.convertToXml(obj));
		}
		
		// now send the request
		for(DeathRegistration event : deathEvents) {
			DeathEventRequest req = new DeathEventRequest(event);
			req.sendRequest();
			AuditRequest errReq = new AuditRequest(req);
			errReq.sendRequest();
		}
		
		return "";
	}
}
