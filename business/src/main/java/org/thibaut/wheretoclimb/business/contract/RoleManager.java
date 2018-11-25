package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.Role;

import java.util.List;

public interface RoleManager {
	List<String> findRoleByUserName( String username );

	List<Role> findRoleByRoleName( String roleName );
}
