package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.beans.UserRole;
import org.thibaut.wheretoclimb.model.beans.User;


import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository< UserRole, Integer > {

//	SELECT user_role.role FROM user_role, users
//	WHERE  user_role.user_id = users.id
//	AND users.user_name = 'Admin'

	@Query("SELECT userRole.role FROM UserRole userRole , User user WHERE userRole.user.id = user.id AND user.userName =?1")
	public List</*UserRole*/ String> findRoleByUserName( String username );

	//Il n'arrive pas à choper l'attribut userId dans UserRole car ce putain d'attribut n'existe pas !!! L'attribut est bien créé en DB mais n'est pas dans mon bean Java. Je pense que c'est la faute au mapping mais alors comment fucking faire... ?!!

}
