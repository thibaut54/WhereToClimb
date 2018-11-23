package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.Pitch;

public interface PitchManager {
	void savePitch( Pitch pitch );

	Pitch findPitchById( Integer id );

	void deletePitch( Integer id );

	Pitch createPitch( Pitch pitch );
}
