package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.RoleManager;
import org.thibaut.wheretoclimb.consumer.repository.RoleRepository;
import org.thibaut.wheretoclimb.model.entity.Role;

import java.util.List;

@Component
public class RoleManagerImpl extends AbstractManager implements RoleManager {

	@Autowired
	private RoleRepository roleRepository;


	@Override
	public List<String> findRoleByUserName( String username ){

		List<String> roles = null;
		try {
			roles = roleRepository.findRoleByUserName( username );
		} catch ( Exception e ){
			throw e;
		}
		return roles;
	}

	@Override
	public List<Role> findRoleByRoleName( String roleName ){
		return getDaoFactory().getRoleRepository().findRolesByRoleContains( roleName );
	}

}
