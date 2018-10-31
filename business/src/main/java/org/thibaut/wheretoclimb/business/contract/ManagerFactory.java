package org.thibaut.wheretoclimb.business.contract;

public interface ManagerFactory {

	abstract UserManager getUserManager();

	void setUserManager( UserManager userManager );

	abstract AtlasManager getAtlasManager();

	void setAtlasManager( AtlasManager atlasManager );

	RoleManager getRoleManager( );

	void setRoleManager( RoleManager roleManager );

	PasswordManager getPasswordManager( );

	void setPasswordManager( PasswordManager passwordManager );

	AreaManager getAreaManager( );

	void setAreaManager( AreaManager areaManager );

	BookingRequestManager getBookingRequestManager( );

	void setBookingRequestManager( BookingRequestManager bookingRequest );

	CragManager getCragManager( );

	void setCragManager( CragManager cragManager );

	RouteManager getRouteManager( );

	void setRouteManager( RouteManager routeManager );

	PitchManager getPitchManager( );

	void setPitchManager( PitchManager pitchManager );
}
