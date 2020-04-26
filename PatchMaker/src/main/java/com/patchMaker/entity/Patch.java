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

	@Column
	private String patchName;

	@Column
	private String bankJira;

	@Column
	private String productJira;

	@Column
	private String internalJira;

	@Column
	private String patchDate;

	@Column
	private String defectsFixed;

	@Column
	private String modules;

	@Column
	private String features;

	@Column
	private String impact;

	@Column
	private String installSteps;

	@Column
	private String knownBugs;

	@Column
	private String newFunctionality;

	@Column
	private String environments;

	@Column
	private String rollbackSteps;

	@Column
	private String svnRevisions;

	@Column
	private String testingDetails;

	@Column
	private String createdBy;

	@Column
	private String project;

	@Column
	private String type;

	@Column(length = 3000)
	private String filesJson;

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

	public String getPatchDate() {
		return patchDate;
	}

	public void setPatchDate(String patchDate) {
		this.patchDate = patchDate;
	}

	public String getDefectsFixed() {
		return defectsFixed;
	}

	public void setDefectsFixed(String defectsFixed) {
		this.defectsFixed = defectsFixed;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getInstallSteps() {
		return installSteps;
	}

	public void setInstallSteps(String installSteps) {
		this.installSteps = installSteps;
	}

	public String getKnownBugs() {
		return knownBugs;
	}

	public void setKnownBugs(String knownBugs) {
		this.knownBugs = knownBugs;
	}

	public String getNewFunctionality() {
		return newFunctionality;
	}

	public void setNewFunctionality(String newFunctionality) {
		this.newFunctionality = newFunctionality;
	}

	public String getRollbackSteps() {
		return rollbackSteps;
	}

	public void setRollbackSteps(String rollbackSteps) {
		this.rollbackSteps = rollbackSteps;
	}

	public String getSvnRevisions() {
		return svnRevisions;
	}

	public void setSvnRevisions(String svnRevisions) {
		this.svnRevisions = svnRevisions;
	}

	public String getTestingDetails() {
		return testingDetails;
	}

	public void setTestingDetails(String testingDetails) {
		this.testingDetails = testingDetails;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getEnvironments() {
		return environments;
	}

	public void setEnvironments(String environments) {
		this.environments = environments;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilesJson() {
		return filesJson;
	}

	public void setFilesJson(String filesJson) {
		this.filesJson = filesJson;
	}

	@Override
	public String toString() {
		return "Patch [patchName=" + patchName + ", bankJira=" + bankJira + ", productJira=" + productJira
				+ ", internalJira=" + internalJira + ", patchDate=" + patchDate + ", defectsFixed=" + defectsFixed
				+ ", modules=" + modules + ", features=" + features + ", impact=" + impact + ", installSteps="
				+ installSteps + ", knownBugs=" + knownBugs + ", newFunctionality=" + newFunctionality
				+ ", environments=" + environments + ", rollbackSteps=" + rollbackSteps + ", svnRevisions="
				+ svnRevisions + ", testingDetails=" + testingDetails + ", createdBy=" + createdBy + ", project="
				+ project + ", type=" + type + ", filesJson=" + filesJson + ", getId()=" + getId()
				+ ", getCreationTimeStamp()=" + getCreationTimeStamp() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
