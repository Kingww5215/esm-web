package gov.esm.electric.domain;

import java.io.Serializable;

/**
 * 表示一个开关
 * 
 * @author XueLiang
 * @date 2014年11月27日
 */
public class CableSwitch implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String code;

	private String name;

	private String properties;

	private int status;

	public CableSwitch() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperties() {
		return this.properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}