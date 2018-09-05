package com.pdomingo.service;


import java.util.*;
import java.util.stream.Collectors;
import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;
import com.pdomingo.dao.PersonDaoImpl;
import org.hibernate.NonUniqueObjectException;

public class PersonService {

	private static EntityMapper entityMapper = new EntityMapper();
	private static PersonDaoImpl personDaoImpl;

	public PersonService() {
		personDaoImpl = new PersonDaoImpl();
	}

	public PersonService(PersonDaoImpl newDao) {
		personDaoImpl = newDao;
	}

	public String saveOrUpdate(PersonDTO entity) {
		String updateStatus;
		try{
			personDaoImpl.saveOrUpdate(entityMapper.mapToPerson(entity, false));
			updateStatus = "\n\t !!! Save Person Success !!!";
		}catch(Exception e){
			updateStatus = "\n\t !-- Save Person Failed --!";
		}
		return updateStatus;
	}

	public String update(PersonDTO entity) {
		String updateStatus;
		try{
			personDaoImpl.update(entityMapper.mapToPerson(entity, false));
			updateStatus = "\n\t !!! Update Person Successful !!!";
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			updateStatus = "\n\t !-- Update Person Failed --!";
		}
		return updateStatus;
	}

	public Set<RoleDTO> removeRole(Set<RoleDTO> roles, String roleName){
		for (Iterator<RoleDTO> iterator = roles.iterator(); iterator.hasNext();) {
		    RoleDTO role  =  iterator.next();
		    if (role.getRole().equals(roleName)) {
		        iterator.remove();
		    }
		}
		return roles;
	}


	public Set<ContactInfoDTO> removeContact(Set<ContactInfoDTO> contacts, Long id){
		for (Iterator<ContactInfoDTO> iterator = contacts.iterator(); iterator.hasNext();) {
			ContactInfoDTO contact  =  iterator.next();
			if (contact.getContactInfoId().equals(id)) {
				iterator.remove();
			}
		}
		return contacts;
	}

	public Boolean containsRole(Set<RoleDTO> list, String name){
    	return list.stream().filter(o -> o.getRole().equals(name)).findFirst().isPresent();
	}

	public Person findById(Long id) {
		Person person = personDaoImpl.findById(id);

		return person;
	}

	public Person findPerson(PersonDTO personDTO) {
		Person person = personDaoImpl.findPerson(entityMapper.mapToPerson(personDTO, false));

		return person;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;

		Person person = personDaoImpl.findById(Id);

		if(person == null){
			exists = false;
		}

		return exists;
	}

	public String delete(Long id) {

		String textToReturn;

			Person person = personDaoImpl.findById(id);
			person.getName();

			personDaoImpl.delete(person);
			textToReturn = "\n\t!!! Deleted !!!\n";


		return textToReturn;
	}

	public List<PersonDTO> findAllPersonDTO(){
		return entityMapper.mapPersonDTOList(findAll());
	}

	public List<Person> findAll() {
		List<Person> persons = personDaoImpl.findAll();
		return persons;
	}

	public List<PersonDTO> findOrderBy(String field, Integer order) {
		List<PersonDTO> persons;
		String stringOrder = order.equals(1)? "ASC": "DESC";
		if(field.equals("last_name")){
			persons = entityMapper.mapPersonDTOList(personDaoImpl.listLastNameBy(stringOrder));
		}
		else{
			persons = entityMapper.mapPersonDTOList(personDaoImpl.findAllOrderBy(field, stringOrder));
		}

		return persons;
	}

	public List<RoleDTO> findPersonRoles(Long id){
		List<Role> roles = personDaoImpl.findPersonRoles(id);
		List<RoleDTO> rolesDTO = entityMapper.createRoleListDTO(roles);
		return rolesDTO;
	}

	public List<String> findPersonContacts(Long id){
		List<ContactInfo> contacts = personDaoImpl.findPersonContacts(id);
		List<String> contactString = new ArrayList<String>();

		for (ContactInfo contact : contacts) {
			contactString.add(String.format("\t%s: %s %s",contact.getContactInfoId(),
 		   												contact.getContactType(), contact.getContactInfo()));
		}

		return contactString;
	}


}
