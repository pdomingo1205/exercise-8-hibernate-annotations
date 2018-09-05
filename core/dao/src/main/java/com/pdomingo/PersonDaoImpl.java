package com.pdomingo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Property;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.ObjectNotFoundException;

import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;

public class PersonDaoImpl implements DaoInterface<Person, Long> {

	private final Statistics stats = DaoConfig.getSessionFactory().getStatistics();

	public PersonDaoImpl(){
	}

	public void saveOrUpdate(Person entity) {
			DaoConfig.openCurrentSessionwithTransaction();
			DaoConfig.getCurrentSession().saveOrUpdate(entity);
			DaoConfig.showStatistics("from saveOrUpdate(entity)",stats);
			DaoConfig.closeCurrentSessionwithTransaction();
	}

	@SuppressWarnings("unchecked")
	public List<Role> findPersonRoles(Long id) {
		DaoConfig.openCurrentSessionwithTransaction();
		List<Role> roles;
		roles = DaoConfig.getCurrentSession().createCriteria(Role.class).createAlias("persons", "person")
							.add(Restrictions.eq("person.personId", id)).setCacheable(true).list();


		DaoConfig.closeCurrentSessionwithTransaction();

		return roles;
	}

	@SuppressWarnings("unchecked")
	public List<ContactInfo> findPersonContacts(Long id) {
		DaoConfig.openCurrentSessionwithTransaction();
		List<ContactInfo> contacts;
		contacts = DaoConfig.getCurrentSession().createCriteria(ContactInfo.class).add(Restrictions.eq("person.personId", id)).setCacheable(true).list();

		DaoConfig.closeCurrentSessionwithTransaction();
		return contacts;
	}

	public void update(Person entity) {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().update(entity);
		DaoConfig.getCurrentSession().flush();
		DaoConfig.showStatistics("from update(entity)",stats);
		DaoConfig.closeCurrentSessionwithTransaction();
	}

	public void updateRole(Person entity) {
		DaoConfig.openCurrentSessionwithTransaction();

		DaoConfig.getCurrentSession().update(entity);
		Person person = findById(entity.getPersonId());
		DaoConfig.getCurrentSession().evict(person);
		person = entity;
		DaoConfig.getCurrentSession().merge(person);

		DaoConfig.closeCurrentSessionwithTransaction();
	}

	public Person findById(Long id) {
		DaoConfig.openCurrentSessionwithTransaction();
		Person person = (Person) DaoConfig.getCurrentSession().get(Person.class, id);
		DaoConfig.getCurrentSession().evict(person);
		DaoConfig.closeCurrentSessionwithTransaction();
		person.getRoles();
		DaoConfig.showStatistics("from findById)",stats);

		return person;
	}

	public Person findPerson(Person person) {
		DaoConfig.openCurrentSessionwithTransaction();

		Criteria crit = DaoConfig.getCurrentSession().createCriteria(Person.class).setCacheable(true);
		crit.setCacheable(true);
		List<Person> list = crit.list();
		DaoConfig.getCurrentSession().evict(list.get(list.indexOf(person)));

		DaoConfig.closeCurrentSessionwithTransaction();
		DaoConfig.showStatistics("from findPerson)",stats);

		DaoConfig.getSessionFactory().getCache();
		return list.get(list.indexOf(person));

	}

	public void delete(Person entity) {
		DaoConfig.openCurrentSessionwithTransaction();
		DaoConfig.getCurrentSession().delete(entity);
		DaoConfig.getCurrentSession().flush();
		DaoConfig.closeCurrentSessionwithTransaction();
		//DaoConfig.getSessionFactory().evict(Person.class, entity.getPersonId());
		//findPerson(entity);
		//findAll();
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		List<Person> persons = null;
		DaoConfig.openCurrentSessionwithTransaction();
		try{
			persons = DaoConfig.getCurrentSession().createCriteria(Person.class).setCacheable(true).list();
		}catch(ObjectNotFoundException onfe){

		}
		DaoConfig.showStatistics("from findAll())",stats);
		DaoConfig.closeCurrentSessionwithTransaction();

		return persons;
	}

	public List<Person> listLastNameBy(String order) {
  		DaoConfig.openCurrentSessionwithTransaction();
		Criteria criteria;
		if(order.equals("ASC")){

			 criteria = DaoConfig.getCurrentSession().createCriteria(Person.class)
								.addOrder(Property.forName("name.lastName").asc());
		}else{
			criteria = DaoConfig.getCurrentSession().createCriteria(Person.class)
							  .addOrder(Property.forName("name.lastName").desc());

		}
		criteria.setCacheable(true);
		List<Person> list = criteria.list();
		DaoConfig.showStatistics("from listLastNameBy(field)",stats);
  		DaoConfig.closeCurrentSessionwithTransaction();
  		return list;
  	}

	public List<Person> findAllOrderBy(String field, String order) {
		DaoConfig.openCurrentSessionwithTransaction();
		Criteria criteria;
		List<Person> persons = (List<Person>) DaoConfig.getCurrentSession().createQuery(String.format("from Person ORDER BY %s %s", field, order)).list();
		DaoConfig.closeCurrentSessionwithTransaction();
		return persons;
	}



}
