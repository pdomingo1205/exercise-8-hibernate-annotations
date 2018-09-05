package com.pdomingo.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public interface DaoInterface<T, Id extends Serializable> {
	
	public void saveOrUpdate(T entity);

	public void update(T entity);

	public T findById(Id id);

	public void delete(T entity);

	public List<T> findAll();


}
