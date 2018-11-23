package org.thibaut.wheretoclimb.business.impl;

import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.PitchManager;
import org.thibaut.wheretoclimb.model.entity.Pitch;
import org.thibaut.wheretoclimb.model.entity.Route;

import java.util.Optional;

@Component
public class PitchManagerImpl extends AbstractManager implements PitchManager {

	@Override
	public void savePitch( Pitch pitch ) {
		getDaoFactory().getPitchRepository().save( pitch );
	}

	@Override
	public Pitch findPitchById( Integer id ){

		if(id != null){
			Optional< Pitch > pitchOpt =  getDaoFactory().getPitchRepository().findById( id );
			if(pitchOpt.isPresent()){
				return pitchOpt.get();
			}
		}
		return null;
	}


	@Override
	public void deletePitch( Integer id ){
		getDaoFactory().getCommentRepository().deleteAll( findPitchById( id ).getComments() );
		getDaoFactory().getPitchRepository().deleteById( id );
	}

	@Override
	public Pitch createPitch( Pitch pitch ){
		return getDaoFactory().getPitchRepository().save( pitch );
	}
}
