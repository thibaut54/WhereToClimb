package org.thibaut.wheretoclimb.consumer.contract;

import org.thibaut.wheretoclimb.consumer.repository.AreaRepository;
import org.thibaut.wheretoclimb.consumer.repository.AtlasRepository;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
import org.thibaut.wheretoclimb.consumer.repository.UserRoleRepository;

public interface DaoFactory {

	public UserRepository getUserRepository();
	public UserRoleRepository getUserRoleRepository( );
	public AtlasRepository getAtlasRepository();
	public AreaRepository getAreaRepository();

	void setUserRepository(UserRepository userRepository);
	void setUserRoleRepository( UserRoleRepository userRoleRepository );
	void setAtlasRepository(AtlasRepository atlasRepository);
	void serAreaRepository(AreaRepository areaRepository);
}
