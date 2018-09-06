package com.pdomingo.model.entities;
import java.util.*;
import java.util.Date;
import java.util.Set;

import com.pdomingo.model.dto.*;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="person")
public class Person{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Long personId;

	@Embedded
	private Name name;

	@Embedded
	private Address address;

	private Double GWA;

	@Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
	private Date bDay;

	@Temporal(TemporalType.DATE)
    @Column(name = "date_hired")
	private Date dateHired;

	@Column(name = "curr_employed")
	private Boolean currEmployed;

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="contacts")
    @OneToMany(mappedBy="person", fetch=FetchType.EAGER, orphanRemoval=false)
    @Cascade({CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
	private Set<ContactInfo> contactInfo = new HashSet<ContactInfo>(0);

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="roles")
    @ManyToMany(fetch=FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "person_roles",
        joinColumns = { @JoinColumn(name = "person_id")},
        inverseJoinColumns = { @JoinColumn(name = "role_id")})
	private Set<Role> roles = new HashSet<Role>(0);

	public Person(){

	}

	public Person(Name newName, Address newAddress, Date newBDay, Double newGWA,
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

	public Name getName(){
		return name;
	}

	public Address getAddress(){
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

	public Set<ContactInfo> getContactInfo(){
		return this.contactInfo;
	}

	public Set<Role> getRoles(){
		return this.roles;
	}

	public void setPersonId(Long newID){
		this.personId = newID;
	}

	public void setName(Name newName){
		this.name = newName;
	}

	public void setAddress(Address newAddress){
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

	public void setContactInfo(Set<ContactInfo> newContactInfo){
		this.contactInfo = newContactInfo;
	}

	public void setRoles(Set<Role> newRoles){
		this.roles = newRoles;
	}

	@Override
	public String toString(){
		return(String.format("|%s\t|%s\t|%s\t|%f\t|%s\t|", name, address, bDay, GWA, dateHired));
	}
}
