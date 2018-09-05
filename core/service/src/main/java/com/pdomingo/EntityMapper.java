package com.pdomingo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class EntityMapper {

	PersonService personService = new PersonService();
	RoleService roleService = new RoleService();
    ContactInfoService contactInfoService = new ContactInfoService();
    //UserDTO targetUserDTO = new UserDTO();
    ModelMapper modelMapper = new ModelMapper();

    public Address createAddress(AddressDTO addressDTO) {
        Address address = new Address();
        modelMapper.map(addressDTO, address);
		return address;
	}

	public AddressDTO createAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
		modelMapper.map(address, addressDTO);
        return addressDTO;
	}

    private NameDTO createNameDTO(Name name){
        NameDTO nameDTO = new NameDTO();
		modelMapper.map(name, nameDTO);
        return nameDTO;
    }

    private Name createName(NameDTO nameDTO){
        Name name = new Name();
        modelMapper.map(nameDTO, name);
        return name;
    }

    public List<PersonDTO> mapPersonDTOList(List<Person> personList) {
		List<PersonDTO> personsDTO = new ArrayList<>();

		personList.forEach(person -> {
			try {
				personsDTO.add(mapToPersonDTO(person));
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		return personsDTO;
	}

    public PersonDTO mapToPersonDTO(Person person) throws Exception {
		PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonId(person.getPersonId());
		personDTO.setName(createNameDTO(person.getName()));
		personDTO.setbDay(person.getbDay());
		personDTO.setGWA(person.getGWA());
		personDTO.setAddress(createAddressDTO(person.getAddress()));
		personDTO.setCurrEmployed(person.getCurrEmployed());
		personDTO.setDateHired(person.getDateHired());
		personDTO.setRoles(createRoleSetDTO(person.getRoles()));
		personDTO.setContactInfo(createContactSetDTO(person.getContactInfo()));

		return personDTO;
	}

    public Person mapToPerson(PersonDTO personDTO, boolean isNew) {
		Person person = new Person();

		person.setPersonId(personDTO.getPersonId());
		person.setName(createName(personDTO.getName()));
		person.setbDay(personDTO.getbDay());
		person.setGWA(personDTO.getGWA());
		person.setAddress(createAddress(personDTO.getAddress()));
		person.setCurrEmployed(personDTO.getCurrEmployed());
		person.setDateHired(personDTO.getDateHired());
		person.setRoles(createRoleSet(personDTO.getRoles()));
		person.setContactInfo(createContactSet(person, personDTO.getContactInfo()));

		return person;
	}

    public ContactInfo createContact(ContactInfoDTO contactDTO) {
		ContactInfo contact = new ContactInfo(contactDTO.getContactType(), contactDTO.getContactInfo());
		contact.setContactInfoId(contactDTO.getContactInfoId());
		contact.setPerson(mapToPerson(contactDTO.getPerson(), false));
		return contact;
	}

	public ContactInfoDTO createContactDTO(String contactType, String contactValue) {
		return new ContactInfoDTO(contactType, contactValue);
	}

	public Set<ContactInfo> createContactSet(Person person, Set<ContactInfoDTO> contactsDTO) {
		Set<ContactInfo> contacts = person.getContactInfo();

    	if(contacts == null){
            contacts = new HashSet<>();
        }

    	contacts.clear();

		try {
			for(ContactInfoDTO contactDTO : contactsDTO) {
				ContactInfo contact = new ContactInfo(contactDTO.getContactType(), contactDTO.getContactInfo());

                if(contactDTO.getContactInfoId()!= null){
                    contact.setContactInfoId(contactDTO.getContactInfoId());
                }

				contact.setPerson(person);
				contacts.add(contact);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

	public Set<ContactInfoDTO> createContactSetDTO(Set<ContactInfo> contacts) {
		Set<ContactInfoDTO> contactsDTO = new HashSet<>();
		contacts.forEach(contact -> {

            ContactInfoDTO contactDTO = new ContactInfoDTO(contact.getContactType(), contact.getContactInfo());

            contactDTO.setContactInfoId(contact.getContactInfoId());
			contactsDTO.add(contactDTO);
		});
		return contactsDTO;
	}

    private PersonDTO createPersonDTO(Person person) {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setPersonId(person.getPersonId());
		personDTO.setName(createNameDTO(person.getName()));

		return personDTO;
	}

    private Person createPerson(PersonDTO personDTO) {
		Person person = new Person();
		person.setPersonId(personDTO.getPersonId());
		person.setName(createName(personDTO.getName()));
		return person;
	}

    public RoleDTO mapToRoleDTO(Role role){
		RoleDTO roleDTO = new RoleDTO();
		try{
			roleDTO = new RoleDTO(role.getRoleId(), role.getRole());
			Set<PersonDTO> persons = new HashSet<>();
			role.getPersons().forEach(person -> persons.add(createPersonDTO(person)));
			roleDTO.setPersons(persons);

		}catch(Exception e){
		}


		return roleDTO;
	}



    public Role mapToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRole(roleDTO.getRole());

        Set<Person> persons = new HashSet<>();
        roleDTO.getPersons().forEach(person -> persons.add(createPerson(person)));
        role.setPersons(persons);
        return role;
    }

    public Set<Role> createRoleSet(Set<RoleDTO> rolesDTO) {
        Set<Role> roles = new HashSet<>();
        rolesDTO.forEach(role -> roles.add(mapToRole(role)));
        return roles;
    }

    public List<RoleDTO> getAllRoles() {
		List<Role> roles = roleService.findAll();
		List<RoleDTO> rolesDTO = new ArrayList<>();
		roles.forEach(role -> {
			rolesDTO.add(new RoleDTO(role.getRoleId(), role.getRole()));
		});
		return rolesDTO;
	}

    public List<RoleDTO> createRoleListDTO(List<Role> roles) {
        List<RoleDTO> rolesDTO = new ArrayList<>();
        roles.forEach(role -> rolesDTO.add(mapToRoleDTO(role)));
        return rolesDTO;
    }



    public Set<RoleDTO> createRoleSetDTO(Set<Role> roles) {
        Set<RoleDTO> rolesDTO = new HashSet<>();
        roles.forEach(role -> rolesDTO.add(mapToRoleDTO(role)));
        return rolesDTO;
    }



}
