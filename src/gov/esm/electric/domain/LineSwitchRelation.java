package gov.esm.electric.domain;

import java.io.Serializable;

public class LineSwitchRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	private int int_;

	private int lineId;

	private int switchId;

	public LineSwitchRelation() {
	}

	public int getInt_() {
		return this.int_;
	}

	public void setInt_(int int_) {
		this.int_ = int_;
	}

	public int getLineId() {
		return this.lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public int getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(int switchId) {
		this.switchId = switchId;
	}

}