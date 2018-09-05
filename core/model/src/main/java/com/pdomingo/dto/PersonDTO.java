package com.pdomingo.model.dto;
import java.util.*;
import com.pdomingo.model.entities.*;

public class PersonDTO{

	 private Long personId;
	 private NameDTO name;
	 private AddressDTO address;
	 private Double GWA;
	 private Date bDay;
	 private Date dateHired;
	 private Boolean currEmployed;
	 private Set<ContactInfoDTO> contactInfo = new HashSet<ContactInfoDTO>(0);
	 private Set<RoleDTO> roles = new HashSet<RoleDTO>(0);

	public PersonDTO(){

	}

	public PersonDTO(NameDTO newName, AddressDTO newAddress, Date newBDay, Double newGWA,
					Date newDateHired,
					Boolean newCurrEmployed){

		this.name = newName;
		this.address = newAddress;
		this.bDay = newBDay;
		this.GWA = newGWA;
		this.dateHired = newDateHired;
		this.currEmployed = newCurrEmployed;

	}

	public Long getPersonId(){
		return personId;
	}

	public NameDTO getName(){
		return name;
	}

	public AddressDTO getAddress(){
		return address;
	}

	public Date getbDay(){
		return bDay;
	}

	public Double getGWA(){
		return GWA;
	}

	public Date getDateHired() {
		return dateHired;
	}

	public Boolean getCurrEmployed(){
		return currEmployed;
	}

	public Set<ContactInfoDTO> getContactInfo(){
		return this.contactInfo;
	}

	public Set<RoleDTO> getRoles(){
		return this.roles;
	}

	public void setPersonId(Long newID){
		this.personId = newID;
	}

	public void setName(NameDTO newName){
		this.name = newName;
	}

	public void setAddress(AddressDTO newAddress){
		this.address = newAddress;
	}

	public void setbDay(Date newBDay){
		this.bDay = newBDay;
	}

	public void setGWA(Double newGWA){
		this.GWA = newGWA;
	}

	public void setDateHired(Date newDateHired){
		this.dateHired = newDateHired;
	}


	public void setCurrEmployed(Boolean newEmploymentStatus){
		this.currEmployed = newEmploymentStatus;
	}

	public void setContactInfo(Set<ContactInfoDTO> newContactInfo){
		this.contactInfo = newContactInfo;
	}

	public void setRoles(Set<RoleDTO> newRoles){
		this.roles = newRoles;
	}

	@Override
	public String toString(){
		return(String.format("|%s\t|%s\t|%s\t|%f\t|%s\t|", name, address, bDay, GWA, dateHired));
	}
}
