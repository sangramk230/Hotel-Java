package com.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tables {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tableid;
	private String type;
	private String status;

	public Tables() {
		super();
	}

	public Long getTableid() {
		return this.tableid;
	}

	public void setTableid(Long tableid) {
		this.tableid = tableid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
