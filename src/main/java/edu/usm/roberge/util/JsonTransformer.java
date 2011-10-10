package edu.usm.roberge.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Transforms the String payload sent by ODK to valid JSON objects
 * You cannot have the jackson library do this automatically because
 * of the way ODK sends its formatted JSON objects.
 * There are times ODK sends strings like the following:
 * {
 *   properties:..
 * }
 * {
 *   properties:...
 * }
 * 
 * As of writing, the jackson library threw exceptions when the JSON came
 * in like that. This class is meant to massage that payload and process the
 * objects separately.
 * 
 * A better solution might be to physically fix the ODK implementation, or coordinate
 * modifying it to work with Jackson and other libraries.
 */
public class JsonTransformer {

	private final static ObjectMapper objMapper = new ObjectMapper();
	private final static Pattern JSON_OBJECT_PATTERN = Pattern.compile("\\{[^\\}]*\\}");
	
	public List<Map<String, String>> transform(String payload) {
		List<String> jsonObjects = parseJsonObjects(payload);
		List<Map<String, String>> formInstances = new ArrayList<Map<String, String>>();
		
		for(String jsonObject : jsonObjects) {
			Map<String, String> jsonData = convertJsonStringToMap(jsonObject);
			formInstances.add(jsonData);
		}
		
		return formInstances;
	}

	private List<String> parseJsonObjects(String payload) {
		List<String> matches = new ArrayList<String>();
		Matcher matcher = JSON_OBJECT_PATTERN.matcher(payload);
		while(matcher.find()) {
			matches.add(matcher.group());
		}
		
		return matches;
	}

	private Map<String, String> convertJsonStringToMap(String payload) {
		try {
			return objMapper.readValue(payload, Map.class);
		} catch (Exception e) {
			throw new IllegalArgumentException("Bad JSON value");
		}
	}
}
