package com.rest.service.cachingservice;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 *  this class used to log the store details
 */
@Entity
public class LogEntiry implements Serializable {


		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;

		@Column
		private String key;

		@Column
		private  String value;

		@Column
		private String date;

		@Column
		private String operation;

		public void setOperation(String operation) {
			this.operation = operation;
		}

		public String getOperation() {
			return operation;
		}

	public void setId(Integer id) {
			this.id = id;
		}

		public Integer getId() {
			return id;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getDate() {
			Instant instant = Instant.now();
			return instant.toString();
		}

		public void setKey(String key) {
			this.key = key;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public Object getValue() {
			return value;
		}
	}


