package org.thibaut.wheretoclimb.consumer.contract;

import org.thibaut.wheretoclimb.consumer.repository.*;

public interface DaoFactory {

	UserRepository getUserRepository( );
	RoleRepository getRoleRepository( );
	AtlasRepository getAtlasRepository( );
	AreaRepository getAreaRepository( );
	MessageRepository getMessageRepository( );
	CommentRepository getCommentRepository( );
	BookingRequestRepository getBookingRequestRepository( );
	CragRepository getCragRepository( );

	void setUserRepository(UserRepository userRepository);
	void setRoleRepository(RoleRepository roleRepository);
	void setAtlasRepository(AtlasRepository atlasRepository);
	void setAreaRepository(AreaRepository areaRepository);
	void setMessageRepository( MessageRepository messageRepository );
	void setCommentRepository( CommentRepository commentRepository );
	void setBookingRequestRepository( BookingRequestRepository bookingRequestRepository );
	void setCragRepository( CragRepository cragRepository );

	RouteRepository getRouteRepository( );

	void setRouteRepository( RouteRepository routeRepository );

	PitchRepository getPitchRepository( );

	void setPitchRepository( PitchRepository pitchRepository );
}
