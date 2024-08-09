package com.customer.entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_order")
public class TableOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] image;
	private Double price;
	private Long quantity;
	private String time;
	private String status;
	private Long tableid;
	private String placeorder;

	public TableOrder() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTableid() {
		return this.tableid;
	}

	public void setTableid(Long tableid) {
		this.tableid = tableid;
	}

	public String getPlaceorder() {
		return this.placeorder;
	}

	public void setPlaceorder(String placeorder) {
		this.placeorder = placeorder;
	}

	@Override
	public String toString() {
		return "TableOrder [id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", image="
				+ Arrays.toString(this.image) + ", price=" + this.price + ", quantity=" + this.quantity + ", time="
				+ this.time + ", status=" + this.status + ", tableid=" + this.tableid + ", placeorder="
				+ this.placeorder + "]";
	}

}
