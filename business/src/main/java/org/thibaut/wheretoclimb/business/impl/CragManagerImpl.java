package org.thibaut.wheretoclimb.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.CragManager;
import org.thibaut.wheretoclimb.model.entity.Crag;

import java.util.Optional;

@Component
@Slf4j
public class CragManagerImpl extends AbstractManager implements CragManager {


	@Override
	public Crag findCragById( Integer id ){

		if(id != null){
			Optional< Crag > crag =  getDaoFactory().getCragRepository().findById( id );
			if(crag.isPresent()){
				return crag.get();
			}
		}
		return null;
	}


	@Override
	public void saveCrag( Crag crag ) {
		getDaoFactory().getCragRepository().save( crag );
	}


	@Override
	public void deleteCrag( Integer id ){
		getDaoFactory().getCommentRepository().deleteAll( findCragById( id ).getComments() );
		getDaoFactory().getCragRepository().deleteById( id );
	}
}
