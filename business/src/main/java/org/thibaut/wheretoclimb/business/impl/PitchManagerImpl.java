package org.thibaut.wheretoclimb.business.impl;

import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.PitchManager;
import org.thibaut.wheretoclimb.model.entity.Pitch;
import org.thibaut.wheretoclimb.model.entity.Route;

@Component
public class PitchManagerImpl extends AbstractManager implements PitchManager {

	@Override
	public void savePitch( Pitch pitch ) {
		getDaoFactory().getPitchRepository().save( pitch );
	}
}
