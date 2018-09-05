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


public class PersonIO {

	Scanner scan = new Scanner(System.in);
	RoleIO roleIO = new RoleIO();
	ContactIO contactIO = new ContactIO();
	PersonService personService = new PersonService();
	RoleService roleService = new RoleService();
	ContactInfoService contactInfoService = new ContactInfoService();
	NameDTO name;
	AddressDTO address;
	int index;
	List<PersonDTO> persons;

	public PersonIO(){

	}

	public PersonIO(Scanner newScanner){
		scan = newScanner;
	}

	public void start(){
		Integer choice;
		do{
			choice = chooseOperation();
			doChooseOperation(choice);
		}while(choice != 7);
	}

	public Integer chooseOperation(){

		System.out.println("\n \t--- Choose Operation --- \n");
		System.out.println("\n \t1. Create Person");
		System.out.println("\n \t2. Read Person");
		System.out.println("\n \t3. Update Person");
		System.out.println("\n \t4. Delete Person");
		System.out.println("\n \t5. List Persons");
		System.out.println("\n \t6. Manage Person (Role/Contact Info)");
		System.out.println("\n \t7. Back");

		return InputValidation.getIntegerInRange(1,7);

	}

	public void doChooseOperation(Integer choice){

		switch(choice){
			case 1:
				createPerson();
				break;
			case 2:
				readPerson();
				break;
			case 3:
				updatePerson();
				break;
			case 4:
				deletePerson();
				break;
			case 5:
				askListBy();
				break;
			case 6:
				managePerson();
				break;
			case 7:
				break;
		}

	}

	public NameDTO askName(){
		name = new NameDTO();

		System.out.println("\n \t--- Input Title ---\n");
		name.setTitle(InputValidation.getInput());

		System.out.println("\n \t--- Input First Name ---\n");
		name.setFirstName(InputValidation.getRequiredInput());

		System.out.println("\n \t--- Input Middle Name ---\n");
		name.setMiddleName(InputValidation.getInput());

		System.out.println("\n \t--- Input Last Name ---\n");
		name.setLastName(InputValidation.getRequiredInput());

		System.out.println("\n \t--- Input Suffix ---\n");
		name.setSuffix(InputValidation.getInput());

		return name;
	}

	public AddressDTO askAddress(){
		address = new AddressDTO();

		System.out.println("\n \t--- Input Street # ---\n");
		address.setStreetNo(InputValidation.getRequiredInput());

		System.out.println("\n \t--- Input Barangay ---\n");
		address.setBarangay(InputValidation.getRequiredInput());

		System.out.println("\n \t--- Input Municipality ---\n");
		address.setMunicipality(InputValidation.getRequiredInput());

		System.out.println("\n \t--- Input Zip Code ---\n");
		address.setZipCode(InputValidation.getRequiredInput());

		return address;
	}

	public Date askDate(String dateType){
		System.out.println(String.format("\n \t--- Input %s in (YYYY-MM-DD) ---\n", dateType));
		return InputValidation.getDate();
	}

	public void createPerson(){

			if(roleService.findAllRoleDTO().size() == 0){
				System.out.println("\n\t !-- No Roles found in Database --! \n");
			}else{

				name = askName();
				address = askAddress();
				Date birthDay = askDate("Birth Day");
				Date hireDate = askDate("Date Hired");
				Boolean employmentStatus = InputValidation.getYesOrNo("\n\t --- Is currently employed? Y/N ---\n");
				Double GWA = InputValidation.getGWA();

				ContactInfoDTO contact = contactIO.addContact();
				System.out.println("\n\t --- Input index of role to add ---\n");
				RoleDTO role = roleIO.listAndGetInput();
				System.out.println(role);
				PersonDTO person = new PersonDTO(name, address, birthDay, GWA, hireDate, employmentStatus);

				Set<ContactInfoDTO> contacts = new HashSet<ContactInfoDTO>();
				contact.setPerson(person);
				contacts.add(contact);

				Set<RoleDTO> roles = new HashSet<RoleDTO>();
				roles.add(role);

				person.setContactInfo(contacts);
				person.setRoles(roles);

				System.out.println(personService.saveOrUpdate(person));
			}

	}

	public String listRole(Long id){
		String toReturn = "";
		for(RoleDTO role : personService.findPersonRoles(id)) {
		   toReturn = "\n\t\t" + toReturn +" "+ role;
		}
		return toReturn;
	}

	public String listContacts(Long id){
		String toReturn = "";
		for(String contactInfo : personService.findPersonContacts(id)) {

		   toReturn = toReturn + "\n\t" + contactInfo;
		}
		return toReturn;
	}

	public void readPerson(){

		if(personService.findAllPersonDTO().size() <1){
			System.out.println("\n\t !-- No Persons Found --! \n");
		}
		else{
			try{
				System.out.println("\n\t--- Input ID of Person to read ---\n");


				PersonDTO person = listAndGetInput();
				person.getName();

				StringBuilder personDetail = new StringBuilder();
				personDetail.append(String.format("\n\t--- Name : %s ", person.getName()));
				personDetail.append(String.format("\n\t--- Born in : %s", person.getbDay()));
				personDetail.append(String.format("\n\t--- Lives in : %s", person.getAddress()));
				personDetail.append(String.format("\n\t--- gwa : %f", person.getGWA()));

				if(person.getCurrEmployed()){
					personDetail.append(String.format("\n\t--- Hired in : %s", person.getDateHired()));
				}

				personDetail.append(String.format("\n\t--- Contact Information : %s", listContacts(person.getPersonId())));
				personDetail.append(String.format("\n\t--- Roles : %s", listRole(person.getPersonId())));

				String finalString = personDetail.toString();
				System.out.println(finalString);

			}catch(NullPointerException ne){
				System.out.println("\n\t--- Person Not Found ---\n");
			}catch(Exception e){
				System.out.println("\n\t--- Read Failed ---\n");
			}

		}





	}

	private Integer askUpdateChoice(){

		System.out.println("\n\t--- Choose what to update ---\n");
		System.out.println("\n\t1. Name \n");
		System.out.println("\n\t2. Address \n");
		System.out.println("\n\t3. GWA \n");
		System.out.println("\n\t4. BirthDay \n");
		System.out.println("\n\t5. Set Hire Date\n");
		System.out.println("\n\t6. Set Employment Status\n");
		System.out.println("\n\t7. Back \n");

		Integer choice = InputValidation.getIntegerInRange(1,7);
		return choice;
	}

	private PersonDTO doUpdateChoice(Integer choice, PersonDTO person){
		String contactInfo = "";

		switch (choice.intValue()){

			case 1:
					person.setName(askName());
					break;
			case 2:
					person.setAddress(askAddress());
					break;
			case 3:
					person.setGWA(InputValidation.getGWA());
					break;
			case 4:
					person.setbDay(askDate("Birth Day"));
					break;
			case 5:
					person.setDateHired(askDate("Date Hired"));
					break;
			case 6:
					person.setCurrEmployed(InputValidation.getYesOrNo("\n\t--- Is currently employed? Y/N ---\n"));
					break;
			default:
					break;
		}

		return person;
	}

	public PersonDTO listAndGetInput(){
		Long input;
		PersonDTO person = null;
		listPersonsBy();
		try{
			List<PersonDTO> persons = personService.findAllPersonDTO();
			persons.get(0);
			input = Long.valueOf(InputValidation.getIntegerInRange(1, persons.size()));
			person = persons.get(input.intValue()-1);
		}catch(Exception e){

		}
		return person;
	}

	public void updatePerson(){

		if(personService.findAllPersonDTO().size()<1){
			System.out.println("\n\t !-- No Persons Found --! \n");
		}else{
			System.out.println("\n\t---Input ID of Person to update ---\n");
			try{

				PersonDTO person = listAndGetInput();

				person.getName();
				Integer choice;

				do{
					choice = askUpdateChoice();
					person = doUpdateChoice(choice,person);
				}while(choice.intValue() != 7);

				System.out.println(personService.update(person));

			}catch(NullPointerException ne){
				System.out.println("\n\t--- Person Not Found ---\n");
			}catch(Exception e){
				System.out.println("\n\t--- Update Failed ---\n");
			}
		}
	}



	public void deletePerson(){
		if(personService.findAllPersonDTO().size()<1){
			System.out.println("\n\t !-- No Persons Found --! \n");
		}
		else{
			try{
				System.out.println("\n\t Input ID of person to delete\n");
				PersonDTO person = listAndGetInput();

				person.getName();
				System.out.println(personService.delete(person.getPersonId()));

			}catch(NullPointerException ne){
				System.out.println("\n\t--- Person Not Found ---\n");
			}

		}

	}

	public void askListBy(){
		if(personService.findAllPersonDTO().size()<1){
			System.out.println("\n\t !-- No Persons Found --! \n");
		}else{

				System.out.println("\n\t --- List by? ---\n");
				System.out.println("\n\t 1.GWA \n");
				System.out.println("\n\t 2.Date Hired \n");
				System.out.println("\n\t 3.Last Name \n");
				System.out.println("\n\t 4.No Order \n");

				doListPerson(InputValidation.getIntegerInRange(1,4));

		}

	}

	public Integer askOrder(){
		System.out.println("\n\t --- Order by? ---\n");
		System.out.println("\n\t 1.Ascending \n");
		System.out.println("\n\t 2.Descending \n");

		return InputValidation.getIntegerInRange(1,2);
	}

	public void doListPerson(Integer choice){
		Integer order;

		switch(choice){
			case 1:

				listPersonsByGWA(askOrder());
				break;
			case 2:
				listPersonsBy("date_hired", askOrder());
				break;
			case 3:
				listPersonsBy("last_name", askOrder());
				break;
			case 4:
				listPersonsBy();
				break;
		}
	}


	public void listPersonsBy(){
		index = 1;
		for (PersonDTO p : personService.findAllPersonDTO()) {
			name = p.getName();
			System.out.println(String.format("ID:%s \t%s \t%s ", index++, name.getFirstName(), name.getLastName()));
		}
	}

	public void listPersonsByGWA(Integer order){

		List<PersonDTO> persons = personService.findAllPersonDTO();
		Collections.sort(persons, new Comparator<PersonDTO>(){
		    public int compare(PersonDTO p1, PersonDTO p2) {
		        return p1.getGWA().compareTo(p2.getGWA());
		    }
		});

		if(order.equals(1) == false){
			Collections.reverse(persons);
		}
		index = 1;
		for (PersonDTO p : persons) {
			name = p.getName();
			System.out.println(String.format("ID:%s \t%s \t%s \t%s", index++, name.getFirstName(), name.getLastName(), p.getGWA()));
		}

	}

	public void listPersonsBy(String property, Integer order){
		index = 1;
		if(property.equals("last_name")){
			for (PersonDTO p : personService.findOrderBy(property, order)) {
				name = p.getName();
				System.out.println(String.format("ID:%s \t%s \t%s", index++, name.getFirstName(), name.getLastName().toUpperCase()));
			}
		}else{
			for (PersonDTO p : personService.findOrderBy(property, order)) {
				name = p.getName();
				System.out.println(String.format("ID:%s \t%s \t%s \t%s", index++, name.getFirstName(), name.getLastName(), p.getDateHired()));
			}
		}


	}

	private Integer chooseManage(){
		System.out.println("\n\t---Choose what to manage---\n");
		System.out.println("\n\t1.Role\n");
		System.out.println("\n\t2.Contact Info\n");

		return InputValidation.getIntegerInRange(1,2);
	}

	private void manageRole(PersonDTO person){

		System.out.println(listRole(person.getPersonId()));
		System.out.println("\n\t--- Choose Operation ---\n");
		System.out.println("\n\t1.Add Role\n");
		System.out.println("\n\t2.Delete Role\n");
		System.out.println("\n\t3.Back\n");


		Set<RoleDTO> roles;
		String roleToUpdate;


		switch(InputValidation.getIntegerInRange(1,4)){

			case 1:
				roles = person.getRoles();
				RoleDTO newRole = roleIO.listAndGetInput();
				if(personService.containsRole(roles, newRole.getRole())){
					System.out.println("\n\t !!! Role already in person!!! \n");
				}else{
					roles.add(newRole);
					person.setRoles(roles);
					System.out.println(personService.update(person));
					System.out.println(String.format("\n\t Role %s added to Person!", newRole.getRole()));
				}

				break;

			case 2:
				roles = person.getRoles();

				for(RoleDTO role : roles){
					System.out.println(role);
				}

				System.out.println("--- Input NAME of Role to Delete ---");
				roleToUpdate = InputValidation.getRequiredInput().toUpperCase();

				if(personService.containsRole(roles, roleToUpdate)){
					roles = personService.removeRole(roles, roleToUpdate);
					System.out.println(String.format("\n\t Role %s removed from Person!",roleToUpdate));
				}else{
					System.out.println("\n\t !!! Role does not exist!!! \n");
				}
				break;
			case 3:
			break;
		}
		personService.update(person);

	}

	private void manageContacts(PersonDTO person){


		System.out.println(listContacts(person.getPersonId()));
		System.out.println("\n\t--- Choose Operation ---\n");
		System.out.println("\n\t1.Add Contact Info\n");
		System.out.println("\n\t2.Delete Contact Info\n");
		System.out.println("\n\t3.Update Contact Info\n");
		System.out.println("\n\t4.Back\n");
		Set<ContactInfoDTO> contacts;
		ContactInfoDTO contact;
		Long id;


		Integer choice = InputValidation.getIntegerInRange(1,4);

		switch(choice){

			case 1:
				try{
					contacts = person.getContactInfo();
					contact = contactIO.addContact();

					contact.setPerson(person);
					contacts.add(contact);
					person.setContactInfo(contacts);

					System.out.println("\n\t !-- Contact Info Added! --!");
				}catch(Exception e){
					System.out.println("\n\t !!! Failed to add Contact info !!!");
				}
				break;
			case 2:
				try{

					contacts = person.getContactInfo();

					System.out.println("\n\t--- Input Id of Contact Info to Delete ---");
					id = Long.valueOf(InputValidation.getInteger());

					contacts =  personService.removeContact(contacts, Long.valueOf(id));
					person.setContactInfo(contacts);
					contactInfoService.delete(id);

					System.out.println("\n\t !-- Contact Info Deleted! --!");
				}catch(Exception e){
					System.out.println("\n\t !!! Failed to Delete Contact info !!!");
				}
				break;

			case 3:
				contactIO.updateContact();
				break;

			case 4:
			break;
		}

			personService.update(person);


	}

	public void managePerson(){
		if(personService.findAllPersonDTO().size()<1){
			System.out.println("\n\t !-- No Persons Found --! \n");

		}else{
			System.out.println("\n\t--- Input ID of Person to Manage ---\n");
			try{
				PersonDTO person = listAndGetInput();
				person.getName();
				if(chooseManage().equals(1)){
					manageRole(person);

				}else{
					manageContacts(person);
				}

			}catch(NullPointerException ne){
				System.out.println("\n\t--- No Person Found ---\n");
			}
		}

	}



}
