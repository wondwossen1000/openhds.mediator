package edu.usm.roberge.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.usm.roberge.event.AbstractEvent;

/**
 * Abstract Converter
 * A converter is responsible for transforming an incoming JSON request to the underlying
 * Java object. Unforuntuately, this process is probably more complicated then it needs to
 * be because of the way ODK sends a form instance
 * 
 * @param <T> The HDS event that will be produced by the conversion
 * (birth, pregnancy observation, etc.)
 */
public abstract class AbstractConverter<T extends AbstractEvent> {

	protected final Map<String, String> mappings = new HashMap<String, String>();
	
	public abstract T convertToXml(Map<String, String> jsonObject);

	protected abstract void initMappings();
	
	protected boolean mappingsInitialized() {
		return mappings.size() != 0;
	}

	protected void extractValuesWithMapping(Map<String, String> jsonObject, StringBuffer buf) {
		for(Entry<String, String> entry : jsonObject.entrySet()) {
			String xml = mappings.get(entry.getKey());
			String formatted = String.format(xml, entry.getValue());
			buf.append(formatted);
		}
	}
}