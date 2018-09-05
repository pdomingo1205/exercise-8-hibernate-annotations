package com.pdomingo.dao;

import java.util.List;

import java.util.Set;

import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.Criteria;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;



public class RoleDaoImpl implements DaoInterface<Role, Long> {

	public RoleDaoImpl(){

	}

	public void saveOrUpdate(Role entity) {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().saveOrUpdate(entity);
		DaoConfig.closeCurrentSessionwithTransaction();
	}

	public void update(Role entity) {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().update(entity);
		DaoConfig.closeCurrentSessionwithTransaction();
	}

	public Role findById(Long id) {
		DaoConfig.openCurrentSessionwithTransaction();
		Role role = (Role) DaoConfig.getCurrentSession().get(Role.class, id);
		role.getPersons().size();
		DaoConfig.closeCurrentSessionwithTransaction();
		return role;
	}

	public Role findRole(Role role) {
		DaoConfig.openCurrentSessionwithTransaction();

		Criteria crit = DaoConfig.getCurrentSession().createCriteria(Role.class).setCacheable(true);
		crit.setCacheable(true);
		List<Role> list = crit.list();
		DaoConfig.closeCurrentSessionwithTransaction();
		return list.get(list.indexOf(role));
	}

	public void delete(Role entity) {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().delete(entity);
		DaoConfig.closeCurrentSessionwithTransaction();
	}

	public Role findByRoleName(String roleName){
		Role role = new Role();
		DaoConfig.openCurrentSessionwithTransaction();
		try{
			Query query= DaoConfig.getCurrentSession().createQuery("from Role where role=:name").setCacheable(true);
			query.setParameter("name", roleName);
			role = (Role) query.uniqueResult();
		}catch(Exception e){

		}
		DaoConfig.closeCurrentSessionwithTransaction();
		return role;
	}


	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().flush();
		List<Role> roles = (List<Role>) DaoConfig.getCurrentSession().createQuery("from Role").setCacheable(true).list();
		DaoConfig.closeCurrentSessionwithTransaction();
		return roles;
	}

	public void deleteAll() {
		DaoConfig.openCurrentSessionwithTransaction();
		List<Role> entityList = findAll();
		for (Role entity : entityList) {
			delete(entity);
		}
		DaoConfig.closeCurrentSessionwithTransaction();

	}


}
