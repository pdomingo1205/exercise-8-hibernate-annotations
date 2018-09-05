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



public class ContactIO {

	ContactInfoService contactInfoService = new ContactInfoService();
	public ContactIO(){

	}

	private Integer askContactType(){

		System.out.println("\n \t--- Choose Contact Type --- \n");
        System.out.println("\n \t1. Phone");
        System.out.println("\n \t2. Landline");
        System.out.println("\n \t3. Email Address");

		Integer choice = InputValidation.getIntegerInRange(1,3);
		return choice;
	}

    private String contactTypeToString(Integer intContactType){
        String contactType = "";

        switch (intContactType.intValue()){
            case 1: contactType = "Phone";
            break;
            case 2: contactType = "Landline";
            break;
            case 3: contactType = "Email Address";
            break;
            default:
            break;
        }
        return contactType;
    }

    private String askContactInfo(Integer intContactType){
        String contactInfo = "";
        switch (intContactType.intValue()){

            case 1:
					System.out.println("Input phone number ex.09176365546");
                    contactInfo = InputValidation.getMatchedNumber("^[09][0-9]{10}$");
                    break;
            case 2:
					System.out.println("Input landline number ex.3514848");
                    contactInfo = InputValidation.getMatchedNumber("^[0-9]{7}$");
                    break;
            case 3:
					System.out.println("Input email address ex.catlassmcarthur@yahoo.com");
                    contactInfo = InputValidation.getEmailAddress();
                    break;
            default:
                    break;
        }

        return contactInfo;
    }

	public ContactInfoDTO addContact(){
		ContactInfoDTO contact = new ContactInfoDTO();
        Integer intContactType = askContactType();

        contact.setContactType(contactTypeToString(intContactType));
        contact.setContactInfo(askContactInfo(intContactType));
        return contact;
	}


	public ContactInfoDTO editContact(ContactInfoDTO contact){
        Integer intContactType = askContactType();

        contact.setContactType(contactTypeToString(intContactType));
        contact.setContactInfo(askContactInfo(intContactType));
        return contact;
	}

	public void updateContact(){

		System.out.println("\n\t Input ID of Contact Info to update *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.getInteger());
		ContactInfoDTO contactInfo;

		if(contactInfoService.checkIfExists(inputId)){
			contactInfo = contactInfoService.findById(inputId);
			System.out.println("\n\n\n"+contactInfo.getContactInfoId());
			ContactInfoDTO contact = editContact(contactInfo);
			contact.setContactInfoId(inputId);
			try{
				System.out.println(contact);

			}catch(Exception e){

			}
			System.out.println(contactInfoService.update(contact));

		}
		else{
			System.out.println("\n\t Contact Info does not exist \n");
		}
	}

	public Set<ContactInfoDTO> removeContact(PersonDTO person){
		List<ContactInfoDTO> listContact = new ArrayList<ContactInfoDTO>(person.getContactInfo());
		int index = 1;

		for(ContactInfoDTO contact : listContact){
			System.out.println(String.format("\n\tID:%s %s:%s",index++ ,contact.getContactType(), contact.getContactInfo()));
		}

		System.out.println("\n\t Input ID of Contact Info to delete\n");
		Integer contactToDelete = InputValidation.getIntegerInRange(1, listContact.size());
		contactInfoService.delete(listContact.get(contactToDelete).getContactInfoId());
		listContact.remove(contactToDelete);
		return new HashSet<>(listContact);
	}


	public void deleteContact(){
		System.out.println("\n\t Input ID of Contact Info to delete *Use List to find ID's \n");
		Long inputId = Long.valueOf(InputValidation.getInteger());
		ContactInfoDTO contactInfo;

		if(contactInfoService.checkIfExists(inputId)){

			System.out.println(contactInfoService.delete(inputId));

		}
		else{
			System.out.println("\n\t Contact Info does not exist \n");
		}
	}


}
