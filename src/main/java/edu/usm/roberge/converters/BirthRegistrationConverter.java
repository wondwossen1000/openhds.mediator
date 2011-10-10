package edu.usm.roberge.converters;

import java.util.Map;

import edu.usm.roberge.event.BirthRegistrationEvent;

public class BirthRegistrationConverter extends AbstractConverter<BirthRegistrationEvent> {

	@Override
	public BirthRegistrationEvent convertToXml(Map<String, String> jsonObject) {
		BirthRegistrationEvent birthRegistration = new BirthRegistrationEvent();
		
		if (!mappingsInitialized()) {
			initMappings();
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<pregnancyoutcome>");
		
		extractValuesWithMapping(jsonObject, buf);
		
		buf.append("</pregnancyoutcome>");
		birthRegistration.setXml(buf.toString());
		
		birthRegistration.setFatherId(jsonObject.get("fatherId"));
		birthRegistration.setHouseholdId(jsonObject.get("householdId"));
		birthRegistration.setHouseId(jsonObject.get("houseId"));
		birthRegistration.setMotherId(jsonObject.get("motherId"));
		birthRegistration.setVisitId(jsonObject.get("visitId"));
		
		return birthRegistration;
	}

	@Override
	protected void initMappings() {
		mappings.put("motherId", "<mother><extId>%s</extId></mother>");
		mappings.put("motherName", "<householdName>%s</householdName>");
		mappings.put("fatherId", "<father><extId>%s</extId></father>");
		mappings.put("firstLiveBirth", "<firstLiveBirth>%s</firstLiveBirth>");
		mappings.put("totalChildrenBorn", "<totalChildrenBorn>%s</totalChildrenBorn>");
		mappings.put("fatherName", "<nameOfFather>%s</nameOfFather>");
		mappings.put("numLiveBirthsFromRecentPreg", "<numLiveBirths>%s</numLiveBirths>");
		mappings.put("meta:instanceId", "<instanceId>%s</instanceId>");
		mappings.put("houseId", "<house><extId>%s</extId></house>");
		mappings.put("householdName", "<householdName>%s</householdName>");
		mappings.put("birthPlaceOther", "<placeOfBirthOther>%s</placeOfBirthOther>");
		mappings.put("motherName", "<nameOfMother>%s</nameOfMother>");
		mappings.put("umbilicalCordApplied", "<umbilicalCordCut>%s</umbilicalCordCut>");
		mappings.put("visitId", "<visit><extId>%s</extId></visit>");
		mappings.put("reportedBy", "<reportedBy>%s</reportedBy>");
		mappings.put("recordedDate", "<recordedDate>%s</recordedDate>");
		mappings.put("householdId", "<household><extId>%s</extId></household>");
		mappings.put("childDob", "<childDob>%s</childDob>");
		mappings.put("collectedBy", "<collectedBy><extId>%s</extId></collectedBy>");
		mappings.put("umbilicalCordCutting", "<umbilicalCord>%s</umbilicalCord>");
		mappings.put("birthPlace", "<placeOfBirth>%s</placeOfBirth>");
		mappings.put("numLiveBirths", "<numLiveBirths>%s</numLiveBirths>");
		mappings.put("motherLineNumber", "<motherLineNumber>%s</motherLineNumber>");

		mappings.put("children:firstChildId", "<child1><extId>%s</extId></child1>");
		mappings.put("children:firstChildFirstName", "<child1><firstName>%s</firstName></child1>");
		mappings.put("children:firstChildLastName", "<child1><lastName>%s</lastName></child1>");
		mappings.put("children:firstChildSex", "<child1><gender>%s</gender></child1>");

		mappings.put("children:secondChildId", "<child2><extId>%s</extId></child2>");
		mappings.put("children:secondChildFirstName", "<child2><firstName>%s</firstName></child2>");
		mappings.put("children:secondChildLastName", "<child2><lastName>%s</lastName></child2>");
		mappings.put("children:secondChildSex", "<child2><gender>%s</gender></child2>");
	}

}
