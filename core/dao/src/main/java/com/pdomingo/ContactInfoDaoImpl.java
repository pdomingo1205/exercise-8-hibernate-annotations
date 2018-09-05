package com.pdomingo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;



import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;

public class ContactInfoDaoImpl implements DaoInterface<ContactInfo, Long> {



	public void saveOrUpdate(ContactInfo entity) {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().saveOrUpdate(entity);
		DaoConfig.closeCurrentSessionwithTransaction();
	}

	public void update(ContactInfo entity) {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().update(entity);
		DaoConfig.closeCurrentSessionwithTransaction();
	}

	public ContactInfo findById(Long id) {
		DaoConfig.openCurrentSessionwithTransaction();
		ContactInfo contactInfo = (ContactInfo) DaoConfig.getCurrentSession().get(ContactInfo.class, id);
		DaoConfig.closeCurrentSessionwithTransaction();
		return contactInfo;
	}

	public ContactInfo findContact(ContactInfo contact) {
		DaoConfig.openCurrentSessionwithTransaction();
		List<ContactInfo> list = DaoConfig.getCurrentSession().createCriteria(ContactInfo.class).setCacheable(true).list();
		DaoConfig.closeCurrentSessionwithTransaction();
		return list.get(list.indexOf(contact));
	}

	public void delete(ContactInfo entity) {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().delete(entity);
		DaoConfig.getCurrentSession().flush();
		DaoConfig.closeCurrentSessionwithTransaction();
	}

	@SuppressWarnings("unchecked")
	public List<ContactInfo> findAll() {
		DaoConfig.openCurrentSessionwithTransaction();
		List<ContactInfo> contactInfos = DaoConfig.getCurrentSession().createCriteria(ContactInfo.class).setCacheable(true).list();
		DaoConfig.closeCurrentSessionwithTransaction();
		return contactInfos;
	}

}
