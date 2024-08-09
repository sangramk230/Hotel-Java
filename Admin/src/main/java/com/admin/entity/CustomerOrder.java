package com.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {
	// save all order aftercomplet dinning
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String completestatus;
	private Long tableid;
	private String name;
	private String description;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] image;
	private Double price;
	private String custname;
	private Long contact;
	private String category;

public CustomerOrder() {
	super();
	// TODO Auto-generated constructor stub
}

public Long getId() {
	return this.id;
}

public void setId(Long id) {
	this.id = id;
}

public String getCompletestatus() {
	return this.completestatus;
}

public void setCompletestatus(String completestatus) {
	this.completestatus = completestatus;
}

public Long getTableid() {
	return this.tableid;
}

public void setTableid(Long tableid) {
	this.tableid = tableid;
}

public String getName() {
	return this.name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return this.description;
}

public void setDescription(String description) {
	this.description = description;
}


public byte[] getImage() {
	return this.image;
}

public void setImage(byte[] image) {
	this.image = image;
}

public Double getPrice() {
	return this.price;
}

public void setPrice(Double price) {
	this.price = price;
}

public String getCustname() {
	return this.custname;
}

public void setCustname(String custname) {
	this.custname = custname;
}

public Long getContact() {
	return this.contact;
}

public void setContact(Long contact) {
	this.contact = contact;
}

public String getCategory() {
	return this.category;
}

public void setCategory(String category) {
	this.category = category;
}

}
