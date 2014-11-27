package gov.esm.electric.domain;

import java.io.Serializable;

/**
 * 表示一条完整线路
 * 
 * @author XueLiang
 * @date 2014年11月27日
 */
public class CableLine implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String code;

	private String name;

	private int parentId;

	private int status;

	public CableLine() {
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

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}