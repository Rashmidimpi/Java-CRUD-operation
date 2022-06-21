package com.mb.demo.web.model;

import java.util.Date;

public class BaseModel {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date createdAt;
	private Date lastModifiedAt;
	private boolean isDeleted;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
