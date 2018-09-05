package com.pdomingo.service;

import java.util.List;

import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;
import com.pdomingo.dao.ContactInfoDaoImpl;

public class ContactInfoService {
	private static EntityMapper entityMapper = new EntityMapper();
	private static ContactInfoDaoImpl contactInfoDao;

	public ContactInfoService() {
		contactInfoDao = new ContactInfoDaoImpl();
	}

	public ContactInfoService(ContactInfoDaoImpl newDao) {
		contactInfoDao = newDao;
	}

	public String saveOrUpdate(ContactInfo entity) {

		String textToReturn;
		try{

			contactInfoDao.saveOrUpdate(entity);

				textToReturn = "\n\t!!! Insert Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Insert Failed --!\n";
		}
		return textToReturn;
	}

	public String update(ContactInfoDTO entity) {
		String textToReturn;
		try{

				ContactInfo persistentInfo = contactInfoDao.findById(entity.getContactInfoId());
				persistentInfo.setContactInfo(entity.getContactInfo());
				persistentInfo.setContactType(entity.getContactType());
				contactInfoDao.update(persistentInfo);

				textToReturn = "\n\t!!! Update Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Update Failed --!\n";
		}
		return textToReturn;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;


		ContactInfo contact = contactInfoDao.findById(Id);


		if(contact == null){
			exists = false;
		}

		return exists;
	}

	public ContactInfoDTO findById(Long id) {
		ContactInfo contactInfo = contactInfoDao.findById(id);

		return entityMapper.createContactDTO(contactInfo.getContactType(), contactInfo.getContactInfo());
	}

	public String delete(Long id) {

		String textToReturn;
		try{
				contactInfoDao.delete(contactInfoDao.findById(id));
				textToReturn = "\n\t!!! Delete Sucessful !!!\n";
		}catch(Exception e){
			textToReturn = "\n\t!-- Delete Failed --!\n";
		}
		return textToReturn;
	}

	public List<ContactInfo> findAll() {

		List<ContactInfo> contactInfos = contactInfoDao.findAll();

		return contactInfos;
	}

}
