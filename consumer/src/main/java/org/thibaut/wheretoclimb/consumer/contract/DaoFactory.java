package org.thibaut.wheretoclimb.consumer.contract;

import org.thibaut.wheretoclimb.consumer.repository.*;

public interface DaoFactory {

	public UserRepository getUserRepository();
	public RoleRepository getRoleRepository( );
	public AtlasRepository getAtlasRepository();
	public AreaRepository getAreaRepository();

	void setUserRepository(UserRepository userRepository);
	void setRoleRepository(RoleRepository roleRepository);
	void setAtlasRepository(AtlasRepository atlasRepository);
	void setAreaRepository(AreaRepository areaRepository);

	MessageRepository getMessageRepository( );

	void setMessageRepository( MessageRepository messageRepository );

	CommentRepository getCommentRepository( );

	void setCommentRepository( CommentRepository commentRepository );

	BookingRequestRepository getBookingRequestRepository( );

	void setBookingRequestRepository( BookingRequestRepository bookingRequestRepository );
}
