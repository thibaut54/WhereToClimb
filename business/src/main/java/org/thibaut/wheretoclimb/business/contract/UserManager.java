package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.beans.User;

import java.util.List;

public interface UserManager {

	public List< User > getUsers();
}
