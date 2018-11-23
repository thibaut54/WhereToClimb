package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository< Role, Integer > {

//	@Query("SELECT role.role FROM Role role " +
//			       "INNER JOIN User user ON user.roles.id = role.id " +
//			       "WHERE user.userName =:username ")
	@Query("SELECT r.role FROM Role r JOIN r.users u WHERE u.userName =:username")
	List<String> findRoleByUserName( @Param( "username" ) String username );

	@Query("SELECT r.role FROM Role r JOIN r.users u WHERE u.email =:email")
	List<String> findRoleByEmail( @Param( "email" ) String email );

	//	@Query("SELECT r.role FROM Role r WHERE r.role =:role")
	List<Role> findRolesByRoleContains( String role );

	Role findByRoleLike( String role );
}
