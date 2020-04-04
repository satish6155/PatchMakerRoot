package com.patchMaker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PATCH")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patch")
public class Patch extends BaseEntity {

	@Transient
	private static final long serialVersionUID = -1232395859408322328L;

	@Column(nullable = false, length = 255)
	private String patchName;

	@Column
	private String bankJira;
	
	@Column
	private String productJira;
	
	@Column
	private String internalJira;

	public String getPatchName() {
		return patchName;
	}

	public void setPatchName(String patchName) {
		this.patchName = patchName;
	}

	public String getBankJira() {
		return bankJira;
	}

	public void setBankJira(String bankJira) {
		this.bankJira = bankJira;
	}

	public String getProductJira() {
		return productJira;
	}

	public void setProductJira(String productJira) {
		this.productJira = productJira;
	}

	public String getInternalJira() {
		return internalJira;
	}

	public void setInternalJira(String internalJira) {
		this.internalJira = internalJira;
	}

	@Override
	public String toString() {
		return "Patch [patchName=" + patchName + ", bankJira=" + bankJira
				+ ", productJira=" + productJira + ", internalJira="
				+ internalJira + ", getId()=" + getId()
				+ ", getCreationTimeStamp()=" + getCreationTimeStamp() + "]";
	}


//	@Column(columnDefinition = "NUMBER(3)")
//	private Integer bankJira;

	
		
}
