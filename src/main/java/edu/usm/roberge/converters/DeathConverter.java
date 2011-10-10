package edu.usm.roberge.converters;

import java.util.Map;

import edu.usm.roberge.event.DeathRegistrationEvent;

/**
 * Converts a JSON encoded Death registration event from ODK
 */
public class DeathConverter extends AbstractConverter<DeathRegistrationEvent> {

	public DeathRegistrationEvent convertToXml(Map<String, String> jsonObject) {
		DeathRegistrationEvent deathRegistration = new DeathRegistrationEvent();
		
		if (!mappingsInitialized()) {
			initMappings();
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<death>");
		
		extractValuesWithMapping(jsonObject, buf);
		
		buf.append("</death>");
		deathRegistration.setXml(buf.toString());
		
		deathRegistration.setFieldWorkerId(jsonObject.get("basicInformation:fieldWorker"));
		deathRegistration.setIndividualId(jsonObject.get("basicInformation:permanentId"));
		deathRegistration.setVisitId(jsonObject.get("basicInformation:visitId"));
		deathRegistration.setInstanceId(jsonObject.get("meta:instanceId"));
		
		return deathRegistration;
	}

	@Override
	protected void initMappings() {
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
