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

	private MessageRepository messageRepository;

	private CommentRepository commentRepository;

	private BookingRequestRepository bookingRequestRepository;


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
	public void setAreaRepository( AreaRepository areaRepository ) {
		this.areaRepository = areaRepository;
	}

	@Override
	public MessageRepository getMessageRepository( ) {
		return messageRepository;
	}

	@Override
	@Autowired
	public void setMessageRepository( MessageRepository messageRepository ) {
		this.messageRepository = messageRepository;
	}

	@Override
	public CommentRepository getCommentRepository( ) {
		return commentRepository;
	}

	@Override
	@Autowired
	public void setCommentRepository( CommentRepository commentRepository ) {
		this.commentRepository = commentRepository;
	}

	@Override
	public BookingRequestRepository getBookingRequestRepository( ) {
		return bookingRequestRepository;
	}

	@Override
	@Autowired
	public void setBookingRequestRepository( BookingRequestRepository bookingRequestRepository ) {
		this.bookingRequestRepository = bookingRequestRepository;
	}
}
