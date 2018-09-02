package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.bean.Climber;

@Repository
public interface ClimberRepository extends JpaRepository< Climber, Integer > {

	@Query("select user from Climber user where user.email like :email and user.password like :password")
	public Climber findUserFromEmail(
			@Param( "email" ) String email,
			@Param( "password" ) String password );

}
