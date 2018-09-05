package com.pdomingo.app;

import java.io.IOException;
import java.util.*;
import java.text.*;

import org.apache.commons.lang3.StringUtils;
import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;
import com.pdomingo.service.*;
import com.pdomingo.util.*;

import com.pdomingo.util.InputValidation;



public class RoleIO {
	RoleService roleService = new RoleService();
	EntityMapper entityMapper = new EntityMapper();

	public RoleIO(){

	}

	public static void clearScreen() {
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	}

	public void start(){
		Integer choice;
		do{
			choice = chooseOperation();
			doChooseOperation(choice);
		}while(choice != 4);
	}

	public Integer chooseOperation(){
		listRoles();
		
		System.out.println("\n\t--- Choose Operation --- \n");
		System.out.println("\n\t 1. Create Role");
		System.out.println("\n\t 2. Update Role");
		System.out.println("\n\t 3. Delete Role");
		System.out.println("\n\t 4. Back");

		return InputValidation.getIntegerInRange(1,4);
	}

	public void doChooseOperation(Integer choice){

		switch(choice){
			case 1:
				createRole();
				break;
			case 2:
				updateRole();
				break;
			case 3:
				deleteRole();
				break;
			case 4:
				break;
		}

	}


	public RoleDTO addRole(){
		RoleDTO role = new RoleDTO();
		String roleName = askRole();


		RoleDTO existingRole = roleService.checkIfUnique(roleName);
		role = existingRole;
        return role;
	}


	public RoleDTO listAndGetInput(){
		Long input;
		RoleDTO role = null;
		try{
			List<RoleDTO> roles = entityMapper.getAllRoles();
			System.out.println("\n\t --- Input index of Role to add ---");
			listRoles();
			roles.get(0);
			input = Long.valueOf(InputValidation.getIntegerInRange(1, roles.size()));
			role = roles.get(input.intValue()-1);
		}catch(Exception e){

		}
		return role;
	}

	public void createRole(){
		RoleDTO role = new RoleDTO();
		String roleName = askRole().toUpperCase();

		role = roleService.checkIfUnique(roleName);
		if(role.getRoleId() == null){
			System.out.println(roleService.saveOrUpdate(role));
		}
		else{
			System.out.println("\n \t--- Role already exists ---\n");
		}
	}

	private String askRole(){

		System.out.println("\n \t--- Input name of Role ---\n");

		String inputRole = InputValidation.getRequiredInput();

		return inputRole;
	}


	public void updateRole(){
		try{
			System.out.println("\n\t--- Input ID of role to update---\n");
			RoleDTO role = listAndGetInput();

				System.out.println("\n\t--- Input new Role ---\n");
				String newRole = InputValidation.getRequiredInput().toUpperCase();

					if(roleService.checkIfNameExists(newRole)){
						System.out.println("Role Already Exists");
					}else{
						role.setRole(newRole);
						System.out.println(roleService.update(role));

					}

		}catch(Exception e ){
			System.out.println("\n\t!-- Update Failed --!\n");
		}


		}


	public void deleteRole(){
		try{
			System.out.println("\n\t--- Input ID of role to delete *Use List to find ID's ---\n");
			RoleDTO role = listAndGetInput();


			String message = roleService.delete(role.getRoleId());
			System.out.println(message);

			if(message.equals("\n\t!-- Another Person is still assigned to this Role --!\n")){
				System.out.println(getRoleOwners(roleService.findById(role.getRoleId())));
			}

		}catch(Exception e ){
			System.out.println("\n\t!-- Update Failed --!\n");
		}


	}

	int index;

	public void listRoles(){
		index = 1;
		for (RoleDTO r : entityMapper.getAllRoles()) {
			System.out.println((index++) +": "+r.getRole());
		}
	}

	private String getRoleOwners(RoleDTO role) {
		StringBuilder sb = new StringBuilder();
		roleService.findById(role.getRoleId()).getPersons().stream()
						   .sorted((p1,p2) -> Long.compare(p1.getPersonId(), p2.getPersonId()))
		                   .forEach(person -> sb.append(String.format("%s: %s \n", person.getPersonId(), person.getName())));
		return sb.toString();
	}




}
