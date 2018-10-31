package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.Crag;

public interface CragManager {

	Crag findById( Integer id );
}
