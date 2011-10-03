package edu.usm.roberge;

public class RestUrlResolver {

	public String resolveIndividualUrl(String individualId) {
		return "http://localhost:8090/openhds/api/rest/corewebservice/individual/" + individualId;
	}

	public String resolveFieldWorkerUrl(String fieldWorkerId) {
		return "http://localhost:8090/openhds/api/rest/corewebservice/fieldWorker/" + fieldWorkerId;
	}

	public String resolveVisitUrl(String visitId) {
		return "http://localhost:8090/openhds/api/rest/corewebservice/visit/" + visitId;
	}

	public String resolveDeathEventUrl() {
		return "http://localhost:8090/openhds/api/rest/corewebservice/death";
	}

	public String resolveAuditUrl() {
		return "http://localhost:8090/openhds/api/rest/corewebservice/errors";
	}

	public String resolveBirthEventUrl() {
		return "http://localhost:8090/openhds/api/rest/corewebservice/pregnancyoutcome";
	}
}
