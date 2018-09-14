package org.thibaut.wheretoclimb.business.contract;

public interface ManagerFactory {

	abstract UserManager getUserManager();

	void setUserManager( UserManager userManager );

	abstract AtlasManager getAtlasManager();

	void setAtlasManager( AtlasManager atlasManager );

	RoleManager getRoleManager( );

	void setRoleManager( RoleManager roleManager );
}
