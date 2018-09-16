package org.thibaut.wheretoclimb.business.contract;

import java.util.List;

public interface RoleManager {
	List<String> findRoleByUserName( String username );
}
