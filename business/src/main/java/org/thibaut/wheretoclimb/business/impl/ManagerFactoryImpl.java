package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.*;

@Component
public class ManagerFactoryImpl implements ManagerFactory {


	private UserManager userManager;
	private AtlasManager atlasManager;
	private RoleManager roleManager;
	private PasswordManager passwordManager;
	private AreaManager areaManager;
	private BookingRequestManager bookingRequestManager;
	private CragManager cragManager;
	private RouteManager routeManager;
	private PitchManager pitchManager;
	private ElementManager elementManager;
	private CommentManager commentManager;

	@Override
	public UserManager getUserManager( ) {
		return userManager;
	}

	@Override
	@Autowired
	public void setUserManager( UserManager userManager ) {
		this.userManager = userManager;
	}

	@Override
	public AtlasManager getAtlasManager( ) {
		return atlasManager;
	}

	@Override
	@Autowired
	public void setAtlasManager( AtlasManager atlasManager ) {
		this.atlasManager = atlasManager;
	}


	@Override
	public RoleManager getRoleManager( ) {
		return roleManager;
	}

	@Override
	@Autowired
	public void setRoleManager( RoleManager roleManager ) {
		this.roleManager = roleManager;
	}

	@Override
	public PasswordManager getPasswordManager( ) {
		return passwordManager;
	}

	@Override
	@Autowired
	public void setPasswordManager( PasswordManager passwordManager ) {
		this.passwordManager = passwordManager;
	}


	@Override
	public AreaManager getAreaManager( ) {
		return areaManager;
	}

	@Override
	@Autowired
	public void setAreaManager( AreaManager areaManager ) {
		this.areaManager = areaManager;
	}

	@Override
	public BookingRequestManager getBookingRequestManager( ) {
		return bookingRequestManager;
	}

	@Override
	@Autowired
	public void setBookingRequestManager( BookingRequestManager bookingRequestManager ) {
		this.bookingRequestManager = bookingRequestManager;
	}


	@Override
	public CragManager getCragManager( ) {
		return cragManager;
	}

	@Override
	@Autowired
	public void setCragManager( CragManager cragManager ) {
		this.cragManager = cragManager;
	}

	@Override
	public RouteManager getRouteManager( ) {
		return routeManager;
	}

	@Override
	@Autowired
	public void setRouteManager( RouteManager routeManager ) {
		this.routeManager = routeManager;
	}

	@Override
	public PitchManager getPitchManager( ) {
		return pitchManager;
	}

	@Override
	@Autowired
	public void setPitchManager( PitchManager pitchManager ) {
		this.pitchManager = pitchManager;
	}


	@Override
	public ElementManager getElementManager( ) {
		return elementManager;
	}

	@Override
	@Autowired
	public void setElementManager( ElementManager elementManager ) {
		this.elementManager = elementManager;
	}

	@Override
	public CommentManager getCommentManager( ) {
		return commentManager;
	}

	@Override
	@Autowired
	public void setCommentManager( CommentManager commentManager ) {
		this.commentManager = commentManager;
	}
}
