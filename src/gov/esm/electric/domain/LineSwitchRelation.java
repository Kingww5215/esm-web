package gov.esm.electric.domain;

import java.io.Serializable;

public class LineSwitchRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int linePartId;

	private int switchId;

	public LineSwitchRelation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLinePartId() {
		return this.linePartId;
	}

	public void linePartId(int linePartId) {
		this.linePartId = linePartId;
	}

	public int getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(int switchId) {
		this.switchId = switchId;
	}

}