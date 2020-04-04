package com.patchMaker.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -1232395859408322328L;

	@Id
	@GenericGenerator(name = "sequencePerEntityGenerator", strategy = "com.patchMaker.util.CustomSequenceGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
			@org.hibernate.annotations.Parameter(name = "sequence_per_entity_suffix", value = "_seq"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "50000") })
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequencePerEntityGenerator")
	private Long id;

	
	private Timestamp creationTimeStamp;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		if (creationTimeStamp == null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			creationTimeStamp = timestamp;
		}
		this.creationTimeStamp = creationTimeStamp;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", creationTimeStamp="
				+ creationTimeStamp + "]";
	}



}
