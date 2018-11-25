package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository< User, Integer > {


//	@Query("select user from User user where user.userName=:usernameOrEmail or user.email=:usernameOrEmail")
//	public User findByUsernameOrEmail(
//			@Param("usernameOrEmail") String usernameOrEmail);

//	Optional< User > findByName( String username );

//	Optional< User> findByUserName( String userName );

	User findByUserName( String username );

	User findByEmail( String email );

}
