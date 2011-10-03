package edu.usm.roberge.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.usm.roberge.DeathRegistrationEvent;

/**
 * Converts a JSON encoded Death registration event from ODK
 */
public class DeathConverter {

	private final Map<String, String> mappings = new HashMap<String, String>();
	
	public DeathRegistrationEvent convertToXml(Map<String, String> jsonObject) {
		DeathRegistrationEvent deathRegistration = new DeathRegistrationEvent();
		
		if (!mappingsInitialized()) {
			initMappings();
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<death>");
		
		for(Entry<String, String> entry : jsonObject.entrySet()) {
			String xml = mappings.get(entry.getKey());
			String formatted = String.format(xml, entry.getValue());
			buf.append(formatted);
		}
		
		buf.append("</death>");
		deathRegistration.setXml(buf.toString());
		
		deathRegistration.setFieldWorkerId(jsonObject.get("basicInformation:fieldWorker"));
		deathRegistration.setIndividualId(jsonObject.get("basicInformation:permanentId"));
		deathRegistration.setVisitId(jsonObject.get("basicInformation:visitId"));
		deathRegistration.setInstanceId(jsonObject.get("meta:instanceId"));
		
		return deathRegistration;
	}
	
	private boolean mappingsInitialized() {
		return mappings.size() != 0;
	}

	private void initMappings() {
		mappings.put("meta:instanceId", "<instanceId>%s</instanceId>");
		mappings.put("basicInformation:visitId", "<visitDeath><extId>%s</extId></visitDeath>");
		mappings.put("basicInformation:fieldWorker", "<collectedBy><extId>%s</extId></collectedBy>");
		mappings.put("basicInformation:dateOfInterview", "<recordedDate>%s</recordedDate>");
		mappings.put("basicInformation:permanentId", "<individual><extId>%s</extId></individual>");
		mappings.put("basicInformation:houseId", "<house><extId>%s</extId></house>");
		mappings.put("basicInformation:householdName", "<householdName>%s</householdName>");
		mappings.put("basicInformation:householdId", "<household><extId>%s</extId></household>");
		mappings.put("basicInformation:dateOfDeath", "<deathDate>%s</deathDate>");
		mappings.put("basicInformation:deceasedName", "<deceasedName>%s</deceasedName>");
		mappings.put("basicInformation:sex", "<sex>%s</sex>");
		mappings.put("basicInformation:placeOfDeath", "<deathPlace>%s</deathPlace>");
		mappings.put("sourceOfInformation:reportedBy", "<reportedBy>%s</reportedBy>");
	}
}
