package com.pdomingo.service;

import java.util.List;
import java.util.Set;


import com.pdomingo.dao.RoleDaoImpl;
import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;
import org.hibernate.exception.ConstraintViolationException;

public class RoleService {

	private static EntityMapper entityMapper = new EntityMapper();
	private static RoleDaoImpl roleDao;

	public RoleService() {
		roleDao = new RoleDaoImpl();
	}
	public RoleService(RoleDaoImpl newDao) {
		roleDao = newDao;
	}

	public String saveOrUpdate(RoleDTO entity) {
		String textToReturn ="";

		try{
			roleDao.saveOrUpdate(entityMapper.mapToRole(entity));
			textToReturn =("\n\t!!! Role added! !!!\n");

		}catch(Exception e){
			textToReturn = "\n\t!-- Failed to add role --!\n";
		}

		return textToReturn;
	}

	public List<RoleDTO> findAllRoleDTO(){
		return entityMapper.getAllRoles();
	}

	public String update(RoleDTO entity) {
		String textToReturn;

		try{

				roleDao.update(entityMapper.mapToRole(entity));
				textToReturn = ("\n\t!!! Role updated! !!!\n");

		}catch(Exception e){
			textToReturn = "\n\t!-- Failed to update Role --!\n";
		}

		return textToReturn;
	}

	public Boolean checkIfExists(Long Id){
		Boolean exists = true;

		Role role = roleDao.findById(Id);

		if(role == null){
			exists = false;
		}

		return exists;
	}

	public RoleDTO findById(Long id) {
		Role role = roleDao.findById(id);

		return entityMapper.mapToRoleDTO(role);
	}

	public Boolean checkIfNameExists(String roleText){
		Boolean exists = true;
		try{

			Role role = roleDao.findByRoleName(roleText);

			if (role == null) {
				exists = false;
			}

		}catch(Exception e){

		}

		return exists;
	}



	public RoleDTO checkIfUnique(String roleText){
		Role role = new Role();
		try{

			role = roleDao.findByRoleName(roleText);
			if (role == null) {
				role = new Role();
				role.setRole(roleText);
			}

		}catch(Exception e){
			System.out.println(e.getMessage());
		}


		return entityMapper.mapToRoleDTO(role);
	}

	public String delete(Long id) {
		String textToReturn;

		try{
			if(checkIfExists(id)){
				Role role = roleDao.findById(id);
				roleDao.delete(role);
				textToReturn = "\n\t!!! Role deleted !!!\n";
			}
			else{
				textToReturn = "\n\t!-- Role does not exist --!\n";
			}
		}catch(ConstraintViolationException pse){
			textToReturn = "\n\t!-- Another Person is still assigned to this Role --!\n";
		}

		return textToReturn;
	}

	public List<Role> findAll() {

		List<Role> roles = roleDao.findAll();
		return roles;
	}

	public String deleteAll() {

		String textToReturn;

		try{
				roleDao.deleteAll();
				textToReturn = "\n\t!!! Role deleted !!!\n";
		}catch(ConstraintViolationException pse){
			textToReturn = "\n\t!-- Another Person is still assigned to this Role --!\n";
		}

		return textToReturn;
	}

	public RoleDaoImpl roleDao() {
		return roleDao;
	}
}
