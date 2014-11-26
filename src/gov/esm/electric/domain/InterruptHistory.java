package gov.esm.electric.domain;

import java.io.Serializable;
import java.sql.Timestamp;


public class InterruptHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private Timestamp interruptTime;

	private int lineId;

	private int operater;

	private int switchId;

	public InterruptHistory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getInterruptTime() {
		return this.interruptTime;
	}

	public void setInterruptTime(Timestamp interruptTime) {
		this.interruptTime = interruptTime;
	}

	public int getLineId() {
		return this.lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public int getOperater() {
		return this.operater;
	}

	public void setOperater(int operater) {
		this.operater = operater;
	}

	public int getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(int switchId) {
		this.switchId = switchId;
	}

}