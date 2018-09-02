package com.rest.service.cachingservice;

import java.io.Serializable;

/**
 *  class hold file store
 */
public class Store implements Serializable {
	public Store(){

	}
	public Store(String key, String value){
		this.key = key;
		this.value = value;
	}

	private final long versionId  = 345345345345345345l;

	private Integer id;


	private String key;

	private  Object value;

	private String date;

	private String operation;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		if(value==null)
			return "";
		return value;
	}

	@Override
	public String toString() {
		return "Store{" +
				"versionId=" + versionId +
				", id=" + id +
				", key='" + key + '\'' +
				", value=" + value +
				", date='" + date + '\'' +
				", operation='" + operation + '\'' +
				'}';
	}
}
