package edu.usm.roberge.event;

/**
 * An event that occurs in the target health application (HDS)
 * Examples include pregnancy observation, out migration, and deaths
 */
public abstract class AbstractEvent {

	private String xml;
	private String instanceId;

	public AbstractEvent() {
		super();
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

}