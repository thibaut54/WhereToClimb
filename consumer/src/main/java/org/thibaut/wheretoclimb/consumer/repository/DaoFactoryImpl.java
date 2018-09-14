package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.consumer.contract.DaoFactory;

@Component
public class DaoFactoryImpl implements DaoFactory {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

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
	public RoleRepository getRoleRepository( ) {
		return roleRepository;
	}

	@Override
	@Autowired
	public void setRoleRepository( RoleRepository roleRepository ) {
		this.roleRepository = roleRepository;
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
