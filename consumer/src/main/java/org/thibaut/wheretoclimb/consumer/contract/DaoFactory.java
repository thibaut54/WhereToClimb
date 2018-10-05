package org.thibaut.wheretoclimb.consumer.contract;

import org.thibaut.wheretoclimb.consumer.repository.AreaRepository;
import org.thibaut.wheretoclimb.consumer.repository.AtlasRepository;
import org.thibaut.wheretoclimb.consumer.repository.RoleRepository;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;

public interface DaoFactory {

	public UserRepository getUserRepository();
	public RoleRepository getRoleRepository( );
	public AtlasRepository getAtlasRepository();
	public AreaRepository getAreaRepository();

	void setUserRepository(UserRepository userRepository);
	void setRoleRepository(RoleRepository roleRepository);
	void setAtlasRepository(AtlasRepository atlasRepository);
	void serAreaRepository(AreaRepository areaRepository);
}
