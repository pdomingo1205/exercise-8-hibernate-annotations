package com.pdomingo.model.dto;
import java.util.*;

public class ContactInfoDTO implements java.io.Serializable{

	private Long contactInfoId;
	private String contactInfo;
	private String contactType;
	private PersonDTO person;


	public ContactInfoDTO(){
	}

	public ContactInfoDTO(String newContactInfo, PersonDTO newPerson){
		this.contactInfo = newContactInfo;
		this.person = newPerson;
	}

	public ContactInfoDTO(String newContactInfo, String newContactType){
		this.contactInfo = newContactInfo;
		this.contactType = newContactType;
	}


	public Long getContactInfoId(){
		return contactInfoId;
	}

	public String getContactInfo(){
		return contactInfo;
	}

	public String getContactType(){
		return contactType;
	}

	public PersonDTO getPerson(){
		return person;
	}

	public void setContactInfoId(Long newContactInfoId){
		this.contactInfoId = newContactInfoId;
	}

	public void setContactInfo(String newContactInfo){
		this.contactInfo = newContactInfo;
	}

	public void setContactType(String newContactType){
		this.contactType = newContactType;
	}

	public void setPerson(PersonDTO newPerson){
		this.person = newPerson;
	}

	@Override
	public String toString(){
		return String.format("%s: %s", contactType, contactInfo);
	}

}
