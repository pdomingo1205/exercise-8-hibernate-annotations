package com.pdomingo.model.dto;

import java.util.*;

import com.pdomingo.model.entities.*;

public class RoleDTO{

	private Long roleId;
	private String role;
	private Set<PersonDTO> persons = new HashSet<>();

	public RoleDTO(){

	}

	public RoleDTO(String newRole){
		this.role = newRole;
	}

	public RoleDTO(Long newRoleId, String newRole){
		this.roleId = newRoleId;
		this.role = newRole;
	}

	public Long getRoleId(){
		return roleId;
	}

	public String getRole(){
		return role;
	}

	public Set<PersonDTO> getPersons(){
		return persons;
	}

	public void setPersons(Set<PersonDTO> newPersons){
		this.persons = newPersons;
	}

	public void setRoleId(Long newRoleId){
		this.roleId = newRoleId;
	}

	public void setRole(String newRole){
		this.role = newRole;
	}

	@Override
	public String toString(){
		return roleId + ": " + role;
	}



}
