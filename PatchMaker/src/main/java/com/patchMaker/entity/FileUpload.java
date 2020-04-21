package com.patchMaker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "FILE_UPLOAD")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FILE_UPLOAD")
public class FileUpload extends BaseEntity {

	@Transient
	private static final long serialVersionUID = -1232395859408322328L;


	@Column
	private String fileType;

	@Column
	private String path;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "FileUpload [fileType=" + fileType + ", path=" + path + "]";
	}

	

}
