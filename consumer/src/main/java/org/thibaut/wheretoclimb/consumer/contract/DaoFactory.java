package org.thibaut.wheretoclimb.consumer.contract;

import org.thibaut.wheretoclimb.consumer.repository.*;

public interface DaoFactory {

	UserRepository getUserRepository( );
	RoleRepository getRoleRepository( );
	AtlasRepository getAtlasRepository( );
	AreaRepository getAreaRepository( );
	CommentRepository getCommentRepository( );
	BookingRequestRepository getBookingRequestRepository( );
	CragRepository getCragRepository( );
	RouteRepository getRouteRepository( );
	PitchRepository getPitchRepository( );

	void setUserRepository(UserRepository userRepository);
	void setRoleRepository(RoleRepository roleRepository);
	void setAtlasRepository(AtlasRepository atlasRepository);
	void setAreaRepository(AreaRepository areaRepository);
	void setCommentRepository( CommentRepository commentRepository );
	void setBookingRequestRepository( BookingRequestRepository bookingRequestRepository );
	void setCragRepository( CragRepository cragRepository );
	void setRouteRepository( RouteRepository routeRepository );
	void setPitchRepository( PitchRepository pitchRepository );
}
