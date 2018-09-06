package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.beans.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository< User, Integer > {

	@Query("select user from User user where user.email like :email and user.password like :password")
	public User findUserFromEmail(
			@Param( "email" ) String email,
			@Param( "password" ) String password );

	@Query("select user from User user where user.userName=:usernameOrEmail or user.email=:usernameOrEmail")
	public User findByUsernameOrEmail(
			@Param("usernameOrEmail") String usernameOrEmail);

//	Optional< User > findByName( String username );

	Optional< User> findByUserName( String userName );

//	/*@Query("select user from User user where user.email=:username")
//	public*/ Optional<User> findByName(
//			/*@Param( "username" )*/ String username);

}
