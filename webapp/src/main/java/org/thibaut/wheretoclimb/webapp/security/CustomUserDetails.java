//package org.thibaut.wheretoclimb.webapp.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.util.StringUtils;
//import org.thibaut.wheretoclimb.model.beans.User;
//import org.thibaut.wheretoclimb.model.beans.UserRole;
//
//import java.util.Collection;
//import java.util.List;
//
//public class CustomUserDetails extends User implements UserDetails {
//
//	private static final long serialVersionUID = 1L;
//	private List< /*UserRole*/ String > userRoles;
//
//
//	public CustomUserDetails( User user, List< /*UserRole*/ String > userRoles ) {
//		super( user );
//		this.userRoles = userRoles;
//	}
//
//
//	@Override
//	public Collection< ? extends GrantedAuthority > getAuthorities( ) {
//
//		String roles = StringUtils.collectionToCommaDelimitedString( userRoles );
//		return AuthorityUtils.commaSeparatedStringToAuthorityList( roles );
//	}
//
//	@Override
//	public boolean isAccountNonExpired( ) {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked( ) {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired( ) {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled( ) {
//		return true;
//	}
//
//
//	@Override
//	public String getUsername( ) {
//		return super.getUserName( );
//	}
//
//
//}
