package gov.esm.electric.domain;

import java.io.Serializable;

public class UserRoleRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String roleId;

	private int userId;

	public UserRoleRelation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}