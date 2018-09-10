package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thibaut.wheretoclimb.consumer.contract.DaoFactory;

@Component
public class DaoFactoryImpl implements DaoFactory {

	private UserRepository userRepository;

	private UserRoleRepository userRoleRepository;

	private AtlasRepository atlasRepository;

	private AreaRepository areaRepository;


	@Override
	public UserRepository getUserRepository( ) {
		return userRepository;
	}

	@Override
	@Autowired
	public void setUserRepository( UserRepository userRepository ) {
		this.userRepository = userRepository;
	}

	@Override
	public UserRoleRepository getUserRoleRepository( ) {
		return userRoleRepository;
	}

	@Override
	@Autowired
	public void setUserRoleRepository( UserRoleRepository userRoleRepository ) {
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public AtlasRepository getAtlasRepository( ) {
		return atlasRepository;
	}

	@Override
	@Autowired
	public void setAtlasRepository( AtlasRepository atlasRepository ) {
		this.atlasRepository = atlasRepository;
	}

	@Override
	public AreaRepository getAreaRepository( ) {
		return areaRepository;
	}

	@Override
	@Autowired
	public void serAreaRepository( AreaRepository areaRepository ) {
		this.areaRepository = areaRepository;
	}
}
